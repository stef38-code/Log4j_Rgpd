package org.stephane.appender;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.stephane.appender.mask.*;

import java.util.List;

@Plugin(name = "logmasker", category = PatternConverter.CATEGORY)
@ConverterKeys({"rgpd"})
@PerformanceSensitive("allocation")
public class RgpdAppender extends LogEventPatternConverter {
    private static final List<LogMasker> MASKERS = List.of(
            new Email(),
            new Nss(),
            new CreditCards(),
            new Iban(),
            new Telephone()

    );

    private RgpdAppender(final String name, final String style) {
        super(name, style);
    }

    public static RgpdAppender newInstance() {
        return new RgpdAppender("rgpd", "rgpd");
    }

    @Override
    public void format(final LogEvent event, final StringBuilder toAppendTo) {
        StringBuilder logMessage = new StringBuilder(event.getMessage().getFormattedMessage());
        for (LogMasker masker : MASKERS) {
            masker.mask(logMessage, "*");
        }
        toAppendTo.append(logMessage);
    }

}
