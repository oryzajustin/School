/**
 * 
 * @author Justin Koh
 * ID: 250837810
 * class ID: 78
 * @param <T>
 */
public class Node<T> 
{
	private Node<T> left;
	private Node<T> right;
	private T element;
	private Node<T> parent;
	public Node(T element)
	{
		parent = null;
		left = null;
		right = null;
		this.element = element;
	}
	public void setParent(Node<T> parent)
	{
		this.parent = parent;
	}
	public Node<T> getParent()
	{
		return parent;
	}
	public Node<T> getLeft()
	{
		return left;
	}
	public Node<T> getRight()
	{
		return right;
	}
	public boolean hasLeft()
	{
		if(left.getElement() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean hasRight()
	{
		if(right.getElement() != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setLeft(Node<T> left)
	{
		this.left = left;
	}
	public void setRight(Node<T> right)
	{
		this.right = right;
	}
	public T getElement()
	{
		return this.element;
	}
	public void setElement(T element)
	{
		this.element = element;
	}
	public boolean isInternal()
	{
		if(hasLeft() && hasRight())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
