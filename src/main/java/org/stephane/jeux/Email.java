package org.stephane.jeux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Email {
    private static final Logger log = LogManager.getLogger(Email.class);

    public void print() {
        log.info("leyenda45@vs-neustift.de");
        log.info("joel.haley@yahoo.com");
        log.info("eliseo.quitzon@hotmail.com");
        log.warn("broderick.oberbrunner@gmail.com");
        log.error("ezequiel.hickle@yahoo.com");
        log.fatal("maddie.sipes@hotmail.com");
    }
}
