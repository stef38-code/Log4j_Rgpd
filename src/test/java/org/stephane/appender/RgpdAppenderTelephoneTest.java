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

@DisplayName("Test numéro de téléphone")
class RgpdAppenderTelephoneTest {
    private RgpdAppender rgpdAppender;

    @BeforeEach
    void setUp() {
        rgpdAppender = RgpdAppender.newInstance();
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {
                    "0123456789,**********",
                    "numero de telephone : 0123456789,numero de telephone : **********",
                    "numero de telephone :0123456789,numero de telephone :**********",
                    "numero de telephone -> 01 23 45 67 89,numero de telephone -> ** ** ** ** **",
                    "01.23.45.67.89,**.**.**.**.**",
                    "01-23-45-67-89,**-**-**-**-**",
                    "0123 45.67.89,**** **.**.**",
                    "0033 123-456-789,**** ***-***-***",
                    "+33-1.23.45.67.89,+**-*.**.**.**.**",
                    "+33 - 123 456 789,+** - *** *** ***",
                    "+33(0) 123 456 789,+**(*) *** *** ***",
                    "+33 (0)123 45 67 89,+** (*)*** ** ** **",
                    "+33 (0)1 2345-6789,+** (*)* ****-****"

            })
    void telephone(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        rgpdAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }


}
