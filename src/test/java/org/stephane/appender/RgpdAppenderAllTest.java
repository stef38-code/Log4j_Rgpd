package org.stephane.appender;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Test Les différentes possibilités")
class RgpdAppenderAllTest {
    private RgpdAppender rgpdAppender;

    @BeforeEach
    void setUp() {
        rgpdAppender = RgpdAppender.newInstance();
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {
                    "leyenda45@vs-neustift.de numero secu 2 94 03 75 120 005 22,l*******5@v*********t.de numero secu * ** ** ** *** *** **",
                    "294037512000522 ezequiel.hickle@yahoo.com,*************** e********h****e@y***o.com"

            })
    @DisplayName("Test email et carte de credit")
    void mailAndNiss(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        rgpdAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }


}
