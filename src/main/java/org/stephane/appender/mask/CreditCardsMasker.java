package org.stephane.appender.mask;

import java.util.regex.Pattern;

/**
 * @see https://code.net.ua/regex-credit-cards/
 */
public class CreditCardsMasker extends LogMasker {
    @Override
    public Pattern getFindPattern() {
        return Pattern.compile("(?:^|\\s)(?:[4|5|6|9]\\d{3})([ -]?)\\d{4}\\1\\d{4}\\1\\d{4}(?:$|\\s|\\.|\\,)");
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(\\d)");
    }
}
