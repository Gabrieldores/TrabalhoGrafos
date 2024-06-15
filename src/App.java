/*
 * Tarefas : - 
 * - Depois fazer a verificação se uclm grafo ele é denso ou esparso
 * - Verificação em DIMACS
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {

    public static void ArquivoDimacs(){
        try {
            FileReader arquivoDimacs = new FileReader("Dimacs.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Scanner leitura = new Scanner("Dimacs.txt");

       String PrimeiraLinha = leitura.nextLine();
       String[] Verifica;

       Verifica = PrimeiraLinha.split(PrimeiraLinha);
       

        while (leitura.hasNextLine()) {
        String linha = leitura.nextLine();
        String [] atual = linha.split(linha);

          
            
        }
    }

       public static void main(String[] args) throws Exception {
        int escolha;
        Scanner ler = new Scanner(System.in);
        Grafo<String> grafo = new Grafo<String>();


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
            System.out.println("8 - Busca em largura");
            System.out.println("9 - Busca em profundidade");
            System.out.println("0 - Sair");
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
                    Map<String, ArrayList<String>> listasAdjacencia = grafo.criarListasAdjacencia();
        System.out.println("Listas de Adjacência:");
        for (Map.Entry<String, ArrayList<String>> entry : listasAdjacencia.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
                    break;
                case 4:
                    
                    break;
                case 5:
                   
                    break;
                case 6:
                    
                    break;
                case 7:
                   
                    break;
                case 8:
                    
                    break;
                case 9:
                  
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
