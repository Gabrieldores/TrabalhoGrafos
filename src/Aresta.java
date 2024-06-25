/*
 * Classe Aresta : armazena somente o peso da aresta, o vértice de inicio e vértice final
 */
public class Aresta <Tipo> {
    private Double peso;
    private Vertice<Tipo> inicio;
    private Vertice<Tipo> saida;

    /*
     * Método construtor
     */
    public Aresta(double peso, Vertice<Tipo> inicio, Vertice<Tipo> saida){
        this.peso = peso;
        this.inicio = inicio;
        this.saida = saida;
    }

    /*
     * Método get para pegar o peso da aresta
     */
    public Double getPeso() {
        return peso;
    }
    /*
     * Método set para alterar o peso da aresta
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    /*
     * método get para pegar o vértice de ínicio
     */
    public Vertice<Tipo> getInicio() {
        return inicio;
    }
    /*
     * Método set para alteração do vértice de ínicio
     */
    public void setInicio(Vertice<Tipo> inicio) {
        this.inicio = inicio;
    }
    /*
     * Método get para pegar a aresta de saída
     */
    public Vertice<Tipo> getSaida() {
        return saida;
    }
    /*
     * Método set para alterar a aresta de saída
     */
    public void setSaida(Vertice<Tipo> saida) {
        this.saida = saida;
    }

}
