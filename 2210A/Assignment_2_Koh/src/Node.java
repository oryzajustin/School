
public class Node<T> 
{	
	private Node<T> next;
	private T element;
	
	public Node(T element)
	{
		this.next = null;
		this.element = element;
	}
	public Node<T> getNext()
	{
		return next;
	}
	public T getElement()
	{
		return element;
	}
	public void setNext(Node<T> next)
	{
		this.next = next;
	}
	public void setElement(T element)
	{
		this.element = element;
	}
}
