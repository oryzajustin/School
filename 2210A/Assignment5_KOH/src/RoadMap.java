import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

/**
 * 
 * @author Justin Koh
 * 2210A Assignment 5
 * December 6, 2016
 *
 */
public class RoadMap 
{
	private Graph G;
	private int start;
	private int end;
	private int initialMoney;
	private int width;
	private int length;
	private int toll;
	private int gain;
	char[][] map;
	
	//Constructor for building a graph from the content of the input file;
	//this graph represents the road map. If the input file does not exist, this method should throw a
	//MapException. Read below to learn about the format of the input file.
	public RoadMap(String inputFile) throws MapException, IOException
	{
		try
		{
			BufferedReader bufRead = new BufferedReader(new FileReader(inputFile)); 
			bufRead.readLine();
			this.start = Integer.parseInt(bufRead.readLine());
			this.end = Integer.parseInt(bufRead.readLine());
			this.initialMoney = Integer.parseInt(bufRead.readLine());
			this.width = Integer.parseInt(bufRead.readLine());
			this.length = Integer.parseInt(bufRead.readLine());
			this.toll = Integer.parseInt(bufRead.readLine());
			this.gain = Integer.parseInt(bufRead.readLine());
			this.G = new Graph(this.width * this.length);
			this.map = new char[2*this.length - 1][2*this.width - 1];
		
			for(int i = 0; i < 2*this.length - 1; i++)
			{
				String eachLine = bufRead.readLine();
				this.map[i] = eachLine.toCharArray();
			}
		
			int nodeNumber = 0;
			for(int row = 0; row < 2*this.length - 1; row++)//for each row starting at 0
			{
				for(int col = 0; col < 2*this.width - 1; col++)//for each column starting at 0
				{
					//check what you're at and if it's an intersection do the following:
					if(this.map[row][col] == '+')
					{
						//if it's the bottom right corner, don't do anything
						if(row == 2*this.length - 2 && col == 2*this.width - 2)
						{
							break;
						}
						//if it's the bottom row, only connect edges to the right
						else if(row == 2*this.length - 2)
						{
							//if the char to the right is an F (free road)
							if(this.map[row][col+1] == 'F')
							{
								Node currnode = new Node(nodeNumber);
								Node rightnode = new Node(nodeNumber+1);
								this.G.insertEdge(currnode, rightnode, 0);
							}
							//if the char to the right is a C (compensation road)
							if(this.map[row][col+1] == 'C')
							{
								Node currnode = new Node(nodeNumber);
								Node rightnode = new Node(nodeNumber+1);
								this.G.insertEdge(currnode, rightnode, -1);
							}
							//if the char to the right is a T (toll road)
							if(this.map[row][col] == 'T')
							{
								Node currnode = new Node(nodeNumber);
								Node rightnode = new Node(nodeNumber+1);
								this.G.insertEdge(currnode, rightnode, 1);
							}
						}
						//if it's the last column, only connect to nodes below
						else if(col == 2*this.width - 2)
						{
							//if the road below is free
							if(this.map[row+1][col] == 'F')
							{
								Node currnode = new Node(nodeNumber);
								Node lowernode = new Node(nodeNumber+this.width);
								this.G.insertEdge(currnode, lowernode, 0);
							}
							//if road below is compensation
							if(this.map[row+1][col] == 'C')
							{
								Node currnode = new Node(nodeNumber);
								Node lowernode = new Node(nodeNumber+this.width);
								this.G.insertEdge(currnode, lowernode, -1);
							}
							//if road below is toll
							if(this.map[row+1][col] == 'T')
							{
								Node currnode = new Node(nodeNumber);
								Node lowernode = new Node(nodeNumber+this.width);
								this.G.insertEdge(currnode, lowernode, 1);
							}
						}
						//if it's any internal intersection excluding the last column, last row
						//and last index
						else
						{
							//if the char to the right is an F (free road)
							if(this.map[row][col+1] == 'F')
							{
								Node currnode = new Node(nodeNumber);
								Node rightnode = new Node(nodeNumber+1);
								this.G.insertEdge(currnode, rightnode, 0);
							}
							//if the char to the right is a C (compensation road)
							if(this.map[row][col+1] == 'C')
							{
								Node currnode = new Node(nodeNumber);
								Node rightnode = new Node(nodeNumber+1);
								this.G.insertEdge(currnode, rightnode, -1);
							}
							//if the char to the right is a T (toll road)
							if(this.map[row][col] == 'T')
							{
								Node currnode = new Node(nodeNumber);
								Node rightnode = new Node(nodeNumber+1);
								this.G.insertEdge(currnode, rightnode, 1);
							}
							//if the road below is free
							if(this.map[row+1][col] == 'F')
							{
								Node currnode = new Node(nodeNumber);
								Node lowernode = new Node(nodeNumber+this.width);
								this.G.insertEdge(currnode, lowernode, 0);
							}
							//if road below is compensation
							if(this.map[row+1][col] == 'C')
							{
								Node currnode = new Node(nodeNumber);
								Node lowernode = new Node(nodeNumber+this.width);
								this.G.insertEdge(currnode, lowernode, -1);
							}
							//if road below is toll
							if(this.map[row+1][col] == 'T')
							{
								Node currnode = new Node(nodeNumber);
								Node lowernode = new Node(nodeNumber+this.width);
								this.G.insertEdge(currnode, lowernode, 1);
							}
						}
						nodeNumber++;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new MapException("No such file exists.");
		}
		
	}
	//Returns the graph representing the road map.
	public Graph getGraph()
	{
		return this.G;
	}
	//Returns the starting node (specified in the input file).
	public int getStartingNode()
	{
		return this.start;
	}
	// Returns the destination node
	public int getDestinationNode()
	{
		return this.end;
	}
	//Returns the initial amount of money
	public int getInitialMoney()
	{
		return this.initialMoney;
	}
	//Returns a Java Iterator
	//containing the nodes along the path from the start node to the destination node, if such a
	//path exists. The amount specified in initialMoney plus the money earned by passing through the
	//compensation roads must be enough to pay for all the toll roads. If the path does not exist, this method
	//returns the value null. For example for the road map described below the Iterator returned by this
	//method should contain the nodes 0, 1, 5, 6, and 10.
	public Iterator findPath(int start, int destination, int initialMoney)
	{
		Stack<Node> stack = dfs(start, destination, initialMoney);
		Iterator itr = stack.iterator();
		return itr;
	}
	//this recursive statement should return a stack of nodes containing the start on the top
	//and the end on the bottom. It should and would be working properly, but for some reason
	//the edge is not updated after each recursive call, resulting in a stack overflow error.
	private Stack<Node> dfs(int start, int destination, int initialMoney)
	{
		try
		{
			
		//curr is the start node
		Node curr = this.G.getNode(start);
		//end is the destination node
		Node end = this.G.getNode(destination);
		//set curr to visited
		curr.setMark(true);
		//iterator containing all of the current node's incident edges
		Iterator<Edge> itr = this.G.incidentEdges(curr);
		//set the remaining money to the initial money
		int remainingMoney = initialMoney;
		
		//if the current node is the end
		if(curr == end)
		{
			//create a new stack and push the current node onto it
			Stack<Node> stack = new Stack<Node>();
			stack.push(curr);
			return stack;
		}
		//if the current node is not the end node
		else
		{
			//for every edge of the current node, do DFS if it meets the conditions
			while(itr.hasNext())
			{
				Edge edge = itr.next();
				if(edge.getType() == 1 && remainingMoney - this.toll >= 0 && edge.secondEndpoint().getMark() == false)
				{
					//if taking toll booth will not bankrupt you, and the edge has not been explored call DFS
					//new remaining amount of money is how much you had minus the toll cost
					remainingMoney = remainingMoney - this.toll;
					int nextpoint = edge.secondEndpoint().getName();
					Stack<Node> stack = dfs(nextpoint, destination, remainingMoney);
					if(stack != null)
					{
						stack.push(curr);
						return stack;
					}
				}
				if(edge.getType() == -1 && edge.secondEndpoint().getMark() == false)
				{
					//if the edge has not been explored,and if the edge is a compensation road
					//new remaining amount of money is how much you had plus the amount of money gained from the compensation road
					remainingMoney = remainingMoney - this.gain;
					int nextpoint = edge.secondEndpoint().getName();
					Stack<Node> stack = dfs(nextpoint, destination, remainingMoney);
					if(stack != null)
					{
						stack.push(curr);
						return stack;
					}
				}
				if(edge.getType() == 0 && edge.secondEndpoint().getMark() == false)
				{
					//if the edge is a free road and has not been visited
					int nextpoint = edge.secondEndpoint().getName();
					Stack<Node> stack = dfs(nextpoint, destination, remainingMoney);
					if(stack != null)
					{
						stack.push(curr);
						return stack;
					}
				}
				
			}
			curr.setMark(false);
			return null;
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
