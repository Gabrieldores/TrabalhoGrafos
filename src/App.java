/*
 * Tarefas : - Arrumar o método criar grafo
 * - Fazer as representações em matriz de adjascencia ou lista de adjascencia 
 * - Depois fazer a verificação se um grafo ele é denso ou esparso
 * - Depois começar a fazer a busca em largura e em profundidade
 */

import java.util.Scanner;

public class App {

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
                    // Aqui deve ter a lógica para grafos densos ou esparsos
                    
                    break;
                case 3:
                    // Lógica para ler o grafo em DIMACS
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
