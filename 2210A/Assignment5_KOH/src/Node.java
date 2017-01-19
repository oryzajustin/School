/**
 * 
 * @author Justin Koh
 * 2210A Assignment 5
 * December 6, 2016
 *
 */
public class Node 
{
	private int name;
	private boolean mark;
	
	public Node(int name)
	{
		this.name = name;
		mark = false;
	}
	
	public void setMark(boolean bool)
	{
		this.mark = bool;
	}
	
	public int getName()
	{
		return name;
	}
	
	public boolean getMark()
	{
		return mark;
	}
}
