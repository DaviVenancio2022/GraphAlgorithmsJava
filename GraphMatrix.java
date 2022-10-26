import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class GraphMatrix {

    private int countNodes;
    private int countEdges;
    private int [][] adjMatrix;
	private static final int INF = 9999; // variavel INFINITO global

    public GraphMatrix(int countNodes) { // metodo construtor recebe n de vertices/nos
        this.countNodes = countNodes;
        this.adjMatrix = new int[countNodes][countNodes]; // cria matriz de adj quadrada
    }

    public int getCountNodes() {
        return this.countNodes;
    }

    public int getCountEdges() {
        return this.countEdges;
    }

    public int [][] getAdjMatrix() {
        return this.adjMatrix;
    }

    public String toString() {
        String str = "";
        for(int i = 0; i < this.adjMatrix.length; ++i) {
            for(int j = 0; j < this.adjMatrix[i].length; ++j) {
                str += this.adjMatrix[i][j] + "\t";
            }
            str += "\n";
        }
        return str;
    }

    public void addEdge(int source, int sink, int weight) {
        if(source < 0 || source > this.countNodes -1 || sink < 0 || sink > this.countNodes -1 || weight <= 0) {
	    	System.err.println("Invalid adge: " + " " + source + " " + sink + " " + weight);
	    	return;
		}
		this.adjMatrix[source][sink] = weight;
		this.countEdges++;
    }

	public void addEdgeUnoriented(int u, int v, int w) { //adc arestas= origem, destino, peso
	    if(u < 0 || u > this.countNodes -1
		   || v < 0 || v > this.countNodes -1
		   || w <= 0) {
			System.out.println("\n---------------------------------------------");
	        System.err.println("Invalid adge: " + " " + u + " " 
			+ v + " " + w);
			return;
	    }
	    this.adjMatrix[u][v] = w;
		this.adjMatrix[v][u] = w;
	    this.countEdges += 1;
    }
  
    public int degree(int node) {
        if(node < 0 || node > this.countNodes -1) {
      	    System.out.println("Invalid node: "  + node);
		}
    	int degree = 0;
		for(int j = 0; j < this.adjMatrix[node].length; ++j) {
	    	if(this.adjMatrix[node][j] != 0) {
	        	++degree;
	    	}
		}
		return degree;
    }

    public int highestDegree() {
        int highest = 0; // maior = 0;
		for(int i = 0; i < this.adjMatrix.length; ++i) { // ler linhas da matriz = 4
		    int aux = this.degree(i); // auxiliar para evitar chamar a funcao toda hora
		    if(highest < aux ) { // comparar todas os nos
		        highest = aux;
		    }
		}
		return highest;
    }

    public int lowestDegree() {
		int lowest = 0; // menor = 0;
		for(int i = 0; i < this.adjMatrix.length; ++i) { // ler linhas da matriz = 4
		    int aux = this.degree(i); // auxiliar para evitar chamar a funcao toda hora
		    if(lowest > aux ) { // comparar todas os nos
		        lowest = aux;
		    }
		}
	     return lowest;
    }

    public Graph complement() {
		Graph g2 = new Graph(this.countNodes);
		for(int i = 0; i < this.adjMatrix.length; ++i) {
		    for (int j = 0; j < this.adjMatrix[i].length; ++j) {
				if(this.adjMatrix[i][j] == 0 && i != j) {
			  	  //g2.adjMatriz[i][j]= 1;
				    g2.addEdge(i, j, 1);
				}
		    }
		}
		return g2;
    }

	public float density() {
		return (float)this.countEdges / (this.countNodes * (this.countNodes -1));
	}

	public boolean subGraph(GraphMatrix g2) {
		if(g2.countNodes > this.countNodes || g2.countEdges > this.countEdges) {
			return false;
		}
		for(int i = 0; i < g2.adjMatrix.length; ++i) {
			for(int j = 0; j < g2.adjMatrix[i].length; ++j) {
				if(g2.adjMatrix[i][j] != 0 && this.adjMatrix[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public ArrayList<Integer> bfs(int s) { // Busca em largura
		int [] desc = new int[this.countNodes];
		ArrayList<Integer> Q = new ArrayList<>();
		Q.add(s);
		ArrayList<Integer> R = new ArrayList<>();
		R.add(s);
		desc[s] = 1;
		while(Q.size() != 0) {
			int u = Q.remove(0);
			for(int v = 0; v < this.adjMatrix[u].length; ++v) {
				if(this.adjMatrix[u][v] != 0 && desc[v] == 0) { // V e adjacente a u
					Q.add(v);
					R.add(v);
					desc[v] = 1; // Foi descoberto
				}
			}
		}
		return R;
	}

	public boolean connected() { // Funcao para verificar se grafo e conexo
		return this.bfs(0).size() == this.countNodes;
	}

	public GraphMatrix(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));
        // Create and fill adjMatrix with read edges
        this.adjMatrix = new int[this.countNodes][this.countNodes];
        for(int i = 0; i < fileLines; ++i) {
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int sink = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            addEdge(source, sink, weight);
        }
        bufferedReader.close();
        reader.close();
	}
	public ArrayList<Integer> dfs(int s) { // Busca em profundidade
		int [] desc = new int[this.countNodes]; // desc: Descoberto
		ArrayList<Integer> S = new ArrayList<>(); // Criar o conjunto/pilha s{}
		S.add(s);
		ArrayList<Integer> R = new ArrayList<>(); // Criar o conjunto R{}
		R.add(s);
		desc[s] = 1;
		while(S.size() != 0) {
			boolean unstak = true; // desempilhar = flag = 1 tem que iniciar dentro do while
			int u = S.get(S.size() -1); // Ultima posicao
			for(int v = 0; v < this.adjMatrix[u].length; ++v) {
				if(this.adjMatrix[u][v] != 0 && desc[v] == 0) { // V e adjacente a u . existe aresta?
					S.add(v);
					R.add(v);
					desc[v] = 1; // Foi descoberto
					unstak = false; // flag = 0;
					break; // empilha so o primeiro adj
				}
			}
			if(unstak) {
				S.remove(S.size() -1); // remove ultima posicao
			}
		}
		return R;
    }

	public boolean nonOriented() {
		for(int i = 1; i < this.adjMatrix.length; ++i) {
			for(int j = i+1; j < this.adjMatrix[i].length; ++j) {
				if(this.adjMatrix[i][j] != this.adjMatrix[j][i]) {
					return false;
				}
			}
		}
		return true;
	}

	public ArrayList<Integer> dfsRec(int s) {
    	int[] desc = new int[this.countNodes];
    	ArrayList<Integer> R = new ArrayList<>();
    	dfsRecAux(s, desc, R); // Chamando a funcao recursiva
    	return R;
    }
	
	public void dfsRecAux(int u, int[] desc, ArrayList<Integer> R) { // Funcao recursiva
	    desc[u] = 1;
	    R.add(u);
	    for(int v = 0; v < this.adjMatrix[u].length; ++v) {
	  	 	if(this.adjMatrix[u][v] != 0 && desc[v] == 0) {
	        	dfsRecAux(v, desc, R);
	        }
	    }
  	}
	/*
	public void dijkstra(int u, int v, int s, int t) {
		int[] dist = new int[this.countNodes]; // Vetor de distancia
		int[] pred = new int[this.countNodes]; // Vetor de predecessor
		ArrayList<Integer> Q = new ArrayList<>();
		dist[v] = INF;
		pred[v] = null; 
		dist[s] = 0;
		Q = v; // Nos a processar
		while (Q != 0) {  // O(n)
			u = dist[Q];
			Q.remove(u);
			if(this.adjMatrix[u][v] != 0 && desc[v] == 0) {  // O(n^2)
				if(dist[v] > dist[u] + this.adjMatrix[u][v]) { // O(n^2)
					dist[v] = dist[u] + this.adjMatrix[u][v]; // O(n^2)
					pred[u] = v;
				}
			}
			
		}
	}
	//private static final int INF = 99999;
	*/
	public void floid_warshall(int s, int t) {
		int[][] dist = new int[this.countNodes][this.countNodes];
		int[][] pred = new int[this.countNodes][this.countNodes];

		for(int i = 0; i < this.adjMatrix.length; ++i) {
			for (int j = 0; j < this.adjMatrix[i].length; ++j) {
				if(i == j) {
					dist[i][j] = 0;
				}
				else if(this.adjMatrix[i][j] != 0) { // tem aresta em (i, j)
					dist[i][j] = this.adjMatrix[i][j];
					pred[i][j] = i;
				}
				else {
					dist[i][j] = INF;
					pred[i][j] = -1;
				}
			}
		}
		for(int k = 0; k < this.countNodes; k++) {
			for(int i = 0 ; i < this.countNodes; i++) {
				for(int j = 0 ; j < this.countNodes; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						pred[i][j] = pred[k][j];
					}
				}
			}
		}

		// recuperando caminhos
		System.out.println("\n---------------------------------------------");
		System.out.printf("\nCusto: %d", pred[s][t]);
		System.out.printf("\nOrigem: %d", s);
		System.out.printf("\nDestino: %d", t);
		ArrayList<Integer> C = new ArrayList<Integer>();
		C.add(t);
		int aux = t;
		while(aux != s) {
			aux = pred[s][aux];
			C.add(0, aux);
		}
		System.out.println("\nCaminho :" + C);
		
		/*
		System.out.println("Matriz dist\n");
		for(int i = 0; i < dist.length; i++) {
			for(int j = 0 ; j < dist[i].length; j++) {
				System.out.printf("%4d ", dist[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n---------------------------------------------");
		System.out.println("Matriz pred\n");
		for(int i = 0; i < pred.length; i++) {
			for(int j = 0 ; j < pred[i].length; j++) {
				System.out.printf("%2d ", pred[i][j]);
			}
			System.out.println();
		}
		*/
	}

	public static GraphMatrix Labirinto(String fileName) throws IOException {
		File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
		int cont1 = 0, cont2 = 0, S = 0, E = 0;
        String[] vet_linha; 
        String arq; 
        while((arq = bufferedReader.readLine()) != null) {
            vet_linha = arq.split("");
            cont2 = vet_linha.length;
            cont1++;
        }
        String[][] matriz_lab = new String[cont1][cont2];
        bufferedReader.close();
        reader.close();
        reader = new FileReader(file);
        bufferedReader = new BufferedReader(reader);
        bufferedReader.close();
        reader.close();
        reader = new FileReader(file);
        bufferedReader = new BufferedReader(reader);
        int cont3 = 0;
        while((arq = bufferedReader.readLine()) != null) {
            vet_linha = arq.split("");
            for (int i = 0; i < vet_linha.length; i++) {
                matriz_lab[cont3][i] = vet_linha[i];
            }
            cont3++;
        }
        int countNodes = 0;
        for(int i = 0; i < matriz_lab.length; i++) {
            for(int j = 0; j < matriz_lab[i].length; j++) {
                if (matriz_lab[i][j] == null) {
                    matriz_lab[i][j] = countNodes + "";
                    countNodes++;
                }else {
                    if(!matriz_lab[i][j].equals("#") && !matriz_lab[i][j].equals("█")) {
                        if(matriz_lab[i][j].equals("S")) {
                            S = countNodes;
                        }
                        if (matriz_lab[i][j].equals("E")) {
                            E = countNodes;
                        }
                        matriz_lab[i][j] = countNodes + "";
                        countNodes++;
                    }
                }
            }
        }
		System.out.println("\n---------------------------------------------");
		System.out.println("\nLabirinto: \n");
        for(int i = 0; i < matriz_lab.length; i++) {
            for(int j = 0; j < matriz_lab[i].length; j++) {
                if(!matriz_lab[i][j].equals("#") && !matriz_lab[i][j].equals("█")) {
                    if(Integer.parseInt(matriz_lab[i][j]) < 10){
                        System.out.print(0 + matriz_lab[i][j] + " ");
                    }else {
                        System.out.print(matriz_lab[i][j] + " ");
                    }
                }else {
                    System.out.print("##" + " ");
                }
            }
            System.out.println("");
        }
        GraphMatrix g3 = new GraphMatrix(countNodes);
        for(int i = 0; i < matriz_lab.length; i++) {
            for(int j = 0; j < matriz_lab[i].length; j++) {
                if(j + 1 < matriz_lab[i].length) {
                    if((!matriz_lab[i][j].equals("#") && !matriz_lab[i][j].equals("█")) && (!matriz_lab[i][j+1].equals("#") && !matriz_lab[i][j + 1].equals("█"))) {
                        g3.addEdgeUnoriented(Integer.parseInt(matriz_lab[i][j]), Integer.parseInt(matriz_lab[i][j + 1]), 1);
                    }
                }
                if(i + 1 < matriz_lab.length) {
                    if((!matriz_lab[i][j].equals("#") && !matriz_lab[i][j].equals("█")) && (!matriz_lab[i+1][j].equals("#") && !matriz_lab[i + 1][j].equals("█"))) {
                        g3.addEdgeUnoriented(Integer.parseInt(matriz_lab[i][j]), Integer.parseInt(matriz_lab[i + 1][j]), 1);
                    }
                }
            }
        }
        g3.floid_warshall(S, E);
        return g3;
    }
}