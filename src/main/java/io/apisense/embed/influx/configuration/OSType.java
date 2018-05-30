package io.apisense.embed.influx.configuration;

import de.flapdoodle.embed.process.distribution.Platform;
import io.apisense.embed.influx.download.InfluxArchiveType;

/**
 * Available platforms for InfluxDB associated with their available archive {@link InfluxArchiveType}.
 */
public enum OSType {
    Linux("linux", InfluxArchiveType.TGZ, Platform.Linux),
    Windows("windows", InfluxArchiveType.ZIP, Platform.Windows),
    MacOS_X("MacOsX", InfluxArchiveType.TGZ, Platform.OS_X);

    public final String dlPath;
    public final InfluxArchiveType archiveType;
    private Platform platform;

    OSType(String dlPath, InfluxArchiveType archiveType, Platform platform) {
        this.dlPath = dlPath;
        this.archiveType = archiveType;
        this.platform = platform;
    }

    /**
     * Return the {@link OSType} for the current system.
     *
     * @return The value of the currently running {@link OSType}.
     */
    public static OSType getCurrent() {
        switch (System.getProperty("os.name").toLowerCase()){
            case "windows":
                return OSType.Windows;
            case "mac os x":
                return OSType.MacOS_X;
            default:
                return OSType.Linux;
        }
    }

    public Platform toPlatform() {
        return platform;
    }
}
