
package enext.modelos;

import java.util.HashMap;
import java.util.Map;

/*
* Informações das mortes no jogo
*/

public class Kills {
    private Integer KillsValidas;
    private Integer TotalKills;
    private Integer TotalDeaths;
    private Map<Mortes, Integer> mortesTipo;
    
    public Kills() {
        KillsValidas = 0;
        TotalKills = 0;
        TotalDeaths = 0;
        mortesTipo = new HashMap<>();
    }

    public void setKillsValidas(Integer KillsValidas) {
        this.KillsValidas = KillsValidas;
    }

    public Integer getKillsValidas() {
        return KillsValidas;
    }

    public void setTotalKills(Integer TotalKills) {
        this.TotalKills = TotalKills;
    }

    public Integer getTotalKills() {
        return TotalKills;
    }

    public void setTotalDeaths(Integer TotalDeaths) {
        this.TotalDeaths = TotalDeaths;
    }

    public Integer getTotalDeaths() {
        return TotalDeaths;
    }

    public void KillValida() {
        KillsValidas++;
    }

    public void Kill() {
        TotalKills++;
    }

    public void death() {
        TotalDeaths++;
    }

    public void killWorld() {
        KillsValidas--;
    }

    public Map<Mortes, Integer> getMortes() {
        return mortesTipo;
    }

    public void IncrementaTipoMorte(Mortes tipo) {
        if (!mortesTipo.containsKey(tipo)) {
            mortesTipo.put(tipo, 1);
        } else {
            Integer x = mortesTipo.get(tipo);
            mortesTipo.put(tipo, x + 1);
        }
    }
}
