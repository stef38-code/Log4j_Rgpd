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

@DisplayName("Test numéro sécurité social")
class CustomAppenderNssTest {
    private CustomAppender customAppender;

    @BeforeEach
    void setUp() {
        customAppender = CustomAppender.newInstance();
    }

    @ParameterizedTest(name = "${0} devient ${1}")
    @CsvSource(
            {
                    "numero secu 2 94 03 75 120 005 22,numero secu * ** ** ** *** *** **",
                    "numero secu 2 94 03 2B 120 005 22,numero secu * ** ** ** *** *** **",
                    "2 94 03 2A 120 005 22,* ** ** ** *** *** **",
                    "numero secu 294037512000522,numero secu ***************",
                    "294037512000522,***************",
                    "194037512000522,***************",
                    "numero secu 294037512000522,numero secu ***************",
                    "numero secu 194037512000522,numero secu ***************",
                    "numero secu:194037512000522,numero secu:***************",
                    "numero secu[194037512000522],numero secu[***************]",
                    "numero secu 19403751AA00522,numero secu 19403751AA00522"
            })
    void format(String email, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(email)) //
                .build();
        customAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }
}
