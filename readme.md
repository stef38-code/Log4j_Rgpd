# RGPD
Ce projet à pour but de rendre anonyme certaines données contenu dans les logs.
Dans les projets java, il est souvent utilisé un logger de type Log4j

## Utilisation
Pour appliquer le système de filtre dit 'RGPD', il suffit d'ajouter dans les propriétés du fichier log4j2.xml
le masque %rgpd.
Exemple de fichier:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="info">
    <Properties>
        <Property name="pattern">-> %d %-5p [%c{1}] : %rgpd%n </Property>
    </Properties>
    <Appenders>
        <Console name="console" target="SYSTEM_OUT">
            <PatternLayout pattern="${pattern}" />
        </Console>
    </Appenders>
    <loggers>
        <Root level="info">
            <AppenderRef ref="console" />
        </Root>
    </loggers>
</Configuration>
```
## Execution
### Test
```java
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
}
```
### Résultat
```text
-> 2022-08-19 13:48:05,616 INFO  [ShowTest] : Monsieur Dupond Gerard, téléphone: **.**.**.**.**, numéro secu [***************],carte Visa **** **** **** ****,IBAN FR*************************
-> 2022-08-19 13:48:05,628 INFO  [ShowTest] : Visa **** **** **** **** , numero secu ***************, Credit Cooperatif FR*************************, email e********h****e@y***o.com
-> 2022-08-19 13:48:05,642 INFO  [ShowTest] : cards ****-****-****-**** numero secu ***************
-> 2022-08-19 13:48:05,644 INFO  [ShowTest] : payment ****************, nss * ** ** ** *** *** **, tel: **.**.**.**.**
```
## Création
### Un masque
L'ajout d'un nouveau masque est assez simple, il suffit de créer une classe qui implémente la classe LogMasker.
Puis de surcharger les méthodes `getFindPattern` et `getMaskPattern`.

Exemple:
```java
public class Iban implements LogMasker {
    @Override
    public Pattern getFindPattern() {
        return Pattern.compile(buildExpression());
    }

    @Override
    public Pattern getMaskPattern() {
        return Pattern.compile("(\\d)");
    }
    private String buildExpression(){
        return ConstRegexp.DEBUT +
                "(FR\\d{12}\\w{11}\\d{2})" +
                ConstRegexp.FIN;
    }
}
```
### Mise en place
La mise en place du nouveau masque se fait par l'ajout d'une nouvelle instance dans la classe `RgpdAppender` dans la liste `MASKERS`. 
