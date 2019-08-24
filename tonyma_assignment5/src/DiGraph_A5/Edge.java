package DiGraph_A5;

//Edge Class to make data storage easier
public class Edge {
	
	public String label;
	public Vertex sn;
	public Vertex dn;
	public long weight;
	public long id;
	
	public Edge(long id, Vertex sn, Vertex dn, long weight, String label){
		
		this.id = id;			//Edge id num
		this.sn = sn;			//Source vertex
		this.dn = dn;			//Destination vertex
		this.weight = weight;	//Weight of the edge
		this.label = label;		//Edge label
		
	}
}
