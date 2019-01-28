
package enext.modelos;

import java.util.ArrayList;
import java.util.List;

/*
* Modelo de das Linhas do game:
*/

public class LinesGame {
    private String nome;
    private List<String> linhas;
    
    public LinesGame(){
	this.linhas = new ArrayList<> ();
    }
    
    public String getNome(){
        return nome;
    }
    
    public List<String> getLines() {
        return linhas;
    }
    
    public void setName(String nome){
        this.nome = nome;
    }
    
    public void setLines(List<String> linhas){
        this.linhas = linhas;
    }
    
    public void addLine(String linha){
        linhas.add(linha);
    }
}
