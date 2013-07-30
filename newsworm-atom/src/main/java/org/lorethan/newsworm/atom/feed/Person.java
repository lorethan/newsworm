package org.lorethan.newsworm.atom.feed;

public class Person
{
    private String name;
    private String uri;
    private String email;

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Person))
        {
            return false;
        }

        final Person author = (Person) o;

        if (email != null ? !email.equals(author.email) : author.email != null)
        {
            return false;
        }
        if (name != null ? !name.equals(author.name) : author.name != null)
        {
            return false;
        }
        if (uri != null ? !uri.equals(author.uri) : author.uri != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
