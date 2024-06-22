
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {

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

       public static void main(String[] args) throws Exception {
        int escolha;
        Scanner ler = new Scanner(System.in);
        Grafo<String> grafo = new Grafo<String>();
        String x;
        String y; //Somente usado para saber se há adjascencia entre os vértices


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
            System.out.println("7 - Todos os vértices incidentes do vértice escolhido");
            System.out.println("8 - Imprimir o grau de um vértice");
            System.out.println("9 - Determinar se dois vértices são adjascentes");
            System.out.println("10 - Substituir o peso de uma aresta ");
            System.out.println("11 - Trocar dois vértices ");
            System.out.println("12 - Busca em largura");
            System.out.println("13 - Busca em profundidade");
            System.out.println("14 - Algoritmo de Drijskta");
            System.out.println("15 - Algoritmo de Floyd-Warshall");
            System.out.print("Escolha uma opção: ");
            escolha = ler.nextInt();

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
                    grafo.imprimirVerticesIncidentes(x);
                    
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
                
                grafo.saoAdjacentes(x, y);
                    break;
                case 10:
                int peso;
                System.out.println("Digite os dois vértices para alterar o peso da aresta :");
                x = ler.next();
                y = ler.next();
                System.out.println("Digite o peso desejado :");
                peso = ler.nextInt();

                grafo.substituirPesoAresta(x, y, peso);
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

                //Parte Sarah

                    break;
                case 14:

                    break;
                case 15:

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
