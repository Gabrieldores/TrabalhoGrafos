/*
 * CLASSE GRAFO:
 * Tem as duas listas de vértices e arestas
 * Todos os metodos acionados para manipulação de um grafo
 */

import java.util.ArrayDeque; // Interface que dá suporte para arrays redimensionáveis
import java.util.ArrayList; // Para trabalhar com listas no código
import java.util.Arrays; // classe com métodos para manipular arrays
import java.util.Collections; // Classe genérica de coleções
import java.util.Comparator; // Classe chave para comparação de objetos
import java.util.Deque; //Combina fila e pilha
import java.util.HashMap;//Trabalhar com tabelas hash
import java.util.List;//Trabalhar com listas
import java.util.Map;//Trabalhar com o tipo map em java
import java.util.PriorityQueue; //Definir prioridades em nossa fila
import java.util.Scanner; //Método Scanner para ler o teclado e o documento txt
import java.util.Stack; // Extensão da classe vector


public class Grafo <Tipo> {
    private ArrayList<Vertice<Tipo>> vertices;
    private ArrayList<Aresta<Tipo>> arestas;

    /*
 * Método construtor que recebe um nome genérico Tipo que este tipo definirá qual será o objeto dos
 * vértices e das arestas, inicializa nosso grafo com uma lista de vértices e arestas
 */
    public Grafo(){
        this.vertices = new ArrayList<Vertice<Tipo>>();
        this.arestas = new ArrayList<Aresta<Tipo>>();
    }

    /*
     * Método feito para adicionar vértices, com seu dado que será o valor contido no nó, será
     * anexado na lista de vértices
     */
    public void adicionarVertice(Tipo dado){
        Vertice<Tipo> novoVertice = new Vertice<Tipo>(dado);
        this.vertices.add(novoVertice);
    }

    /*
     * Método adicionarAresta que recebe o peso da aresta e seus vértices de inicio e saida, adiconando
     * este objeto na nossa lista de arestas, tendo na lista arestas de saida e entrada
     */
    public void adicionarAresta(Double peso, Tipo DadoInicio, Tipo DadoSaida){
        Vertice<Tipo> inicio = this.getVertice(DadoInicio);
        Vertice<Tipo> fim = this.getVertice(DadoSaida);
        Aresta<Tipo> aresta = new Aresta<Tipo>(peso, inicio, fim);
        inicio.AdicionarArestaSaida(aresta);
        fim.AdicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    /*
     * Método que percorre por nossa lsita de vértices e retira o vértice desejado da lista
     * pelo usuário
     */
    public Vertice<Tipo> getVertice(Tipo dado){
        Vertice<Tipo> vertice = null;
        for(int i = 0; i < this.vertices.size(); i ++)
        {
            if(this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
            }
        }
        return vertice;
    } 

    /*
     * Método feito para criar grafo, usando a classe grafo, O usuário como parametro preenche o 
     * número de vértices desejados, preenche os valores chamando o método adicionar vértice
     * após esta ação e chamado o método para adicionar arestas
     */
    public static <Tipo> void CriarGrafo(Grafo<Tipo> grafo) {
        Scanner sc = new Scanner(System.in);
        String texto;

        System.out.println("Quantos vértices no grafo você quer adicionar?");
        int NumVertices = sc.nextInt();
        sc.nextLine(); // Limpar o buffer após a leitura do número inteiro

        System.out.println("Preencha o valor dos vértices:");
        for (int i = 0; i < NumVertices; i++) {
            texto = sc.nextLine();
            grafo.adicionarVertice((Tipo) texto);
        }

        System.out.println("Quantas arestas você quer adicionar?");
        int NumArestas = sc.nextInt();
        sc.nextLine(); // Limpar o buffer após a leitura do número inteiro

        System.out.println("Preencha o valor das arestas:");
        for (int j = 0; j < NumArestas; j++) {
            System.out.print("Origem: ");
            String origem = sc.nextLine();
            System.out.print("Saída: ");
            String saida = sc.nextLine();
            System.out.print("Peso: ");
            double peso = sc.nextDouble();
            sc.nextLine(); // Limpar o buffer após a leitura do número decimal

            grafo.adicionarAresta(peso, (Tipo) origem, (Tipo) saida);
        }

        System.out.println("Grafo criado!!!");
    }

    /*
     * Tendo como paramêtro o grafo que foi lido
     * Método imprimirGrafo que primeiro verifica se o grafo ele é denso, dependendo de sua densidade
     * é chamado ou imprimir a matriz de adjascencia ou a lista de adjascencia
     */
    public void imprimirGrafo(Grafo<Tipo> grafo) {
        int vertice = this.vertices.size();
        int arestas = this.arestas.size(); 

        int numMaxArestas = (vertice * (vertice - 1)) / 2;

        boolean isDenso = arestas > numMaxArestas / 2;

        if(isDenso) {
            System.out.println("O grafo é denso");
            imprimirMatrizAdjacencia();
        }else{
            System.out.println("O grafo é denso");
            Map<Tipo, ArrayList<Tipo>> listasAdjacencia = grafo.criarListasAdjacencia();
            System.out.println("Listas de Adjacência:");
            for (Map.Entry<Tipo, ArrayList<Tipo>> entry : listasAdjacencia.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }

    }
    /*
     * Método para impressão da matriz de adjascencia recebendo um valor inteiro tamanho
     * sendo o número de elementos da lista de vértices, formando uma matriz quadŕatica
     */
    public void imprimirMatrizAdjacencia() {
        int tamanho = this.vertices.size();
        int[][] matrizAdjacencia = new int[tamanho][tamanho];

        // Preenchendo a matriz de adjacência com 0s
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matrizAdjacencia[i][j] = 0;
            }
        }

        // Preenchendo a matriz de adjacência com 1s onde houver arestas
        for (Aresta<Tipo> aresta : this.arestas) {
            int indiceInicio = this.vertices.indexOf(aresta.getInicio());
            int indiceSaida = this.vertices.indexOf(aresta.getSaida());
            matrizAdjacencia[indiceInicio][indiceSaida] = 1;
            matrizAdjacencia[indiceSaida][indiceInicio] = 1; // Para grafo não direcionado
        }

        // Imprimindo a matriz de adjacência
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*
     * Método que retorna um map que nele contem a nossa lista de vértices,percorrendo por eles
     * e só pegamos os valores do vértices das arestas de saida
     */
    public Map<Tipo, ArrayList<Tipo>> criarListasAdjacencia() {
        Map<Tipo, ArrayList<Tipo>> listasAdjacencia = new HashMap<>();

        for (Vertice<Tipo> vertice : vertices) {
            ArrayList<Tipo> adjacentes = new ArrayList<>();
            for (Aresta<Tipo> aresta : vertice.getArestasSaida()) {
                Vertice<Tipo> destino = aresta.getSaida();
                adjacentes.add(destino.getDado());
            }
            listasAdjacencia.put(vertice.getDado(), adjacentes);
        }

        return listasAdjacencia;
    }
    
    
    /*
     * 
     */
    public void imprimirArestasAdjacentes(Tipo dado) {
        Vertice<Tipo> vertice = getVertice(dado);
        if (vertice != null) {
            System.out.println("Arestas adjacentes ao vértice " + dado + ":");
            for (Aresta<Tipo> aresta : vertice.getArestasAdjacentes(vertice)) {
                System.out.println(aresta.getInicio().getDado() + " -> " + aresta.getSaida().getDado() + " (Peso: " + aresta.getPeso() + ")");
            }
        } else {
            System.out.println("Arestas " + dado + " não encontrado.");
        }
    }
    /*
     * Método que pega somente as vértices adjacentes ao vértice dado pelo usuário. Eles verificam se 
     tem a vértice(no if. Criam uma lista com o vértces adjacentes
     */
  public void imprimirVerticesAdjacentes(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        ArrayList<Vertice<Tipo>> verticesAdjacentes = vertice.getVerticesAdjacentes(vertice);
        System.out.println("Vértices adjacentes ao vértice " + dado + ":");
        for (Vertice<Tipo> adjacente : verticesAdjacentes) {
            System.out.println(adjacente.getDado());
        }
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
}
/*
 * Método que pega somente as arestas incidentes a ele, ou sejá vértices já conectados com ele, porém
 * somente na lista da aresta de saída
 */
public void imprimirArestasIncidentes(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        ArrayList<Aresta<Tipo>> arestasIncidentes = vertice.getArestasIncidentes(vertice);
        System.out.println("Arestas incidentes ao vértice " + dado + ":");
        for (Aresta<Tipo> aresta : arestasIncidentes) {
            System.out.println(aresta.getInicio().getDado() + " -> " + aresta.getSaida().getDado() + " (Peso: " + aresta.getPeso() + ")");
        }
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
}

/*
 * Método que imprime os vértices incidentes a um outro vértice, chamando um método da classe vértice
 * que cria uma lista vazia para a anexação dos vértices incidentes
 */
public void imprimirVerticesIncidentes(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        ArrayList<Vertice<Tipo>> verticesIncidentes = vertice.getVerticesIncidentes(vertice);
        System.out.println("Vértices incidentes ao vértice " + dado + ":");
        for (Vertice<Tipo> adjacente : verticesIncidentes) {
            System.out.println(adjacente.getDado());
        }
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
}
/*
* Método que pega o grau da vértice na classe Vértice, me retornando a aresta de saída e arestas de entradaa
*/
public void imprimirGrauVertice(Tipo dado) {
    Vertice<Tipo> vertice = getVertice(dado);
    if (vertice != null) {
        int grau = vertice.grau(); // Chama o método grau() diretamente no objeto vertice
        System.out.println("Grau do vértice " + dado + ": " + grau);
    } else {
        System.out.println("Vértice " + dado + " não encontrado.");
    }
  }
  
  
    /*
     * Vérifica se dois vértices são adjascentes em 3 possibilidades, se a aresta de saida do vértice um,
     * e igual a aresta do vértice 2, e se a aresta de inicio do vértice 1, corresponde em igualdade a 
     * aresta do vértice
     */
   public boolean saoAdjacentes(Tipo dadoVertice1, Tipo dadoVertice2) {
        Vertice<Tipo> vertice1 = getVertice(dadoVertice1);
        Vertice<Tipo> vertice2 = getVertice(dadoVertice2);

        if (vertice1 != null && vertice2 != null) {
            // Verifica se há uma aresta de vertice1 para vertice2
            for (Aresta<Tipo> aresta : vertice1.getArestasSaida()) {
                if (aresta.getSaida().equals(vertice2)) {
                    return true;
                }
            }
            // Verifica se há uma aresta de vertice2 para vertice1 (se o grafo for não-direcionado)
            for (Aresta<Tipo> aresta : vertice2.getArestasSaida()) {
                if (aresta.getSaida().equals(vertice1)) {
                    return true;
                }
            }
        }

        return false; // Retorna false se nenhum caso de adjacência for encontrado
    }
    
    /*
    * Método que pega quais são as  duas vértices ditas pelo o usuário, e troca os seus pesos 
    */
    public Boolean substituirPesoAresta(Tipo dadoInicio, Tipo dadoSaida, double novoPeso) {
        Vertice<Tipo> inicio = getVertice(dadoInicio);
        Vertice<Tipo> saida = getVertice(dadoSaida);
        boolean verifica = false;
    
        if (inicio != null && saida != null) {
            // Procura a aresta de inicio para saida
            for (Aresta<Tipo> aresta : inicio.getArestasSaida()) {
                if (aresta.getSaida().equals(saida)) {
                    aresta.setPeso(novoPeso);
                    verifica = true;
                    return verifica;
                }
            }
        }
        return verifica;
    }
        
    /*
    * Método que realiza a Busca em Largura de um grafo, criado pelo usuário. Ele pega a vértice dada pelo usuário
    faz a verificação se existe. 
    */
  public void BuscaemLargura() {
    Scanner scanner = new Scanner(System.in);

    // Solicita ao usuário para escolher o vértice inicial
    System.out.println("Digite o dado do vértice inicial para a busca em largura:");
    Tipo dadoInicial = (Tipo) scanner.nextLine();
    Vertice<Tipo> inicial = getVertice(dadoInicial);

    // Verifica se o vértice inicial existe
    if (inicial == null) {
        System.out.println("Vértice " + dadoInicial + " não encontrado.");
        return;
    }

    ArrayList<Vertice<Tipo>> marcados = new ArrayList<>();
    ArrayList<Vertice<Tipo>> fila = new ArrayList<>();
    Map<Vertice<Tipo>, Integer> nivel = new HashMap<>();// Mapa para armazenar o nível de cada vértice
    Map<Vertice<Tipo>, Vertice<Tipo>> pai = new HashMap<>(); // Mapa para armazenar o pai de cada vértice na árvore de busca
    int iteracao = 0;

    // Marca o vértice inicial como visitado e adiciona à fila
    marcados.add(inicial);
    fila.add(inicial);
    nivel.put(inicial, 0);// Inicializa com 0
    pai.put(inicial, null);// O vértice inicial não tem pai

    System.out.println("Busca em Largura a partir do vértice " + inicial.getDado() + ":");

    while (!fila.isEmpty()) {//verifica se esta vazio
        Vertice<Tipo> visitado = fila.remove(0);
        int nivelAtual = nivel.get(visitado);
        iteracao++;

        Vertice<Tipo> paiAtual = pai.get(visitado);
        String paiStr = (paiAtual != null) ? paiAtual.getDado().toString() : "null";

        System.out.println("Vértice: " + visitado.getDado() + ", Nível: " + nivelAtual + ", Pai: " + paiStr + ", Iteração: " + iteracao);

        for (Aresta<Tipo> aresta : visitado.getArestasSaida()) {
            Vertice<Tipo> proximo = aresta.getSaida();
            if (!marcados.contains(proximo)) {
                marcados.add(proximo);
                fila.add(proximo); // Adiciona o vértice à fila para visitação 
                nivel.put(proximo, nivelAtual + 1);// Define o nível do vértice adjacente
                pai.put(proximo, visitado);// Define o pai do vértice adjacente
            }
        }
    }
}
/*
* Método que realiza a Busca em Profundidade. Pega o vertice dado pelo usuário, 
*/
public void buscaEmProfundidade(Vertice<Tipo> verticeInicial) {
    ArrayList<Vertice<Tipo>> marcados = new ArrayList<>();
    Deque<Vertice<Tipo>> pilha = new ArrayDeque<>();
    Map<Vertice<Tipo>, Integer> tempoDescoberta = new HashMap<>();
    Map<Vertice<Tipo>, Integer> tempoFinalizacao = new HashMap<>();
    int tempo = 0;

    pilha.push(verticeInicial);

    System.out.println("Busca em Profundidade a partir do vértice " + verticeInicial.getDado() + ":");

    // faz o uso da pilha, onde vai empilhando os vértices que não foram visitados. 
    while (!pilha.isEmpty()) { // verificar se a pilha esta vazia 
        Vertice<Tipo> visitado = pilha.peek();//vertice do topo
        if (!marcados.contains(visitado)) {
            //Se o vérticefoi marcado como visitado, ele é adicionado à lista marcados, o tempo de descoberta é registrado e impresso
            marcados.add(visitado);
            tempo++;
            tempoDescoberta.put(visitado, tempo);
            System.out.println("Vértice: " + visitado.getDado() + ", Descoberta: " + tempoDescoberta.get(visitado));
        }

        boolean encontrouAdjacenteNaoMarcado = false;
        List<Vertice<Tipo>> adjacentes = visitado.getVerticesAdjacentes(visitado); // Obtém as vértices adjacentes ao vértice atual

        for (Vertice<Tipo> adj : adjacentes) {
            if (!marcados.contains(adj)) {
                pilha.push(adj);
                encontrouAdjacenteNaoMarcado = true;
            }
        }

        if (!encontrouAdjacenteNaoMarcado) {
            tempo++;
            tempoFinalizacao.put(visitado, tempo);
            pilha.pop();
            System.out.println("Vértice: " + visitado.getDado() + ", Finalização: " + tempoFinalizacao.get(visitado));
             //se o vértice adjacente não visitado foi encontrado (encontrouAdjacenteNaoMarcado é false), o vértice atual é desempilhado
            
        }
    }

    System.out.println("\nTempos de descoberta e finalização:");
    for (Vertice<Tipo> vertice : tempoDescoberta.keySet()) {
        System.out.println("Vértice: " + vertice.getDado() + ", Descoberta: " + tempoDescoberta.get(vertice) + ", Finalização: " + tempoFinalizacao.get(vertice));
    }
}
/*
* Método que realiza a troca de dois vértices dados pelo usuário.
*/
// public void trocarVertices(Tipo dado1, Tipo dado2) {
//     Vertice<Tipo> vertice1 = getVertice(dado1);
//     Vertice<Tipo> vertice2 = getVertice(dado2);

//     if (vertice1 == null || vertice2 == null) {
//         System.out.println("Um ou ambos os vértices não foram encontrados.");
//         return;
//     }// verifica se os dois existem

//     // Trocar as referências nas arestas de entrada e saída dos vértices
    
//     for (Aresta<Tipo> aresta : vertice1.getArestasEntrada()) {
//         if (aresta.getInicio().equals(vertice1)) {
//             aresta.setInicio(vertice2);
//         }
//     }
//     for (Aresta<Tipo> aresta : vertice1.getArestasSaida()) {
//         if (aresta.getSaida().equals(vertice1)) {
//             aresta.setSaida(vertice2);
//         }
//     }

//     for (Aresta<Tipo> aresta : vertice2.getArestasEntrada()) {
//         if (aresta.getInicio().equals(vertice2)) {
//             aresta.setInicio(vertice1);
//         }
//     }
//     for (Aresta<Tipo> aresta : vertice2.getArestasSaida()) {
//         if (aresta.getSaida().equals(vertice2)) {
//             aresta.setSaida(vertice1);
//         }
//     }
//     System.out.println("Vertice trocado com sucesso");
// }
/*
* Método que realiza o algoritmo de Dijkstra
*/

public void trocarVertices(Tipo dado1, Tipo dado2) {
    Vertice<Tipo> vertice1 = getVertice(dado1);
    Vertice<Tipo> vertice2 = getVertice(dado2);

    if (vertice1 == null || vertice2 == null) {
        System.out.println("Um ou ambos os vértices não foram encontrados.");
        return;
    }

    // Salvando as arestas de entrada e saída dos vértices
    ArrayList<Aresta<Tipo>> arestasEntradaV1 = new ArrayList<>(vertice1.getArestasEntrada());
    ArrayList<Aresta<Tipo>> arestasSaidaV1 = new ArrayList<>(vertice1.getArestasSaida());
    ArrayList<Aresta<Tipo>> arestasEntradaV2 = new ArrayList<>(vertice2.getArestasEntrada());
    ArrayList<Aresta<Tipo>> arestasSaidaV2 = new ArrayList<>(vertice2.getArestasSaida());

    // Limpando as listas de arestas dos vértices
    vertice1.getArestasEntrada().clear();
    vertice1.getArestasSaida().clear();
    vertice2.getArestasEntrada().clear();
    vertice2.getArestasSaida().clear();

    // Atualizando as arestas para apontar para os novos vértices
    for (Aresta<Tipo> aresta : arestasEntradaV1) {
        aresta.setSaida(vertice2);
        vertice2.AdicionarArestaEntrada(aresta);
    }

    for (Aresta<Tipo> aresta : arestasSaidaV1) {
        aresta.setInicio(vertice2);
        vertice2.AdicionarArestaSaida(aresta);
    }

    for (Aresta<Tipo> aresta : arestasEntradaV2) {
        aresta.setSaida(vertice1);
        vertice1.AdicionarArestaEntrada(aresta);
    }

    for (Aresta<Tipo> aresta : arestasSaidaV2) {
        aresta.setInicio(vertice1);
        vertice1.AdicionarArestaSaida(aresta);
    }

    System.out.println("Vértices " + dado1 + " e " + dado2 + " foram trocados.");
}
public void algoritmoDijkstra() {
        Scanner scanner = new Scanner(System.in);
        //solicita o usuario para escolher o vértice inicial
        System.out.println("Digite o dado do vértice inicial para o algoritmo de Dijkstra:");
        Tipo dadoInicial = (Tipo) scanner.nextLine();
        Vertice<Tipo> inicial = getVertice(dadoInicial);

        if (inicial == null) { // verifica se é nulo
            System.out.println("Vértice " + dadoInicial + " não encontrado.");
            return;
        }
        // solicita o usuário para escolher o vértice final
        System.out.println("Digite o dado do vértice final para o algoritmo de Dijkstra:");
        Tipo dadoFinal = (Tipo) scanner.nextLine();
        Vertice<Tipo> finalVertice = getVertice(dadoFinal);

        if (finalVertice == null) {//verifica se é nulo
            System.out.println("Vértice " + dadoFinal + " não encontrado.");
            return;
        }
        // armazena as distâncias e predecessores
        Map<Vertice<Tipo>, Double> distancias = new HashMap<>();
        Map<Vertice<Tipo>, Vertice<Tipo>> predecessores = new HashMap<>();
        PriorityQueue<Vertice<Tipo>> filaPrioridade = new PriorityQueue<>(Comparator.comparing(distancias::get));

        // Inicializa as distâncias de todos os vértices, começando com 0
        for (Vertice<Tipo> vertice : this.vertices) {
            if (vertice.equals(inicial)) {
                distancias.put(vertice, 0.0);
            } else {
                distancias.put(vertice, Double.POSITIVE_INFINITY);
            }
            filaPrioridade.add(vertice); // Adiciona todos os vértices na fila de prioridade

        }

        while (!filaPrioridade.isEmpty()) {//verifica se esta vazia
            Vertice<Tipo> atual = filaPrioridade.poll(); //  Remove o vértice com a menor distância da fila de prioridade
            for (Aresta<Tipo> aresta : atual.getArestasSaida()) {
                Vertice<Tipo> adjacente = aresta.getSaida();
                double novaDistancia = distancias.get(atual) + aresta.getPeso(); // calcula a nova distancia
                if (novaDistancia < distancias.get(adjacente)) {
                    filaPrioridade.remove(adjacente); // remove da fila de prioridade
                    distancias.put(adjacente, novaDistancia); // atualiza a distancia
                    predecessores.put(adjacente, atual); // atualiza o predecessor
                    filaPrioridade.add(adjacente); // adiciona a fila dnv com a distancia nova
                }
            }
        }

        if (distancias.get(finalVertice) == Double.POSITIVE_INFINITY) {
            System.out.println("Não existe caminho do vértice " + dadoInicial + " ao vértice " + dadoFinal + ".");
        } else {
            System.out.println("Distância do vértice " + dadoInicial + " ao vértice " + dadoFinal + ": " + distancias.get(finalVertice));
            // faz o caminho da vértice inicial pra final
            List<Vertice<Tipo>> caminho = new ArrayList<>();
            for (Vertice<Tipo> vertice = finalVertice; vertice != null; vertice = predecessores.get(vertice)) {
                caminho.add(vertice); // adiciona a vertice no caminho 
            }
            Collections.reverse(caminho);//reverter
            System.out.print("Caminho: ");
            //imprimi o caminho
            for (Vertice<Tipo> vertice : caminho) {
                System.out.print(vertice.getDado() + " ");
            }
            System.out.println();
        }
    }

    /*
    * Método que realiza o algoritmo de Floyd
    */
    public void algoritmoFloydWarshall() {
        int n = this.vertices.size();
        double[][] distancias = new double[n][n];
        Vertice<Tipo>[][] predecessores = new Vertice[n][n];

        // Inicialização das distâncias
        for (int i = 0; i < n; i++) {
            Arrays.fill(distancias[i], Double.POSITIVE_INFINITY); // defini as distancias ate infinito
            distancias[i][i] = 0; // vertice pra ele mesmo é igual a 0
        }

        // Configuração das arestas
        for (Aresta<Tipo> aresta : this.arestas) {
            int u = this.vertices.indexOf(aresta.getInicio());
            int v = this.vertices.indexOf(aresta.getSaida());
            distancias[u][v] = aresta.getPeso();
            predecessores[u][v] = aresta.getInicio();
        }

        // Algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias[i][j] > distancias[i][k] + distancias[k][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];// Atualiza a distância se um caminho mais curto for encontrado
                        predecessores[i][j] = predecessores[k][j];// Atualiza o predecessor
                    }
                }
            }
        }

        Scanner scanner = new Scanner(System.in);

        //solicita o usuario para escolher o vertice inicial
        System.out.println("Digite o dado do vértice inicial para o algoritmo de Floyd-Warshall:");
        Tipo dadoInicial = (Tipo) scanner.nextLine();
        Vertice<Tipo> inicial = getVertice(dadoInicial);

        if (inicial == null) {
            System.out.println("Vértice " + dadoInicial + " não encontrado.");
            return;
        }

        int inicioIdx = this.vertices.indexOf(inicial);
        System.out.println("Menor distância do vértice " + dadoInicial + " para todos os outros vértices:");

        // imprimi as menores distancias e caminhos
        for (int i = 0; i < n; i++) {
            if (distancias[inicioIdx][i] == Double.POSITIVE_INFINITY) {
                System.out.println("Não existe caminho do vértice " + dadoInicial + " para o vértice " + this.vertices.get(i).getDado() + ".");
            } else {
                System.out.println("Distância para " + this.vertices.get(i).getDado() + ": " + distancias[inicioIdx][i]);
                System.out.print("Caminho: ");
                imprimirCaminho(inicial, this.vertices.get(i), predecessores);
                System.out.println();
            }
        }
    }
   //metodo pra imprimir o caminho 
    private void imprimirCaminho(Vertice<Tipo> inicio, Vertice<Tipo> fim, Vertice<Tipo>[][] predecessores) {
        Stack<Vertice<Tipo>> caminho = new Stack<>();
        Vertice<Tipo> atual = fim;

        while (atual != null && !atual.equals(inicio)) {
            caminho.push(atual);
            int idxAtual = this.vertices.indexOf(atual);
            int idxPredecessor = this.vertices.indexOf(predecessores[this.vertices.indexOf(inicio)][idxAtual]);
            atual = this.vertices.get(idxPredecessor);//imprimi usando os predessesores
        }

        if (atual == null) {
            System.out.print("Nenhum caminho encontrado.");
            return;
        }

        System.out.print(inicio.getDado());
        while (!caminho.isEmpty()) {
            System.out.print(" -> " + caminho.pop().getDado());
        }
    }
}












