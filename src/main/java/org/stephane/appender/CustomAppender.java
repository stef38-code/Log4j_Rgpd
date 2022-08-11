package org.stephane.appender;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Plugin(name = "logmasker", category = PatternConverter.CATEGORY)
@ConverterKeys({"msk"})
public class CustomAppender extends LogEventPatternConverter {
    private static final List<LogMasker> OPTIONS_TO_MASKER = List.of(new EmailMasker(),new NssMasker());
    private CustomAppender(final String name, final String style) {
        super(name, style);
    }

    public static CustomAppender newInstance(final String[] options) {
        return new CustomAppender("mask", "mask");
    }

    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        StringBuffer logMessage = new StringBuffer(event.getMessage().getFormattedMessage());
        for (LogMasker masker:OPTIONS_TO_MASKER ) {
            masker.mask(logMessage, "*");
        }
        toAppendTo.append(logMessage);
    }

    private String getRequestId() {
       return "@@@";
    }
}
