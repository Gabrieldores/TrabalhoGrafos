import java.util.ArrayList;

public class Vertice <Tipo> {
    private Tipo dado;
    private ArrayList <Aresta<Tipo>> arestasEntrada;
    private ArrayList <Aresta<Tipo>> arestaSaida;

    
    public Vertice(Tipo dado){
        this.dado = dado;
        this.arestasEntrada = new ArrayList<Aresta<Tipo>>();
        this.arestaSaida = new ArrayList<Aresta<Tipo>>();
    }

    public Tipo getDado() {
        return dado;
    }

    public void setDado(Tipo dado) {
        this.dado = dado;
    }

    public void AdicionarArestaEntrada(Aresta<Tipo> aresta){
        this.arestasEntrada.add(aresta);

    }
    public void AdicionarArestaSaida(Aresta<Tipo> aresta){
        this.arestaSaida.add(aresta);

    }
    
}
