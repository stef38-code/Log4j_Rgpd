package org.stephane.appender.mask;

import org.apache.commons.lang3.RegExUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LogMasker {
    public abstract Pattern getFindPattern();
    public abstract Pattern getMaskPattern();

    public void mask(StringBuilder stringBuffer, String maskChar) {
        Matcher matcher = getFindPattern().matcher(stringBuffer);
        if (matcher.find()) {
            String group = matcher.group(0);
            String masked = RegExUtils.replaceAll(group, getMaskPattern(), maskChar);
            int idx = stringBuffer.indexOf(group);
            stringBuffer.replace(idx, idx + group.length(), masked);
        }
    }
}
