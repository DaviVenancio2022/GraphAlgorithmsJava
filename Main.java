import java.io.IOException;

class Main {
	
    public static void main(String[] args) {
	    
	Graph g1 = new Graph(4);
	    
	g1.addEdge(0, 1, 3);
    	g1.addEdge(1, 0, 3);
   	g1.addEdge(0, 3, 4);
   	g1.addEdge(3, 0, 4);
    	g1.addEdge(3, 4, 4);//warning

	System.out.println("\n---------------------------------------------");
	System.out.println("Matriz: ");
        System.out.println(g1);
	    
	System.out.println("---------------------------------------------");
	System.out.println("Tamanho nodes de cada linhada matriz: ");
	System.out.println(g1.degree(0));
	System.out.println(g1.degree(1));
	System.out.println(g1.degree(2));
	System.out.println(g1.degree(3));
			   
	System.out.println("\n---------------------------------------------");
	System.out.println("Node de maior grau: " + g1.highestDegree());
	    
	System.out.println("\n---------------------------------------------");
	System.out.println("Node de menor grau: " + g1.lowestDegree());
	    
	System.out.println("\n---------------------------------------------");
	System.out.println("Matriz complementar: \n" + g1.complement());
    }
}
