
package enext.parser;

import enext.modelos.LinesGame;
import enext.modelos.Game;
import enext.modelos.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    /*
    *Divide games por linhas
    */
public class ParserLines {
    private List<String> lines;

    public ParserLines (List<String> linhas) {
        this.lines = linhas; 
    }
    
    public List<Game> parserGames() {
        List<LinesGame> linesGame = parserLines();
        List<Game> games = new ArrayList<>();

        for(LinesGame lineGame : linesGame) {
            games.add(parserLinesGame(lineGame));
        }  
        return games;
    }

    //divide linhas de cada game e cria o game
    List<LinesGame> parserLines() {
        List<LinesGame> games = new ArrayList<>();
        int Partidas = 0;
        String LastLine = lines.get(lines.size() - 1);
        LinesGame game = new LinesGame();

        for (String linha : lines) {
            boolean InitGame = Regex.Encontrar(linha.trim(), "InitGame");
            if (InitGame) {
                if (Partidas > 0) {
                    games.add(game);
                    game = new LinesGame();
                }
                Partidas++;
                game.setName("game: " + Partidas);
            } else {
                game.addLine(linha);
            }

            if (linha.equals(LastLine)) {
                games.add(game);
            }
        }
        return games;
    }

    //Chama o filtro dos restante das informações
    private Game parserLinesGame(LinesGame game) {
        List<String> linhas = game.getLines();
        String nome = game.getNome();
        Map<String, Player> mapPlayers = new HashMap<>();
        FilterGame.filterGame(linhas, mapPlayers); 
        List<Player> players = new ArrayList<>(mapPlayers.values());
        
        return new Game(nome, players);
    }
}
