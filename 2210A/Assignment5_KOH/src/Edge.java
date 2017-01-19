/**
 * 
 * @author Justin Koh
 * 2210A Assignment 5
 * December 6, 2016
 *
 */
public class Edge 
{
	private Node first;
	private Node second;
	private int type;
	private String label;
	
	/**
	 * @param first node u
	 * @param second node v
	 * @param int type and can be: 
	 * 0 (if the edge represents a free road), 
	 * 1 (if the edge represents a toll road), 
	 * or -1 (if the edge represents a compensation road)
	 */
	public Edge(Node u, Node v, int type)
	{
		this.first = u;
		this.second = v;
		this.type = type;
		this.label = "";
	}
	public Node firstEndpoint()
	{
		return this.first;
	}
	public Node secondEndpoint()
	{
		return this.second;
	}
	public int getType()
	{
		return this.type;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	public String getLabel()
	{
		return label;
	}
}
