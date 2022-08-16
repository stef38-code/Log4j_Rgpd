package org.stephane.appender;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import org.stephane.appender.mask.*;

import java.util.List;

@Plugin(name = "logmasker", category = PatternConverter.CATEGORY)
@ConverterKeys({"msk"})
public class CustomAppender extends LogEventPatternConverter {
    private static final List<LogMasker> OPTIONS_TO_MASKER = List.of(
            new EmailMasker(),
            new NssMasker(),
            new CreditCardsMasker(),
            new IbanMasker()
    );

    private CustomAppender(final String name, final String style) {
        super(name, style);
    }

    public static CustomAppender newInstance() {
        return new CustomAppender("mask", "mask");
    }

    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        StringBuilder logMessage = new StringBuilder(event.getMessage().getFormattedMessage());
        for (LogMasker masker : OPTIONS_TO_MASKER) {
            masker.mask(logMessage, "*");
        }
        toAppendTo.append(logMessage);
    }

}
