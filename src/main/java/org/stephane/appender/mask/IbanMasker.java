package org.stephane.appender.mask;

import java.util.regex.Pattern;

/**
 * @see https://code.net.ua/regex-iban/
 */
public class IbanMasker implements LogMasker {
    @Override
    public Pattern getFindPattern() {
        return Pattern.compile("(?:^|\\s*)(FR\\d{12}\\w{11}\\d{2})(?:$|\\s*|\\.|\\,)");
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(\\d)");
    }
}
