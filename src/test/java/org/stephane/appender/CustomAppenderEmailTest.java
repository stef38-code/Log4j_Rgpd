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
class CustomAppenderEmailTest {
    private CustomAppender customAppender;

    @BeforeEach
    void setUp() {
        customAppender = CustomAppender.newInstance();
    }

    @ParameterizedTest(name = "${0} devient ${1}")
    @CsvSource(
            {
                    "leyenda45@vs-neustift.de,l*******5@v*********t.de",
                    "joel.haley@yahoo.com,j********y@y***o.com",
                    "eliseo.quitzon@hotmail.com,e************n@h*****l.com",
                    "broderick.oberbrunner@gmail.com,b*******************r@g***l.com",
                    "ezequiel.hickle@yahoo.com,e*************e@y***o.com",
                    "maddie.sipes@hotmail.com,m**********s@h*****l.com"
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
