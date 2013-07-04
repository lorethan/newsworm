package org.lorethan.newsworm.core.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlCharsetResolvingReader extends Reader
{
    private static final String UTF_8 = "UTF-8";
    private static final String US_ASCII = "US-ASCII";
    private static final String UTF_16BE = "UTF-16BE";
    private static final String UTF_16LE = "UTF-16LE";

    private Reader internalReader;
    private final Pattern CHARSET_PATTERN = Pattern.compile("charset=([.[^; ]]*)");
    private static final Pattern XML_DECLARATION_ENCODING_PATTERN = Pattern.compile("<\\?xml.*encoding[\\s]*=[\\s]*((?:\".[^\"]*\")|(?:'.[^']*'))", Pattern.MULTILINE);
    private static final int BUFFER_SIZE = 4096;
    private final String encoding;

    private String getContentTypeEncoding(final String httpContentType)
    {
        String encoding = null;
        if (httpContentType != null)
        {
            int i = httpContentType.indexOf(";");
            if (i > -1)
            {
                final String attributes = httpContentType.substring(i + 1);
                final Matcher m = CHARSET_PATTERN.matcher(attributes);
                encoding = (m.find()) ? m.group(1) : null;
                encoding = (encoding != null) ? encoding.toUpperCase() : null;
            }
            if (encoding != null && ((encoding.startsWith("\"") && encoding.endsWith("\"")) || (encoding.startsWith("'") && encoding.endsWith("'"))))
            {
                encoding = encoding.substring(1, encoding.length() - 1);
            }
        }

        return encoding;
    }

    public XmlCharsetResolvingReader(final InputStream inputStream) throws IOException
    {
        this(inputStream, null);
    }

    public XmlCharsetResolvingReader(final InputStream inputStream, final String httpContentType) throws IOException
    {
        encoding = resolveEncoding(inputStream, httpContentType);

        internalReader = new InputStreamReader(inputStream, resolveEncoding(inputStream, httpContentType));
    }

    public String getEncoding()
    {
        return encoding;
    }

    private String resolveEncoding(final InputStream inputStream, final String httpContentType) throws IOException
    {
        String resolvedEncoding = null;
        if (httpContentType != null)
        {
            resolvedEncoding = resolveEncodingAccordingToRFC3023(inputStream, httpContentType);
        }

        final String bestEffortEncoding = resolveEncodingByBestEffort(inputStream);
        if (resolvedEncoding == null)
        {
            if (bestEffortEncoding != null)
            {
                resolvedEncoding = resolveEncodingByXmlDeclaration(inputStream, bestEffortEncoding);
            }
        }

        if (resolvedEncoding == null)
        {
            resolvedEncoding = resolveEncodingByBom(inputStream);

        }

        if (resolvedEncoding == null && bestEffortEncoding != null)
        {
            resolvedEncoding = bestEffortEncoding;

        }
        if (resolvedEncoding == null)
        {
            resolvedEncoding = UTF_8;
        }

        return resolvedEncoding;
    }


    private String resolveEncodingByXmlDeclaration(final InputStream inputStream, final String bestEffortEncoding) throws IOException
    {
        String encoding = null;
        if (bestEffortEncoding != null)
        {
            byte[] bytes = new byte[BUFFER_SIZE];
            inputStream.mark(BUFFER_SIZE);
            int offset = 0;
            int max = BUFFER_SIZE;
            int c = inputStream.read(bytes, offset, max);
            int firstGreaterThan = -1;
            while (c != -1 && firstGreaterThan == -1 && offset < BUFFER_SIZE)
            {
                offset += c;
                max -= c;
                c = inputStream.read(bytes, offset, max);
                firstGreaterThan = new String(bytes, 0, offset).indexOf(">");
            }
            if (firstGreaterThan == -1)
            {
                if (c == -1)
                {
                    throw new IOException("Unexpected end of XML stream");
                }
                else
                {
                    throw new IOException("XML declaration or ROOT element not found on first " + offset + " bytes");
                }
            }
            int bytesRead = offset;
            if (bytesRead > 0)
            {
                inputStream.reset();
                Reader reader = new InputStreamReader(new ByteArrayInputStream(bytes, 0, firstGreaterThan + 1), bestEffortEncoding);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuffer xmlDeclaration = new StringBuffer();
                String line = bufferedReader.readLine();
                while (line != null)
                {
                    xmlDeclaration.append(line);
                    line = bufferedReader.readLine();
                }
                Matcher m = XML_DECLARATION_ENCODING_PATTERN.matcher(xmlDeclaration);
                if (m.find())
                {
                    encoding = m.group(1).toUpperCase();
                    encoding = encoding.substring(1, encoding.length() - 1);
                }
            }
        }
        return encoding;
    }

    private String resolveEncodingByBom(final InputStream inputStream) throws IOException
    {
        String encoding = null;
        int[] bytes = new int[3];
        inputStream.mark(3);
        bytes[0] = inputStream.read();
        bytes[1] = inputStream.read();
        bytes[2] = inputStream.read();

        if (bytes[0] == 0xFE && bytes[1] == 0xFF)
        {
            encoding = UTF_16BE;
            inputStream.reset();
            inputStream.read();
            inputStream.read();
        }
        else if (bytes[0] == 0xFF && bytes[1] == 0xFE)
        {
            encoding = UTF_16LE;
            inputStream.reset();
            inputStream.read();
            inputStream.read();
        }
        else if (bytes[0] == 0xEF && bytes[1] == 0xBB && bytes[2] == 0xBF)
        {
            encoding = UTF_8;
        }
        else
        {
            inputStream.reset();
        }

        return encoding;
    }

    private String resolveEncodingAccordingToRFC3023(final InputStream inputStream, final String httpContentType)
    {
        String resolvedEncoding = null;

        // is the content type some variant of application/xml?
        if (httpContentType.startsWith("application/xml") || (httpContentType.startsWith("application/") && httpContentType.endsWith("+xml")))
        {
            // try by content type charset declaration
            resolvedEncoding = getContentTypeEncoding(httpContentType);

            // then look at XML declaration
            if (resolvedEncoding == null)
            {
                final BufferedInputStream bis = new BufferedInputStream(inputStream, BUFFER_SIZE);
            }

            // default to UTF-8
            if (resolvedEncoding == null)
            {
                resolvedEncoding = UTF_8;
            }
        }
        // is it some kind of text/xml?
        else if (httpContentType.startsWith("text/xml") || (httpContentType.startsWith("text/") && httpContentType.endsWith("+xml")))
        {
            // try by content type charset declaration
            resolvedEncoding = getContentTypeEncoding(httpContentType);

            // default to US-ASCII
            if (resolvedEncoding == null)
            {
                resolvedEncoding = US_ASCII;
            }
        }

        return resolvedEncoding;
    }

    private String resolveEncodingByBestEffort(final InputStream inputStream) throws IOException
    {
        String encoding = null;
        int[] bytes = new int[4];
        inputStream.mark(4);
        bytes[0] = inputStream.read();
        bytes[1] = inputStream.read();
        bytes[2] = inputStream.read();
        bytes[3] = inputStream.read();
        inputStream.reset();

        if (bytes[0] == 0x00 && bytes[1] == 0x3C && bytes[2] == 0x00 && bytes[3] == 0x3F)
        {
            encoding = UTF_16BE;
        }
        else if (bytes[0] == 0x3C && bytes[1] == 0x00 && bytes[2] == 0x3F && bytes[3] == 0x00)
        {
            encoding = UTF_16LE;
        }
        else if (bytes[0] == 0x3C && bytes[1] == 0x3F && bytes[2] == 0x78 && bytes[3] == 0x6D)
        {
            encoding = UTF_8;
        }
        return encoding;
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException
    {
        return internalReader.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException
    {
        internalReader.close();
    }
}
