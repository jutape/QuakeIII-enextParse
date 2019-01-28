package enext.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
* Regex para pesquisa no texto:
*/
public class Regex {
    public static boolean Encontrar (String contem, String contida) {
        return modeloLog(contida).matcher(contem).matches();
    }

    private static Pattern modeloLog(final String value) {
    return Pattern.compile("([0-9]*[0-9]:[0-5][0-9])" + "\\s(" + value + ":)(.*?)");
    }
    public static Matcher encontrarSenteçaespecifica(final String sentence, final String value) {
        return modeloLog(value).matcher(sentence);
    }

    public static Matcher criarPadraoSentenca(final String pattern, final String sentence) {
        return Pattern.compile(pattern).matcher(sentence);
    }

}