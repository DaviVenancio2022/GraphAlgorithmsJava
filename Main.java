import java.io.IOException;
import java.util.Scanner;

class Main {

  	public static void main(String[] args) throws IOException {
		/*
		Graph g1 = new Graph(4); // criando um grafo tamanho 4x4
		g1.addEdge(0, 1, 3); // adc arestas, recebendo origem/x, destino/y e peso/valor
    	g1.addEdge(1, 0, 3); // (1, 0)= 3
   		g1.addEdge(0, 3, 4); // (0, 3)= 4
   		g1.addEdge(3, 0, 4); // (3, 0)= 4
    	g1.addEdge(3, 4, 4); // (3, 4)= 4  warning!! y nao pode receber psÇ maior q 3
		
		System.out.println("\n---------------------------------------------");
		System.out.println("Matriz: ");
	    System.out.println(g1);
		System.out.println("Matriz g1: ");
	    System.out.println(g1); // imprimindo matriz
		System.out.println("---------------------------------------------");
		System.out.println("Tamanho nodes de cada linha
						   da matriz: ");
		System.out.println("Grau de cada linha/nó da matriz: "); // linhas sao nos?
		System.out.println(g1.degree(0));
		System.out.println(g1.degree(1));
		System.out.println(g1.degree(2));
		System.out.println(g1.degree(3));
		System.out.println("\n---------------------------------------------");
		System.out.println("Node de maior grau: " + g1.highestDegree());
		System.out.println("Nó com maior grau: " + g1.highestDegree());
		System.out.println("\n---------------------------------------------");
		System.out.println("Node de menor grau: " + g1.lowestDegree());
		System.out.println("Nó de menor grau: " + g1.lowestDegree());
		System.out.println("\n---------------------------------------------");
		System.out.println("Matriz complementar: \n" + g1.complement());
		System.out.println("\n---------------------------------------------");
		System.out.println("Densidade matriz g1: \n" + g1.density());
		
		Graph g2 = new Graph(3);
	    g2.addEdge(0, 1, 1);
	    g2.addEdge(1, 0, 1);
		System.out.println("\n---------------------------------------------");
	    System.out.println("g2 e subgrafo de g1? " + g1.subGraph(g2)); // true
		System.out.println("\nDensidade de g2: \n" + g2.density());
		
	    Graph g3 = new Graph(4);
	    g3.addEdge(1, 3, 1);
	    g3.addEdge(3, 1, 1);
		System.out.println("\n---------------------------------------------");
	    System.out.println("g3 e subgrafo de g1? " + g1.subGraph(g3)); // false
		System.out.println("\nDensidade de g3: \n" + g3.density());
    }
	
		Graph g1 = new Graph(9); // nos= 9
		g1.addEdgeUnoriented(7, 5, 1);
		g1.addEdgeUnoriented(7, 1, 1);
		g1.addEdgeUnoriented(7, 2, 1);
		g1.addEdgeUnoriented(1, 0, 1);
		g1.addEdgeUnoriented(1, 4, 1);
		g1.addEdgeUnoriented(2, 3, 1);
		g1.addEdgeUnoriented(5, 6, 1);
		g1.addEdgeUnoriented(6, 8, 1);

		System.out.println("\n---------------------------------------------");
		System.out.println("Grafo g1:\n");
		System.out.println(g1);
		System.out.println("\nLista de adjascencia/busca em largura: ");
		System.out.println(g1.bfs(7)); // Inicializaçao da busca s=7
		System.out.println("\n---------------------------------------------");
		System.out.println("\nGrafo g1 e conexo? ");
		System.out.println(g1.connected());

		System.out.println("\n---------------------------------------------");
		System.out.println("\nGrafo g2 txt:\n");
		Graph g2 = new Graph("Graph2.txt");
		System.out.println(g2);

		System.out.println("\n---------------------------------------------");
		System.out.println("\nGrafo g3:\n");
		Graph g3 = new Graph(7);
		g3.addEdgeUnoriented(6, 3, 1);
		g3.addEdgeUnoriented(6, 4, 1);
		g3.addEdgeUnoriented(6, 5, 1);
		g3.addEdgeUnoriented(3, 2, 1);
		g3.addEdgeUnoriented(4, 0, 1);
		g3.addEdgeUnoriented(0, 1, 1);

		System.out.println(g3);
		System.out.println("\n---------------------------------------------");
		System.out.println("\nLista de adjascencia/busca em profundidade: ");
		System.out.println(g3.dfs(6)); // Inicializaçao da busca s=6
		System.out.println("\n---------------------------------------------");
		System.out.println("\nGrafo g3 e orientado? ");
		System.out.println(g3.nonOriented()); // Inicializaçao da busca s=6
		System.out.println("\n---------------------------------------------");
		Graph g4 = new Graph(4);
		g3.addEdgeUnoriented(0, 1, 1);
		g3.addEdgeUnoriented(0, 2, 6);
		g3.addEdgeUnoriented(1, 2, 4);
		g3.addEdgeUnoriented(2, 1, 3);
		System.out.println(g4);
		System.out.println("\n---------------------------------------------");
		Graph g5 = new Graph("Graph5.txt");
		System.out.println("\nGrafo g5 txt:\n");
		System.out.println(g5);
		g5.floid_warshall(1,2);
		System.out.println("\n---------------------------------------------");
		Graph g6 = new Graph("Graph6.txt");
		System.out.println("\nGrafo g6 txt:\n");
		System.out.println(g6);
		g6.floid_warshall(0, 3);

		Scanner teclado; 
		teclado = new Scanner(System.in);
		int opc, orig, dest;
		String arq;
		
		do {
			System.out.println("\n1 - Caminho Minimo: ");
			System.out.println("2 - Labirinto: ");
			System.out.println("3 - Sair: ");
			System.out.println("\nInforme a tarefa: ");
			opc = teclado.nextInt();
	
			if(opc == 1) {
				System.out.print("\nArquivo: ");
				arq = teclado.next();
				System.out.print("\nOrigem: ");
				orig = teclado.nextInt();
				System.out.print("\nDestino: ");
				dest = teclado.nextInt();
	
				long tempoInicial = System.currentTimeMillis();
	        	GraphMatrix.Labirinto(arq);
				System.out.print("Arquivo: ");
				System.out.print(arq);
		        long tempoFinal = System.currentTimeMillis();
				System.out.print("\nTempo em milisegundos: "); 
				System.out.print(tempoFinal-tempoInicial);
				System.out.println("\n\n---------------------------------------------");
			}else if(opc == 3) {
				System.out.println("\n---------------------------------------------");
				System.out.println("\nFim do Programa! ");
				break;
			}
				
		}while(opc!= 3);
	}
	*/
	System.out.println("\n---------------------------------------------");
		long tempoInicial = System.currentTimeMillis();
		Graph g6 = new Graph("USA-road-dt.DC.txt");
		System.out.println("\nGrafo g6 txt:\n");
		System.out.println(g6);
		g6.floid_warshall(1, 5);
		long tempoFinal = System.currentTimeMillis();
		System.out.print("\nTempo em milisegundos: "); 
		System.out.print(tempoFinal-tempoInicial);
	}
}
