package org.stephane.appender.mask;

import java.util.regex.Pattern;

/**
 * @see https://code.net.ua/regex-iban/
 */
public class Iban implements LogMasker {
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
                "(FR\\d{12}\\w{11}\\d{2})" +
                ConstRegexp.FIN;
    }
}
