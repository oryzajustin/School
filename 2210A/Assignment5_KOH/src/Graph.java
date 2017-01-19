import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * 
 * @author Justin Koh
 * 2210A Assignment 5
 * December 6, 2016
 *
 */
public class Graph implements GraphADT
{
	private int numberOfNodes = 0;
	private Node first;
	private Node second;
	private Edge[][] adjMatrix;
	private Node[] array;
	
	//constructor for the Graph making an adjacency matrix
	//makes an array storing n objects, creates and stores n nodes into it.
	public Graph(int n)
	{
		this.adjMatrix = new Edge[n][n];
		this.array = new Node[n];
		for(int i = 0; i < n; i++)
		{
			Node node = new Node(i);
			this.array[i] = node;
			numberOfNodes = numberOfNodes + 1;
		}
	}
	//try and find the node with the existing name in the array
	//if no node exists, throw an exception
	public Node getNode(int name) throws GraphException
	{
		if(name >= numberOfNodes)
		{
			throw new GraphException("The node with the requested name does not exist.");
		}
		else
		{
			return this.array[name];
		}
	}
	//Adds an edge of the given type connecting u and v 
	//The label of the edge is the empty String. This method throws a GraphException if either
	//node does not exist or if in the graph there is already an edge connecting the given nodes.
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException
	{
		if(u.getName() >= numberOfNodes || v.getName() >= numberOfNodes)
		{
			throw new GraphException("Cannot create an edge unless both nodes exist.");
		}
		else if(this.adjMatrix[u.getName()][v.getName()] != null)
		{
			throw new GraphException("An edge already exists here.");
		}
		else
		{
			Edge edge = new Edge(u, v, edgeType);
			this.adjMatrix[edge.firstEndpoint().getName()][edge.secondEndpoint().getName()] = edge;
			this.adjMatrix[edge.secondEndpoint().getName()][edge.firstEndpoint().getName()] = edge;
		}	
	}
	//Returns a Java Iterator storing all the edges incident on node
	//u. It returns null if u does not have any edges incident on it.
	public Iterator<Edge> incidentEdges(Node u) throws GraphException
	{
		if(u.getName() >= numberOfNodes)
		{
			throw new GraphException("node does not exist.");
		}
		Edge[] nodeRowInMatrix = this.adjMatrix[u.getName()];
		Edge[] edgeAndNull = new Edge[numberOfNodes-1];
		int count = 0;
		for(int i = 0; i < numberOfNodes; i++)
		{
			Edge check = nodeRowInMatrix[i];
			if(check != null)
			{
				edgeAndNull[count] = check;
				count = count + 1;
			}
		}
		if(count == 0)
		{
			return null;
		}
		else
		{
			Edge[] edges = new Edge[count];
			int k = 0;
			while(edgeAndNull[k] != null)
			{
				edges[k] = edgeAndNull[k];
				k = k + 1;
			}
			Iterator<Edge> iter = Arrays.stream(edges).iterator();
			return iter;
		}	
	}
	//Returns the edge connecting nodes u and v. This method
	//throws a GraphException if there is no edge between u and v.
	public Edge getEdge(Node u, Node v) throws GraphException
	{
		if(u.getName() >= numberOfNodes || v.getName() >= numberOfNodes)
		{
			throw new GraphException("nodes do not exist.");
		}
		else if(this.adjMatrix[u.getName()][v.getName()] == null)
		{
			throw new GraphException("no edge exists.");
		}
		else
		{
			return this.adjMatrix[u.getName()][v.getName()];
		}
	}
	//Returns true if nodes u and v are adjacent; it returns
	//false otherwise.
	public boolean areAdjacent(Node u, Node v) throws GraphException
	{
		if(u.getName() >= numberOfNodes || v.getName() >= numberOfNodes)
		{
			throw new GraphException("Node does not exist");
		}
		if(this.adjMatrix[u.getName()][v.getName()] != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
