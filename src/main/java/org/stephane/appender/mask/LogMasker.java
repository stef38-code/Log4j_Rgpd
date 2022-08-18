package org.stephane.appender.mask;

import org.apache.commons.lang3.RegExUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe de base pour la définition des masques
 */
public interface LogMasker {
    /**
     * Getter du pattern de recherche
     *
     * @return Pattern
     */
    public Pattern getFindPattern();

    /**
     * Getter pattern de remplacement
     *
     * @return Pattern
     */
    public Pattern getMaskPattern();

    public default void mask(StringBuilder stringBuffer, String maskChar) {
        Matcher matcher = getFindPattern().matcher(stringBuffer);
        if (matcher.find()) {
/*            System.out.println(this.getClass().getCanonicalName());
            System.out.println("stringBuffer = " + stringBuffer + ", maskChar = " + maskChar);*/
            String group = matcher.group(0);
            String masked = RegExUtils.replaceAll(group, getMaskPattern(), maskChar);
            int idx = stringBuffer.indexOf(group);
            stringBuffer.replace(idx, idx + group.length(), masked);
        }
    }
}
