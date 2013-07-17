package org.lorethan.newsworm.rss.feed;

public class Cloud
{
    private String domain;
    private int port;
    private String path;
    private String registerProcedure;
    private String protocol;

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(final String domain)
    {
        this.domain = domain;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(final int port)
    {
        this.port = port;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(final String path)
    {
        this.path = path;
    }

    public String getRegisterProcedure()
    {
        return registerProcedure;
    }

    public void setRegisterProcedure(final String registerProcedure)
    {
        this.registerProcedure = registerProcedure;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(final String protocol)
    {
        this.protocol = protocol;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Cloud))
        {
            return false;
        }

        final Cloud cloud = (Cloud) o;

        if (port != cloud.port)
        {
            return false;
        }
        if (domain != null ? !domain.equals(cloud.domain) : cloud.domain != null)
        {
            return false;
        }
        if (path != null ? !path.equals(cloud.path) : cloud.path != null)
        {
            return false;
        }
        if (protocol != null ? !protocol.equals(cloud.protocol) : cloud.protocol != null)
        {
            return false;
        }
        if (registerProcedure != null ? !registerProcedure.equals(cloud.registerProcedure) : cloud.registerProcedure != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = domain != null ? domain.hashCode() : 0;
        result = 31 * result + port;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (registerProcedure != null ? registerProcedure.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        return result;
    }
}
