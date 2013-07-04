package org.lorethan.newsworm.core.extension.syndication;

import java.util.Date;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.AbstractExtension;

public class SyndicationExtension extends AbstractExtension
{
    public static final String NAMESPACE_URI = "http://purl.org/rss/1.0/modules/syndication/";

    private UpdatePeriod updatePeriod = UpdatePeriod.DAILY;
    private int updateFrequency = 1;
    private Date updateBase;

    public SyndicationExtension()
    {
        super(Namespace.getNamespace(NAMESPACE_URI));
    }

    public UpdatePeriod getUpdatePeriod()
    {
        return updatePeriod;
    }

    public void setUpdatePeriod(final UpdatePeriod updatePeriod)
    {
        this.updatePeriod = updatePeriod;
    }

    public int getUpdateFrequency()
    {
        return updateFrequency;
    }

    public void setUpdateFrequency(final int updateFrequency)
    {
        this.updateFrequency = updateFrequency;
    }

    public Date getUpdateBase()
    {
        return updateBase;
    }

    public void setUpdateBase(final Date updateBase)
    {
        this.updateBase = updateBase;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof SyndicationExtension))
        {
            return false;
        }

        final SyndicationExtension that = (SyndicationExtension) o;

        if (updateFrequency != that.updateFrequency)
        {
            return false;
        }
        if (updateBase != null ? !updateBase.equals(that.updateBase) : that.updateBase != null)
        {
            return false;
        }
        if (updatePeriod != that.updatePeriod)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = updatePeriod != null ? updatePeriod.hashCode() : 0;
        result = 31 * result + updateFrequency;
        result = 31 * result + (updateBase != null ? updateBase.hashCode() : 0);
        return result;
    }

    public enum UpdatePeriod
    {
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY;

        public static UpdatePeriod fromString(final String updatePeriodAsString)
        {
            switch (updatePeriodAsString)
            {
                case "hourly":
                    return HOURLY;
                case "daily":
                    return DAILY;
                case "weekly":
                    return WEEKLY;
                case "monthly":
                    return MONTHLY;
                case "yearly":
                    return YEARLY;
                default:
                    throw new IllegalArgumentException("Unknown update period: " + updatePeriodAsString);
            }
        }
    }
}
