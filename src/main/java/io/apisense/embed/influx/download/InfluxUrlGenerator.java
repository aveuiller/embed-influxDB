package io.apisense.embed.influx.download;

import io.apisense.embed.influx.configuration.OSType;
import io.apisense.embed.influx.configuration.VersionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Default implementation of an {@link UrlGenerator}.
 */
public class InfluxUrlGenerator implements UrlGenerator {
    private static final Logger logger = LoggerFactory.getLogger(InfluxUrlGenerator.class.getName());
    public static final String HOSTNAME = "https://dl.influxdata.com";
    public static final String BASE_URL = "influxdb";
    private static final String PRODUCT_NAME = "influxdb";

    @Override
    public URL buildSource(VersionConfiguration configuration) {
        StringBuilder builder = new StringBuilder();
        // For a MacOS system, we will have to build the influxdb from sources
        if (configuration.os.equals(OSType.MacOS_X)) {
            builder.append("https://github.com/influxdata/influxdb/archive/v")
                    .append(configuration.version.dlPath) // FIXME: This will not work with nightly version!
                    .append(".tar.gz");
        } else {
            builder.append(HOSTNAME)
                    .append("/").append(BASE_URL)
                    .append("/").append(configuration.version.directory)
                    .append("/").append(PRODUCT_NAME)
                    .append("-").append(configuration.version.dlPath)
                    .append("_").append(configuration.os.dlPath)
                    .append("_").append(configuration.architecture.dlPath)
                    .append(".").append(configuration.os.archiveType.extension);
        }
        try {
            return new URL(builder.toString());
        } catch (MalformedURLException e) {
            logger.error("Unable to create valid download URL", e);
            throw new RuntimeException(e);
        }
    }
}
