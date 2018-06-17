
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;


class Edge{
	private String fromNode, toNode;
	private double weight; 
	
	public Edge( String from, String to, double w ){
		fromNode = from;
		toNode = to;
		weight = w;
		}
	
	public String get_from_node(){ return fromNode; }
	public String get_to_node(){ return toNode; }
	public Double get_weight(){ return new Double( weight ); }
	
	}


class Graph {
	ArrayList< Edge > edges;
	ArrayList< String > nodes;
	
	public Graph(){
		edges = new ArrayList<Edge>();
		nodes = new ArrayList<String>();
		}
	
	public void create_node( String node ){
		nodes.add( node );
		}
	public void connect( String nodeA, String nodeB, double weight ){
		edges.add( new Edge( nodeA, nodeB, weight ) );
		}
	
	public ArrayList<String> get_nodes(){ return nodes; }
	
	public ArrayList<Edge> get_connected( String node ){
		ArrayList<Edge> out = new ArrayList<Edge>();
		
		for( int i = 0 ; i < edges.size() ; ++i ){
			if( edges.get(i).get_from_node() == node ){
				out.add( edges.get(i) );
				}
			}
		
		return out;
		}
	
	}


public class Djikstra {


	
	static public ArrayList<String> get_shortest_path( Graph graph, String start, String stop ){
		ArrayList<String> nodes = graph.get_nodes();
		ArrayList<String> Q = new ArrayList<String>();
		ArrayList<String> shortest_path = new ArrayList<String>();
		HashMap<String, Double> dist = new HashMap<String, Double>();
		HashMap<String, String> previous = new HashMap<String, String>();
		
		for( String node : nodes ){
			dist.put( node, 1e99 ); 
			previous.put( node, "" );
			Q.add( node );
			}
				
		
		dist.put( start, 0.0 );
		
		
		while( Q.size() > 0 ){
			String nearest_node = new String();
			double smallest_distance = 1e99;
			
			for( String node : Q ){
				
				if( dist.get( node ) < smallest_distance ){
					smallest_distance = dist.get( node );
					nearest_node = node;
					}
				} 
			if( smallest_distance > 1e90 || nearest_node == "" ){
				
				break;
				}
				
			
			Q.remove( Q.indexOf( nearest_node ) );
			
			ArrayList<Edge> edges = graph.get_connected( nearest_node ); 
			
			
			for( Edge edge : edges ){
				
				double alt = dist.get( nearest_node ) + edge.get_weight();
				
				
				if( alt < dist.get( edge.get_to_node() ) ){
					dist.put( edge.get_to_node(), alt );
					previous.put( edge.get_to_node(), edge.get_from_node() );
					}
				}
			
			}
		
		String position = stop;
	
		while( previous.containsKey( position ) ){
			shortest_path.add( position );
			position = previous.get( position );
			}

	
		Collections.reverse( shortest_path );
		
		return shortest_path;
		}
	
	static public void main( String[] args ){

		
		Graph graafi = new Graph();
		

		
		graafi.create_node( "A" );
		graafi.create_node( "B" );
		graafi.create_node( "C" );
		graafi.create_node( "D" );
		graafi.create_node( "E" );
		graafi.create_node( "F" );
		graafi.create_node( "G" );
		

		graafi.connect( "A", "B", 1.0 ); 
		graafi.connect( "A", "C", 2.0 ); 
		graafi.connect( "C", "D", 3.0 ); 
		graafi.connect( "B", "E", 4.0 ); 
		graafi.connect( "D", "E", 2.0 ); 
		graafi.connect( "E", "F", 2.0 ); 
		graafi.connect( "F", "G", 2.0 ); 
		
		ArrayList<String> path = get_shortest_path( graafi, "A", "G" );
		
		System.out.print( "path = " );
		for( String node : path ){
			System.out.print( node + " " );
			}
		System.out.println("");
		}
	
	
	
	
	
	
	
	}