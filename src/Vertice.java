/*
 * Classe Vértice: Classe qie armazena as informações de um vértice
 * como suas arestas de entrada e ínicio e o dado vinculado ao nó.
 * Presença de método para manipulação das listas para encontrar arestas, vértices
 * tanto adjascentes quanto incidentes
 */
import java.util.ArrayList; //Classe para manipulação de listas em java

public class Vertice <Tipo> {
    private Tipo dado;
    private ArrayList <Aresta<Tipo>> arestasEntrada;
    private ArrayList <Aresta<Tipo>> arestaSaida;

    
    /*
     * Método constrtutor, cria uma lista de arestas com arestas de saida e entrada
     */
    public Vertice(Tipo dado){
        this.dado = dado;
        this.arestasEntrada = new ArrayList<Aresta<Tipo>>();
        this.arestaSaida = new ArrayList<Aresta<Tipo>>();
    }

    public Tipo getDado() {
        return dado;
    }

    /*
     * Método para alteração da lista de arestas de entrada
     */
    public void setArestasEntrada(ArrayList<Aresta<Tipo>> arestasEntrada) {
        this.arestasEntrada = arestasEntrada;
    }

    /*
     * Altera o dado vinculado ao vértice
     */
    public void setDado(Tipo dado) {
        this.dado = dado;
    }

    /*
     * Adiciona arestas de entrada a nossa lista de arestas de entrada do vértice
     */
    public void AdicionarArestaEntrada(Aresta<Tipo> aresta){
        this.arestasEntrada.add(aresta);

    /*
     * Adiciona arestas de saida a nossa lista de arestas de saída
     */
    }
    public void AdicionarArestaSaida(Aresta<Tipo> aresta){
        this.arestaSaida.add(aresta);

    }
    /*
     * Método get para pegar a nossa lista de arestas de saída
     */
    public ArrayList<Aresta<Tipo>> getArestasSaida() {
        return arestaSaida;
    }
    /*
     * Método get para pegar a nossa lista de arestas de entrada o vértice
     */
    public ArrayList<Aresta<Tipo>> getArestasEntrada() {
        return arestasEntrada;
    }

    /*
     * Método getArestas Adjascentes que criar uma lista de arestas adjascentes
     * ércorre a nossa lista de arestas de saida e pergunta se o vertice de inicio do vértice desejado
     * ou seja a aresta na qual e o fim de outro vertice, adicionamos ele a lista de adjascentes
     */
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
    /*
     * VérticesAdjascentes, retira os vértices adjascentes de um grafo, nele se cria uma lista
     * de vértices adjascentes vazia, depois por uma variável i, Busca vértices que pegando a nossa aresta de saída
     * correspondem ao mesmo ao outro de saida
     */
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

    /*
     * Métoodo que pega vértices incidentes que cria uma lista de vértices adjascentes
     * e adiciona vértices de inicio as nossas areas de entrada e saida
     */
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
    /*
     * Arestas incidentes cria uma lista em que se adiciona as arestas de entrada de um vértice
     * como também as duas arestas de saída
     */
    public ArrayList<Aresta<Tipo>> getArestasIncidentes (Vertice<Tipo>Vertice) {
        ArrayList<Aresta<Tipo>> arestasIncidentes = new ArrayList<>();

        // Adiciona as arestas de entrada
        arestasIncidentes.addAll(arestasEntrada);

        // Adiciona as arestas de saída
        arestasIncidentes.addAll(arestaSaida);

        return arestasIncidentes;
    }

     /*
      * Método somente para a retirada de um grau de um vértice somando o tamanho de suas duas listas
      * de arestas de saída e entrada
      */
    public int grau()
    {
        return arestaSaida.size() + arestasEntrada.size();
    }

}

