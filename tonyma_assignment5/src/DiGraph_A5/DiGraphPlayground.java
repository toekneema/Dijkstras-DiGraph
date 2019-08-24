package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
      exTest();
      time(1000000);
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      d.addNode(1, "f");
      d.addNode(3, "s");
      d.addNode(7, "t");
      d.addNode(0, "fo");
      d.addNode(4, "fi");
      d.addNode(6, "si");
      d.addEdge(0, "f", "s", 0, null);
      d.addEdge(1, "f", "si", 0, null);
      d.addEdge(2, "s", "t", 0, null);
      d.addEdge(3, "fo", "fi", 0, null);
      d.addEdge(4, "fi", "si", 0, null);
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
    }
    private static void time(int nodes) {
    	long startTime = System.nanoTime();
    	DiGraph d = new DiGraph();
    	d.addNode(0, "0");
    	for (int i = 0; i<nodes; i++) {
    		d.addNode(i, String.valueOf(i));
    		d.addEdge(i, String.valueOf(i - 1), String.valueOf(i), 1, null);
    	}
    	long endTime = System.nanoTime();
    	double totalTime = (endTime - startTime) / 1000000000.0;
    	
    	System.out.println(totalTime + "s");
    	System.out.println(d.numEdges() + " edges");
    	System.out.println(d.numNodes() + " nodes");
    }
}