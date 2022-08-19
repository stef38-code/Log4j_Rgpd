package org.stephane.appender.mask;

import java.util.regex.Pattern;

public class Telephone implements LogMasker {
    @Override
    public Pattern getFindPattern() {
        return Pattern.compile(buildExpression());
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(\\d)" );
    }
    private String buildExpression(){
        return ConstRegexp.DEBUT +
                "(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})" +
                ConstRegexp.FIN;
    }
}
