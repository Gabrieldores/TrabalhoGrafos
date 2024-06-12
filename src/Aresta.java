public class Aresta <Tipo> {
    private Double peso;
    private Vertice<Tipo> inicio;
    private Vertice<Tipo> saida;

    public Aresta(double peso, Vertice<Tipo> inicio, Vertice<Tipo> saida){
        this.peso = peso;
        this.inicio = inicio;
        this.saida = saida;
    }


    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public Vertice<Tipo> getInicio() {
        return inicio;
    }
    public void setInicio(Vertice<Tipo> inicio) {
        this.inicio = inicio;
    }
    public Vertice<Tipo> getSaida() {
        return saida;
    }
    public void setSaida(Vertice<Tipo> saida) {
        this.saida = saida;
    }

}
