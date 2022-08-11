package org.stephane.nss;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Nss {
    private static final Logger log = LogManager.getLogger(Nss.class);
    public void print(){
        log.info("numéro de sécurité 2 94 03 75 120 005 22");
        log.info("numéro de sécurité 294037512000522");
        log.info("We've just greeted the user!");
        log.warn("We've just greeted the user!");
        log.error("We've just greeted the user!");
        log.fatal("We've just greeted the user!");
    }
}
