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

@DisplayName("Test numéro carte credit")
class CustomAppenderCreditCardsTest {
    private CustomAppender customAppender;

    @BeforeEach
    void setUp() {
        customAppender = CustomAppender.newInstance();
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {
                    "4916 9006 6151 3107,**** **** **** ****",
                    "4609-3639-2287-9340,****-****-****-****",
                    "4369559843305287,****************",
                    "Visa 4916 9006 6151 3107,Visa **** **** **** ****",
                    "cards 4609-3639-2287-9340,cards ****-****-****-****",
                    "payment 4369559843305287,payment ****************"

            })
    void visa(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        customAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {
                    "5404 3664 0602 4840,**** **** **** ****",
                    "294037512000522,***************",
                    "194037512000522,***************",
                    "Master 5404 3664 0602 4840,Master **** **** **** ****",
                    "Cards 5404-3658-7741-3532,Cards ****-****-****-****",
                    "payment 5404363072774782,payment ****************"

            })
    void master(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        customAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {

                    "6404 3664 0602 4840,**** **** **** ****",
                    "9404 3664 0602 4840,**** **** **** ****",
                    "9404-3658-7741-3532,****-****-****-****",
                    "6404-3658-7741-3532,****-****-****-****",
                    "6404363072774782,****************",
                    "9404363072774782,****************",
                    "Prostir 6404 3664 0602 4840,Prostir **** **** **** ****",
                    "Prostir 9404 3664 0602 4840,Prostir **** **** **** ****",
                    "Cards 6404-3658-7741-3532,Cards ****-****-****-****",
                    "Cards 9404-3658-7741-3532,Cards ****-****-****-****",
                    "payment 6404363072774782,payment ****************",
                    "payment 9404363072774782,payment ****************"

            })
    void prostir(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        customAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {
                    "2404 3664 0602 4840,**** **** **** ****",
                    "2404-3658-7741-3532,****-****-****-****",
                    "9404363072774782,****************",
                    "Mir 2404 3664 0602 4840,Mir **** **** **** ****",
                    "Cards 2404-3658-7741-3532,Cards ****-****-****-****",
                    "payment 2404363072774782,payment ****************"
            })
    void mir(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        customAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }
}
