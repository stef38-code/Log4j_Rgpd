package org.stephane.appender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShowTest {
    private static Logger logger = null;

    @BeforeEach
    public void setLogger() {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
        logger = LogManager.getLogger();
    }

    @Test
    void infoTest() {
        logger.info("Monsieur Dupond Gerard, téléphone: 01.23.45.67.89, numéro secu [294037512000522],carte Visa 4916 9006 6151 3107,IBAN FR7642559000011234567890121" );
        logger.info("Visa 4916 9006 6151 3107 , numero secu 294037512000522, Credit Cooperatif FR7642559000011234567890121, email ezequiel.hickle@yahoo.com");
        logger.info("cards 4609-3639-2287-9340 numero secu 194037512000522");
        logger.info("payment 4369559843305287, nss 2 94 03 2B 120 005 22, tel: 01.23.45.67.89");

    }
    @Test
    void debugTest() {
        logger.debug("azerty");
        logger.debug("Visa 4916 9006 6151 3107 , numero secu 294037512000522, Credit Cooperatif FR7642559000011234567890121, email ezequiel.hickle@yahoo.com");
        logger.debug("cards 4609-3639-2287-9340 numero secu 194037512000522");
        logger.debug("payment 4369559843305287, nss 2 94 03 2B 120 005 22");

    }
}
