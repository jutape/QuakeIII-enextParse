
package enext.modelos;
/*
* Modelo para informações dos Players da partida
*/
public class Player {
    private String id;
    private String name;
    private Kills kills;
    
    public Player(String id, String name, Kills kills) {
        this.id = id;
        this.name = name;
        this.kills = kills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kills getKills() {
        return kills;
    }

    public void setKills(Kills kills) {
        this.kills = kills;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
}
