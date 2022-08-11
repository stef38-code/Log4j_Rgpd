package org.stephane.appender;

import org.apache.commons.lang3.RegExUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NssMasker implements LogMasker {
    private final Pattern findPattern = Pattern.compile("(([12])\\s{0,}([0-9]{2})\\s{0,}(0[1-9]|1[0-2])\\s{0,})(2[AB]|[0-9]{2})\\s{0,}([0-9]{3})\\s{0,}([0-9]{3})\\s{0,}([0-9]{2})\\s{0,}");
    private final Pattern maskPattern = Pattern.compile("(\\d{0,}|(2[AB]|[0-9]{2}))");

    public void mask(StringBuffer stringBuffer, String maskChar) {
        Matcher matcher =  findPattern.matcher(stringBuffer);
        if (matcher.find()) {
            System.out.println("stringBuffer = " + stringBuffer + ", maskChar = " + maskChar);
            String nss = matcher.group(1);
            String masked = RegExUtils.replaceAll(nss, maskPattern, maskChar);
            System.out.println("String nss = " + nss +", String masked "+ masked);
            int idx = stringBuffer.indexOf(nss);
            stringBuffer.replace(idx, idx + nss.length(), masked);
        }
    }
}
