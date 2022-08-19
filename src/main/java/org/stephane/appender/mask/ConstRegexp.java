package org.stephane.appender.mask;

public class ConstRegexp {
    private ConstRegexp() {
        throw new UnsupportedOperationException("ConstRegexp is a utility class and cannot be instantiated");
    }
    public static final String DEBUT = "(?:^|\\W)";
    public static final String FIN = "(?:$|\\W)";
}
