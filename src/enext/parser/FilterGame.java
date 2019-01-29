
package enext.parser;

import enext.modelos.Kills;
import enext.modelos.Mortes;
import enext.modelos.Player;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
    
    /*
    * Filtra as informações das linha dos games
    */

public class FilterGame {

    //Começa passar pelas linhas dos filtros
    public static void filterGame(List<String> lines, Map<String, Player> players){
        for (String line : lines) {
            Matcher RegistroPlayer = Regex.encontrarSenteçaespecifica(line.trim(), "ClientUserinfoChanged");
            if (RegistroPlayer.matches()) {
                filterReg(RegistroPlayer, players);
            }
            Matcher Kill = Regex.encontrarSenteçaespecifica(line.trim(), "Kill");
            if (Kill.matches()) {
                filterKill(Kill, players);
            }
        }
    }

    //filtrar registros dos players
    private static void filterReg(Matcher registroPlayer, Map<String, Player> players){
        String player = registroPlayer.group(3).trim();
        String playerID = null;
        String playerName = null;

        if (player.length() > 0) {
            playerID = player.substring(0, 1);
            int initName = player.indexOf("n\\");
            int endName = player.indexOf("\\t\\");

            if (initName == 0 || endName == 0) {
                playerName = "";
            } else {
                playerName = player.substring(initName + 2, endName);
            }
        } else {
            playerID = "";
            playerName = "";
        }

        if (players.containsKey(playerID)) {
            Player antigo = players.get(playerID);

            if (!antigo.getName().equals(playerName)) {
                players.remove(playerID);
                antigo.setID("1000" + antigo.getID());
                players.put(antigo.getID(), antigo);
                players.put(playerID, new Player(playerID, playerName, new Kills()));
            }
        } else {
            players.put(playerID, new Player(playerID, playerName, new Kills()));
        }
    }

    //filtrar registros das mortes
    private static void filterKill(Matcher Kill, Map<String, Player> players) {
        String killsPlayer = Kill.group(3).trim();
        Matcher matcherKill = Regex.criarPadraoSentenca("([0-9]*)\\s([0-9]*)\\s([0-9]*)(.*)", killsPlayer);
        if (!matcherKill.matches()) {
            return;
        }
        String idKiller = matcherKill.group(1);
        String idKilled = matcherKill.group(2);
        String type = matcherKill.group(3);

        if (!idKiller.equals(idKilled)) {
            Player killer = players.get(idKiller);
            if (!(killer == null)) {
                killer.getKills().Kill();
                killer.getKills().KillValida();
            }
        }

        Player killed = players.get(idKilled);
        if (!(killed == null)) {
            killed.getKills().death();
            killed.getKills().IncrementaTipoMorte(Mortes.getType(Integer.valueOf(type)));
        }

        if (idKiller.equals("1022")) {
            Player KilledWorld = players.get(idKilled);
            if (!(KilledWorld == null)) {
                KilledWorld.getKills().killWorld();
            }
        }
    }
}