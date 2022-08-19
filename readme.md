# RGPD
Ce projet à pour but de rendre anonyme certaines données contenu dans les logs.
Dans les projets java, il est souvent utilisé un logger de type Log4j

## les transformations
### Email

|          Entrée           |          Sortie           |
|:-------------------------:|:-------------------------:|
| leyenda45@vs-neustift.de  | l*******5@v*********t.de  |


## Numéro de sécurité social

|          Entrée           |          Sortie           |
|:-------------------------:|:-------------------------:|
| 2 94 03 75 120 005 22  | * ** ** ** *** *** **  |
|294037512000522|***************|

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
## Création
### Un masque
L'ajout d'un nouveau masque est assez simple, il suffit de créer une classe qui implémente la classe LogMasker.
Puis de surcharger les méthodes `getFindPattern` et `getMaskPattern`.

Exemple:
```java
public class IbanMasker implements LogMasker {
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
