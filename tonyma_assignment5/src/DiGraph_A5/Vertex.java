package DiGraph_A5;

import java.util.Map;
import java.util.HashMap;

//Vertex class to make data management easier
public class Vertex {
	
	public long id;
	public String label;
	public Map<String, Edge> inEdges;
	public Map<String, Edge> outEdges;
	public long dist;						//Distance from source to this vertex for dijkstra's algo
	
	public Vertex(long id, String label){
		
		this.id = id;
		this.label = label;
		
		this.inEdges = new HashMap<String,Edge>();
		this.outEdges = new HashMap<String,Edge>();
	}
	
	//Add an edge going into this node
	public void addInEdge(String sLabel, Edge e){
		inEdges.put(sLabel, e);
	}
	
	//Add an edge going out of this node
	public void addOutEdge(String dLabel, Edge e){
		outEdges.put(dLabel, e);
	}
	
	//Remove an edge going out of this node
	public void removeOutEdge(String dLabel){
		outEdges.remove(dLabel);
	}
	
	//remove an edge going into this node
	public void removeInEdge(String sLabel){
		inEdges.remove(sLabel);
	}
	
	//find the number of edges going into this node
	public int findInDegree(){
		return inEdges.size();
	}
}