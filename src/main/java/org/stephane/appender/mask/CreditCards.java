package org.stephane.appender.mask;

import java.util.regex.Pattern;

/**
 * @see https://code.net.ua/regex-credit-cards/
 */
public class CreditCards implements LogMasker {
    @Override
    public Pattern getFindPattern() {
        return Pattern.compile(buildExpression());
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(\\d)");
    }
    private String buildExpression(){
        return ConstRegexp.DEBUT +
                "(?:[2|4|5|6|9]\\d{3})([ -]?)\\d{4}\\1\\d{4}\\1\\d{4}" +
                ConstRegexp.FIN;
    }
}
