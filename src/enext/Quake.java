
package enext;

import enext.parser.ParserLines;
import enext.modelos.Game;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Quake {

    public static void main(String[] args) throws Exception {
        List<String> linhas;
        final String diretorio = new Scanner (System.in).next();
        
        boolean arquivoExiste = Files.exists(Paths.get(diretorio));
        
        if(arquivoExiste){
            try {
		linhas = Files.readAllLines(Paths.get(diretorio));
                
                ParserLines game = new ParserLines(linhas);
		List<Game> games = game.parserGames();
               
                
            }
            catch (IOException e) {
		System.out.println("Erro: Arquivo não é arquivo de possivel leitura");
		throw new Exception (e.getMessage());
            }
        }else{
            System.out.println("Erro: Diretório não encontrado");
        }
    }
    
}
