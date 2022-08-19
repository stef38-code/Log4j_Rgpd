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
@DisplayName("Test adresse email")
class RgpdAppenderEmailTest {
    private RgpdAppender rgpdAppender;

    @BeforeEach
    void setUp() {
        rgpdAppender = RgpdAppender.newInstance();
    }

    @ParameterizedTest(name = "${0} devient ${1}")
    @CsvSource(
            {
                    "leyenda45@vs-neustift.de,l*******5@v*********t.de",
                    "joel.haley@yahoo.com,j****h***y@y***o.com",
                    "eliseo.quitzon@hotmail.com,e******q*****n@h*****l.com",
                    "broderick.oberbrunner@gmail.com,b*********o*********r@g***l.com",
                    "ezequiel.hickle@yahoo.com,e********h****e@y***o.com",
                    " ezequiel.hickle@yahoo.com, e********h****e@y***o.com",
                    "121 ezequiel.hickle@yahoo.com,121 e********h****e@y***o.com",
                    "121 maddie.sipes@hotmail.com,121 m******s***s@h*****l.com"
            })
    void format(String email, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(email)) //
                .build();
        rgpdAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }
}
