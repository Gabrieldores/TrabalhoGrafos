import java.util.ArrayList;

public class Vertice <Tipo> {
    private Tipo dado;
    private ArrayList <Aresta<Tipo>> arestasEntrada;
    private ArrayList <Aresta<Tipo>> arestaSaida;


    public void setArestasEntrada(ArrayList<Aresta<Tipo>> arestasEntrada) {
        this.arestasEntrada = arestasEntrada;
    }



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
    public ArrayList<Aresta<Tipo>> getArestasSaida() {
        return arestaSaida;
    }
    public ArrayList<Aresta<Tipo>> getArestasEntrada() {
        return arestasEntrada;
    }


    public ArrayList<Aresta<Tipo>> getArestasAdjacentes(Vertice<Tipo> vertice) {
        ArrayList<Aresta<Tipo>> arestasAdjacentes = new ArrayList<>();

        // Adiciona as arestas de saída do vértice especificado
        if(this.equals(vertice))
        {
        for (int i = 0; i < vertice.getArestasSaida().size(); i++) {
            Aresta<Tipo> aresta = vertice.getArestasSaida().get(i);
            if (aresta.getInicio().equals(vertice)) {
                arestasAdjacentes.add(aresta);
            }
        }

        // Adiciona as arestas de entrada do vértice especificado
        for (int i = 0; i < vertice.getArestasEntrada().size(); i++) {
            Aresta<Tipo> aresta = vertice.getArestasEntrada().get(i);
            if (aresta.getSaida().equals(vertice)) {
                arestasAdjacentes.add(aresta);
            }
        }

    }

        return arestasAdjacentes;
    }

    public ArrayList<Vertice<Tipo>> getVerticesAdjacentes(Vertice<Tipo> vertice) {
        ArrayList<Vertice<Tipo>> verticesAdjacentes = new ArrayList<>();


        if (this.equals(vertice)) {
            // Adiciona vértices de saída das arestas de saída
            for (int i = 0; i < arestaSaida.size(); i++) {
                Aresta<Tipo> aresta = arestaSaida.get(i);
                verticesAdjacentes.add(aresta.getSaida());
            }

            // Adiciona vértices de entrada das arestas de entrada
            for (int j = 0; j < arestasEntrada.size(); j++) {
                Aresta<Tipo> aresta = arestasEntrada.get(j);
                verticesAdjacentes.add(aresta.getInicio());
            }
        }

        return verticesAdjacentes;
    }

    public ArrayList<Vertice<Tipo>> getVerticesIncidentes(Vertice<Tipo> vertice) {
        ArrayList<Vertice<Tipo>> verticesIncidentes = new ArrayList<>();

        // Adiciona os vértices de início das arestas de entrada
        for (Aresta<Tipo> aresta : arestasEntrada) {
            verticesIncidentes.add(aresta.getInicio());
        }

        // Adiciona os vértices de saída das arestas de saída
        for (Aresta<Tipo> aresta : arestaSaida) {
            verticesIncidentes.add(aresta.getSaida());
        }

        return verticesIncidentes;

    }

    public ArrayList<Aresta<Tipo>> getArestasIncidentes (Vertice<Tipo>Vertice) {
        ArrayList<Aresta<Tipo>> arestasIncidentes = new ArrayList<>();

        // Adiciona as arestas de entrada
        arestasIncidentes.addAll(arestasEntrada);

        // Adiciona as arestas de saída
        arestasIncidentes.addAll(arestaSaida);

        return arestasIncidentes;
    }

    public int grau()
    {
        return arestaSaida.size() + arestasEntrada.size();
    }

}

