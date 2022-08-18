package org.stephane.appender.mask;

import java.util.regex.Pattern;

public class NssMasker implements LogMasker {

    @Override
    public Pattern getFindPattern() {
        return Pattern.compile(buildExpression());
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(\\w)");
    }
    private String buildExpression(){
        return ConstRegexp.DEBUT +
                "([1|2])\\s*(\\d{2})\\s*(0\\d|1[0-2])\\s*(2[AB]|\\d{2})\\s*(\\d{3})\\s*(\\d{3})\\s*(\\d{2})" +
                ConstRegexp.FIN;
    }
}
