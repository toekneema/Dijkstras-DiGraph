package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DiGraph implements DiGraphInterface {
	
	public Map<String, Vertex> vertices;
	public Set<Long> edgeIDs, vertexIDs;
	public long numNodes;
	public long numEdges;
	static final long INFINITY = 1000000000;
	
	public DiGraph ( ) { // default constructor
		
		vertices = new HashMap<String,Vertex>();
		edgeIDs = new HashSet<Long>();
		vertexIDs = new HashSet<Long>();
		numNodes = 0;
		numEdges = 0;
	}
	
	public long numNodes(){
		return numNodes;
	}
	
	public long numEdges(){
		return numEdges;
	}
	
	public boolean addNode(long idNum, String label){
		
		
		if(idNum < 0) {
			return false;
		}
		
		if(vertices.containsKey(label) || vertexIDs.contains(idNum)) {
			return false;
		}
		
		vertices.put(label, new Vertex(idNum, label));
		vertexIDs.add(idNum);
		numNodes++;
		return true;
	}
	
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel){
		
		if(idNum < 0){
			return false;
		}
		
		Edge e = new Edge(idNum, vertices.get(sLabel), vertices.get(dLabel), weight, eLabel);
		
		if(edgeIDs.contains(idNum)){
			return false;
		}
		
		if(!(vertices.containsKey(sLabel) && vertices.containsKey(dLabel))){
			return false;
		}
		
		if(vertices.get(sLabel).outEdges.containsKey(dLabel)){
			return false;
		}
		
		edgeIDs.add(idNum);
		vertices.get(sLabel).addOutEdge(dLabel,e);
		vertices.get(dLabel).addInEdge(sLabel,e);
		
		numEdges++;
		return true;
	}
	
	public boolean delNode(String label){
		
		if(!vertices.containsKey(label)){
			return false;
		}
		
		for(String key: vertices.get(label).inEdges.keySet()){
			edgeIDs.remove(vertices.get(label).inEdges.get(key).id);
			
			vertices.get(label).inEdges.get(key).sn.removeOutEdge(label);
			numEdges--;
		}
		
		for(String key: vertices.get(label).outEdges.keySet()){
			edgeIDs.remove(vertices.get(label).outEdges.get(key).id);
			
			vertices.get(label).outEdges.get(key).dn.removeInEdge(label);
			numEdges--;
		}
		
		vertexIDs.remove(vertices.get(label).id);
		vertices.remove(label);
		numNodes--;
		return true;
	}
	
	public boolean delEdge(String sLabel, String dLabel){
		
		if(!(vertices.containsKey(sLabel) && vertices.containsKey(dLabel))){
			return false;
		}
		
		if(!vertices.get(sLabel).outEdges.containsKey(dLabel)){
			return false;
		}
		
		edgeIDs.remove(vertices.get(sLabel).outEdges.get(dLabel).id);
		
		vertices.get(sLabel).removeOutEdge(dLabel);
		vertices.get(dLabel).removeInEdge(sLabel);
		
		numEdges--;
		return true;	
	}
	
	
	public ShortestPathInfo[] shortestPath(String label){
		MinBinHeap priorityQueue = new MinBinHeap();
		ShortestPathInfo[] path = new ShortestPathInfo[(int) numNodes];
		
		vertices.get(label).dist = 0;
		priorityQueue.insert(new EntryPair(label, (int) vertices.get(label).dist ));
		
		for(String key: vertices.keySet()){
			if(!key.equals(label)){
				vertices.get(key).dist = INFINITY;
				priorityQueue.insert(new EntryPair(key, (int) vertices.get(key).dist));
			}
		}
		
		while(priorityQueue.size() != 0){
			
			Vertex v = vertices.get(priorityQueue.getMin().value);
			priorityQueue.delMin();
			
			for(String key: v.outEdges.keySet()){
				
				long smallWeight = v.dist + v.outEdges.get(key).weight;
				long oldWeight = v.outEdges.get(key).dn.dist;
				
				if(smallWeight < oldWeight){
					
					v.outEdges.get(key).dn.dist = smallWeight;
					priorityQueue.insert(new EntryPair(v.outEdges.get(key).dn.label, (int) v.outEdges.get(key).dn.dist));
				}
			}
			
		}
		int count = 0;
		for(String key: vertices.keySet()){
			
			if(vertices.get(key).dist == INFINITY){
				path[count] = new ShortestPathInfo(key,-1);
			}else{
				path[count] = new ShortestPathInfo(key, vertices.get(key).dist);
			}
			
			count++;
		}
		
		return path;
	}
	
	public void print(){
		
		for(String key: vertices.keySet()){
			Vertex v = vertices.get(key);
			
			System.out.println("("+ v.id + ")" + v.label);
			
			for(String edgeKey: v.outEdges.keySet()){
				System.out.println("    (" + v.outEdges.get(edgeKey).id + ")--" + ((v.outEdges.get(edgeKey).label == null) ? v.outEdges.get(edgeKey).weight: v.outEdges.get(edgeKey).label + "," + v.outEdges.get(edgeKey).weight) + "--> " + v.outEdges.get(edgeKey).dn.label );
			}
		}
		
	}
}