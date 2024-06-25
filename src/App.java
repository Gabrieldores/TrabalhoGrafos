/*
 * Classe app: Para que o usuário possa manipular todas as funções de nossas classes
 */
import java.util.ArrayList; //classe para manipulação de listas em java
import java.util.HashMap; //classe para trabalhar com tabela hash
import java.util.Map; // classe para manipulação do objeto map
import java.util.Scanner; // Scanner para ler arquivo e a entrada do usuário
import java.io.FileNotFoundException; // Exceção para erro de leitura de arquivos
import java.io.FileReader; //Classe para leitura do arquvio Dimacs.txt

public class App {

    /*
     * Método arquivo Dimacs, que recebe o arquivo Dimacs.txt que está presente na pasta src,
     * presente na pasta de desenvolvimento das nossas classes para manipulação do grafo. Em que
     * sua função principal e ler um arquivo dimacs e retornando ele como um grafo, com vértices, arestas
     * e peso de suas arestas
     */
    public static void ArquivoDimacs(Grafo<String> g) {
        try {
            FileReader file = new FileReader("src/Dimacs.txt");
            Scanner arq = new Scanner(file);
            Map<String, Boolean> verticeAdd = new HashMap<>();

            if (arq.hasNextLine()) {
                arq.nextLine(); 
            }

            while (arq.hasNextLine()) {
                String linha = arq.nextLine();
                String[] atual = linha.split(" ");  

                String origem = atual[0];
                String destino = atual[1];
                double peso = Double.parseDouble(atual[2]);

                if (!verticeAdd.containsKey(origem)) {
                    g.adicionarVertice(origem);
                    verticeAdd.put(origem, true);
                }
                if (!verticeAdd.containsKey(destino)) {
                    g.adicionarVertice(destino);
                    verticeAdd.put(destino, true);
                }

                g.adicionarAresta(peso, origem, destino);
            }

            arq.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    } 

    /*
     * Main para a manipulação do usuário em seu objeto grafo
     */
       public static void main(String[] args) throws Exception {
        int escolha;
        Scanner ler = new Scanner(System.in);
        Grafo<String> grafo = new Grafo<String>();
        String x;
        String y; //Somente usado para saber se há adjascencia entre os vértices

        /*
         * Do while para impressão de nosso menu
         */
        do {
        System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("***MENU TRABALHO GRAFOS***");
            System.out.println("1 - CRIAR GRAFO");
            System.out.println("2 - Apresentar seu grafo");
            System.out.println("3 - Leitura Grafo em DIMACS");
            System.out.println("4 - Todas as arestas adjacentes do vértice escolhido");
            System.out.println("5 - Todos os vértices adjacentes do vértice escolhido");
            System.out.println("6 - Todas as arestas incidentes do vértice escolhido"); 
            System.out.println("7 - Todos os vértices incidentes do vértice escolhido");            System.out.println("8 - Imprimir o grau de um vértice");
            System.out.println("9 - Determinar se dois vértices são adjascentes");
            System.out.println("10 - Substituir o peso de uma aresta ");
            System.out.println("11 - Trocar dois vértices "); 
            System.out.println("12 - Busca em largura");
            System.out.println("13 - Busca em profundidade");
            System.out.println("14 - Algoritmo de Drijskta");
            System.out.println("15 - Algoritmo de Floyd-Warshall");
            System.out.print("Escolha uma opção: ");
            escolha = ler.nextInt();
            /*
             * Switch case para as escolhas de nosso menu
             */
            switch (escolha) {
                case 1:
                    grafo.CriarGrafo(grafo);
                    break;
                case 2:
                    grafo.imprimirGrafo(grafo);
                    
                    break;
                case 3:
                    ArquivoDimacs(grafo);
        
                    break;
                case 4:
                    System.out.println("Digite o vertices para descobrir as arestas adjascentes : ");
                    x = ler.next();
                    grafo.imprimirArestasAdjacentes(x);
                    
                    break;
                case 5:
                    System.out.println("Digite o vértice para descobrir os seus vértices adjascentes : ");
                    x = ler.next();
                    grafo.imprimirVerticesAdjacentes(x);
                   
                    break;
                case 6:
                System.out.println("Digite o vértice para descobrir os seus vértices incidentes : ");
                    x = ler.next();
                    grafo.imprimirArestasIncidentes(x);
                    
                    break;
                case 7:
                System.out.println("Digite o vértice para descobrir as suas arestas incidentes : ");
                    x = ler.next();
                    grafo.imprimirArestasIncidentes(x);
                
                   
                    break;
                case 8:
                System.out.println("Digite o Vértice para saber seu grau :");
                x = ler.next();
                grafo.imprimirGrauVertice(x);
                    
                    break;
                case 9:
                System.out.println("Digite os dois vértices para saber se são Adjacentes");
                x = ler.next();
                y = ler.next();
                
                if (grafo.saoAdjacentes(x, y)) {
                    System.out.println("Os vértices " + x + " e " + y + " são adjacentes.");
                } else {
                    System.out.println("Os vértices " + x + " e " + y + " não são adjacentes.");
                }
                    break;
                case 10:
                int peso;
                System.out.println("Digite os dois vértices para alterar o peso da aresta :");
                x = ler.next();
                y = ler.next();
                System.out.println("Digite o peso desejado :");
                peso = ler.nextInt();

                if(grafo.substituirPesoAresta(x, y, peso))
                {
                    System.out.println("Peso das arestas substituidas");
                }
                else{
                    System.out.println("Peso das arestas não substituidas!!!");
                }
                    break;
                case 11:
                System.out.println("Digite os dois vértices para alterar os seus lugares:");
                x = ler.next();
                y = ler.next();
                grafo.trocarVertices(x, y);

                    break;
                case 12:
                    grafo.BuscaemLargura();
                    break;
                case 13:
                System.out.println("Digite o vértices");
                x = ler.next();
                Vertice<String> vertice = grafo.getVertice(x);
                    grafo.buscaEmProfundidade(vertice);
                    break;
                case 14:
                grafo.algoritmoDijkstra();

                    break;
                case 15:
                grafo.algoritmoFloydWarshall();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
            
            if (escolha != 0) {
                System.out.println("\nPressione Enter para continuar...");
                ler.nextLine(); // Limpa o buffer do scanner
                ler.nextLine(); // Espera o usuário pressionar Enter
            }
            
        } while (escolha != 0);

        ler.close();
       
    }
}
