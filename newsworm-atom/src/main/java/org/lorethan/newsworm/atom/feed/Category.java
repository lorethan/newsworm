package org.lorethan.newsworm.atom.feed;

public class Category
{
    private String term;
    private String scheme;
    private String label;

    public String getTerm()
    {
        return term;
    }

    public void setTerm(final String term)
    {
        this.term = term;
    }

    public String getScheme()
    {
        return scheme;
    }

    public void setScheme(final String scheme)
    {
        this.scheme = scheme;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(final String label)
    {
        this.label = label;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Category))
        {
            return false;
        }

        final Category category = (Category) o;

        return !(label != null ? !label.equals(category.label) : category.label != null) && !(scheme != null ? !scheme.equals(category.scheme) : category.scheme != null) &&
                !(term != null ? !term.equals(category.term) : category.term != null);

    }

    @Override
    public int hashCode()
    {
        int result = term != null ? term.hashCode() : 0;
        result = 31 * result + (scheme != null ? scheme.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
