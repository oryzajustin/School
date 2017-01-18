/**
 * 
 * @author Justin Koh
 * CS 2214
 * October 19, 2016
 * 250837810
 *
 */
public class Dictionary implements DictionaryADT
{
	private int hashtable_size;
	private Node<ConfigData>[] hashTable;
	
	public Dictionary(int size)
	{
		this.hashtable_size = size;
		this.hashTable = new Node[size];
	}

	private int hashfx(String config)
	{
		//get the board configuration
		String boardString = config;
		int number = 0;
		int x = 0;
		//loop through the string and multiply the ascii value of the char by
		//i, then add a prime number 11
		for(int i=0; i < boardString.length()-1; i++)
		{
			x = x + (int)boardString.charAt(i)^i;
			number = number + (((int)boardString.charAt(i)*x) + 7);
		}
		int index = number % hashtable_size;
		return index;
	}
	
	/*
	 * putting a pair through that contains a string and score
	 * putting pair into a node
	 * mapping the node into the position in the hash table
	 * inserting the node into the position
	 */
	public int insert(ConfigData pair) throws DictionaryException 
	{
		//establish a first node "head"
		Node<ConfigData> head = new Node<ConfigData>(pair);
		//put the configdata pair into the node
		head.setElement(pair);
		//set the index as a variable
		int index = hashfx(pair.getConfig());
		//check to see if the configuration you are trying to put into the hashtable
		//already exists,throw exception
		if(find(pair.getConfig()) != -1)
		{
			throw new DictionaryException("The configuration already exists in the dictionary.");
		}
		//map the node to the appropriate position in the hash table
		if(hashTable[index] == null)//if the slot in the table is empty,
			//insert the node in that position in the table
		{
			hashTable[index] = head;
			return 0;
		}
		else //if the slot already has something in it,
			//loop through all of the nodes until the next is null
			//and set the next of the last node to head
		{
			Node<ConfigData> curr = hashTable[index];//make a variable to keep track of
			//which node you are at.
			while(curr.getNext() != null)
			{
				//variables to make it more organized
				//currConfig and headConfig being the board configurations
				//that are being compared.
				String currConfig = curr.getElement().getConfig();
				String headConfig = head.getElement().getConfig();
				curr = curr.getNext();
				
			}
			//set the last node in the list's next to head
			curr.setNext(head);
			return 1;
		}
	}
	
	//remove method removes a certain board configuration
	//from the hash table
	public void remove(String config) throws DictionaryException
	{
		//make variable for index
		int index = hashfx(config);
		//go to the index of the board configuration
		//and compare the config you passed through the method
		//with the configs in the linked list
		Node<ConfigData> curr = hashTable[index];//pointer to the current node in the linked list
		Node<ConfigData> prev = null;//pointer to the previous node in the linked list
		while(curr != null)
		{
			String currConfig = curr.getElement().getConfig();
			if(prev == null && currConfig.equals(config))
			{
				curr = curr.getNext();
				hashTable[index] = curr;
			}
			else
			{
				prev = curr;
				curr = curr.getNext();
				if(currConfig.equals(config))
				{
					prev.setNext(curr.getNext());
				}
			}
		}
		if(curr == null)
		{
			throw new DictionaryException("The board configuration is not in the hash table.");
		}
	}
	
	/*
	 * @return score of config or -1 if the config DNE
	 */
	public int find(String config) 
	{
		int index = hashfx(config);
		Node<ConfigData> curr = hashTable[index];
		if(curr == null)
		{
			return -1;
		}
		else
		{
			while(curr != null)
			{
				if(curr.getElement().getConfig().equals(config))
				{
					return curr.getElement().getScore();
				}
				else
				{
					if(curr.getNext() != null)
					{
						curr = curr.getNext();
					}
					else
					{
						return -1;
					}
				}
			}
		}
		return -1;
	}
	

}
