/**
 * 
 * @author Justin Koh
 * ID: 250837810
 * class ID: 78
 */
public class Key 
{
	private String word;
	private int type;
	
	public Key(String word, int type)
	{
		this.word = word;
		this.type = type;
	}
	
	/**
	 * @return word in the key
	 */
	public String getWord()
	{
		return word;
	}
	/**
	 * 
	 * @return integer type in the key
	 */
	public int getType()
	{
		return type;
	}
	
	/**
	 * @param Key k 
	 * @return 0 if the keys are the same, -1 if k comes before b, 1 otherwise.
	 */
	public int compareTo(Key k)
	{
		if(k.getType() == this.type && k.getWord().equals(this.word)) //if the keys are the same
		{
			return 0;
		}
		else if(this.word.compareTo(k.getWord()) < 0 || this.type < k.getType() && this.word == k.getWord())// if the key is smaller than the node's key
		{
			return -1;
		}
		else //if the key is greater than the node key
		{
			return 1;
		}
	}
}
