package enext.parser;

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

}