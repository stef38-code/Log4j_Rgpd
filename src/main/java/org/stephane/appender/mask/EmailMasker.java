package org.stephane.appender.mask;

import java.util.regex.Pattern;

public class EmailMasker implements LogMasker {
    @Override
    public Pattern getFindPattern() {
        return Pattern.compile("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)");
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(?<=.)[^@](?=[^@]*?[^@]@)|(?:(?<=@.)|(?!^)\\G(?=[^@]*$)).(?=.*[^@]\\.)");
    }
}
