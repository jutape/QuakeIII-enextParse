package enext;

import enext.parser.ParserLines;
import enext.modelos.Game;
import enext.modelos.Mortes;
import enext.modelos.Player;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Quake {

    /*
    * Acessando arquivo e chamando ParseLines para separa os games por linha
     */
    public static void main(String[] args) throws Exception {
        List<String> linhas;
        final String diretorio = new Scanner(System.in).next();

        boolean arquivoExiste = Files.exists(Paths.get(diretorio));

        if (arquivoExiste) {
            try {
                linhas = Files.readAllLines(Paths.get(diretorio));

                ParserLines game = new ParserLines(linhas);
                List<Game> games = game.parserGames();
                
                printGames(games);

            } catch (IOException e) {
                System.out.println("Erro: Arquivo não é arquivo de possivel leitura");
                throw new Exception(e.getMessage());
            }
        } else {
            System.out.println("Erro: Diretório não encontrado");
        }
    }

    public static void printGames(List<Game> games) {
        for (Game game : games) {
            System.out.println(game.getName() + " {");
            int totalKills = 0;
            for (Player player : game.getPlayers()) {
                totalKills = totalKills + player.getKills().getTotalDeaths();
            }
            System.out.println("Total_Kills: " + totalKills);
            System.out.print("players: [");
            for (Player player : game.getPlayers()) {
                System.out.print("'" + player.getName() + "', ");
            }
            System.out.print("]");
            System.out.println("");
            System.out.println("Kills: {");
            for (Player player : game.getPlayers()) {
                System.out.println(player.getName() + " : " + player.getKills().getKillsValidas());
            }
            System.out.println("  }");

            Map<Mortes, Integer> tipoMorte = new HashMap<>();

            for (Player player : game.getPlayers()) {
                Map<Mortes, Integer> tiposDeMortePlayer = player.getKills().getMortes();

                for (Entry<Mortes, Integer> entry : tiposDeMortePlayer.entrySet()) {
                    Mortes tipoDeMorte = entry.getKey();
                    Integer Total = entry.getValue();

                    if (!tipoMorte.containsKey(tipoDeMorte)) {
                        tipoMorte.put(tipoDeMorte, 0);
                    }
                    tipoMorte.put(tipoDeMorte, tipoMorte.get(tipoDeMorte) + Total);
                }
            }
            Iterator<Entry<Mortes, Integer>> iteratorTiposDeMorte = tipoMorte.entrySet().iterator();
            System.out.println("kills_by_means: {");
            while (iteratorTiposDeMorte.hasNext()) {
                Entry<Mortes, Integer> totalTipoDeMorte = iteratorTiposDeMorte.next();
                Mortes tipoDeMorte = totalTipoDeMorte.getKey();
                Integer total = totalTipoDeMorte.getValue();
                System.out.println
        (tipoDeMorte + ": " + total);
            }
            System.out.println("  }");
        }
        System.out.println("}");
    }
}
