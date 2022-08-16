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

@DisplayName("Test Iban")
class CustomAppenderIbanTest {
    private CustomAppender customAppender;

    @BeforeEach
    void setUp() {
        customAppender = CustomAppender.newInstance();
    }

    @ParameterizedTest(name = "{0} devient {1}")
    @CsvSource(
            {
                    //Banque de France
                    "FR7630001007941234567890185,FR*************************",
                    //BNP Paribas
                    "FR7630004000031234567890143,FR*************************",
                    //Crédit Agricole
                    "FR7630006000011234567890189,FR*************************",
                    //Banque Populaire
                    "FR7610107001011234567890129,FR*************************",
                    //Caisse d'Epargne
                    "FR7611315000011234567890138,***************************",
                    //Crédit Lyonnais (LCL)
                    "FR7630002032531234567890168,***************************",
                    //HSBC
                    "FR7630056009271234567890182,***************************",
                    //Crédit Mutuel
                    "FR7611808009101234567890147,***************************",
                    //La Banque Postale
                    "FR7610011000201234567890188,***************************",
                    //Crédit du Nord
                    "FR7630076020821234567890186,***************************",
                    //Dexia Banque
                    "FR7614410000011234567890163,***************************",
                    //AXA Banque
                    "FR7612548029981234567890161,***************************",
                    //Natixis Banque
                    "FR7630007000111234567890144,***************************",
                    //Credit Cooperatif
                    "FR7642559000011234567890121,***************************",
                    //Crédit Industriel et Commercial
                    "FR7641199110541234567890180,***************************"
            })
    void iban(String actual, String except) {
        StringBuilder stringBuilder = new StringBuilder();
        LogEvent event = Log4jLogEvent.newBuilder() //
                .setLevel(Level.DEBUG) //
                .setMessage(new SimpleMessage(actual)) //
                .build();
        customAppender.format(event, stringBuilder);
        Assertions.assertThat(stringBuilder).isNotNull().hasToString(except);
    }

}
