/**
 * 
 * @author Justin Koh
 * ID: 250837810
 * class ID: 78
 *
 */
public class OrderedDictionary implements OrderedDictionaryADT
{
	private Node<Record> root;

	public OrderedDictionary()
	{
		Record empty = new Record(null,"");
		root = new Node<Record>(empty);
	}
	/**
	 * @param Key k
	 * @return the record in the node
	 * @return null if the record doesn't exist
	 */
	public Record find(Key k)
	{
		Node<Record> s = treeSearch(k, this.root);
		if(s.getElement().getKey() == null) // if the node DNE
		{
			return null;
		}
		else //if the node exists
		{
			return s.getElement();
		}
	}
	/**
	 * @param Record r
	 * inserts a node containing a record into the correct position
	 * in the tree
	 */
	public void insert(Record r) throws DictionaryException
	{
		Key recordKey = r.getKey();
		Node<Record> s = treeSearch(recordKey, this.root);
		if(this.root.getElement().getKey() == null)// if the tree is empty, create a new node containing the record
		{
			this.root = newNode(r);
		}
		else if(s.getElement().getKey() != null) //if the record exists already
		{
			throw new DictionaryException("The record already exists.");
		}
		else //if the record doesn't exist already
		{
			if(s.getParent().getElement().getKey().compareTo(recordKey) < 0)
			{
				Node<Record> setNode = newNode(r);
				s.getParent().setRight(setNode);
				setNode.setParent(s.getParent());
			}
			else
			{
				Node<Record> setNode = newNode(r);
				s.getParent().setLeft(setNode);
				setNode.setParent(s.getParent());
			}
		}
	}
	public void remove(Key k) throws DictionaryException
	{
		Node<Record> s = treeSearch(k, this.root);
		if(s == this.root && s.getLeft().getElement().getKey() == null && s.getRight().getElement().getKey() == null)
		{
			Record empty = new Record(null, "");
			this.root.setElement(empty);
		}
		else if(s.getElement().getKey() == null)
		{
			throw new DictionaryException("The record does not exist.");
		}
		else
		{
			if(s.getLeft().getElement().getKey() != null && s.getRight().getRight().getElement().getKey() != null)
			{
				//replace the node's record with the successor's record and remove the
				//successor node
				Record succ = successor(s.getElement().getKey());
				Key succKey = succ.getKey();
				String succData = succ.getData();
				Record nodeRec = s.getElement();
				nodeRec.setRecord(succKey, succData);
				//remove the successor node
				Node<Record> removeThis = treeSearch(succKey, this.root);
				if(removeThis.getLeft().getElement().getKey() == null && removeThis.getRight().getElement().getKey() == null)
				{
					removeThis.getParent().setRight(removeThis.getRight());
				}
			}
			else
			{
				if(s.getLeft().getElement().getKey() == null)
				{
					//remove the node and the left leaf
					Node<Record> parent = s.getParent();
					if(parent.getLeft() == s)
					{
						parent.setLeft(s.getRight());
						s.getRight().setParent(parent);
					}
					else if(parent.getRight() == s)
					{
						parent.setRight(s.getRight());
						s.getRight().setParent(parent);
					}
				}
				else if(s.getRight().getElement().getKey() == null)
				{
					//remove the node and the right leaf
					Node<Record> parent = s.getParent();
					if(parent.getLeft() == s)
					{
						parent.setLeft(s.getLeft());
						s.getLeft().setParent(parent);
					}
					else if(parent.getRight() == s)
					{
						parent.setRight(s.getLeft());
						s.getLeft().setParent(parent);
					}

				}
			}
		}
	}
	/**
	 * @param key k
	 * @return the record of the successor node, if it exists
	 * successor is the next largest value in the tree.
	 */
	public Record successor(Key k)
	{
		if(this.root.getLeft().getElement().getKey() == null && this.root.getRight().getElement().getKey() == null)
		{
			return null;//if the tree has only the root and null children, there is no successor
		}
		// if the node I want the successor of has a right subtree
		Node<Record> nodeIWantSuccessorOf = treeSearch(k, this.root);
		if(nodeIWantSuccessorOf.getRight().getElement().getKey() != null)
		{
			//look for the left most node in the right subtree.
			Node<Record> sRightChild = nodeIWantSuccessorOf.getRight();
			while(sRightChild.getLeft().getElement().getKey() != null)
			{
				sRightChild = sRightChild.getLeft();
			}
			return sRightChild.getElement();
		}
		// if the root does not have a right subtree
		else
		{
			Node<Record> sParent = nodeIWantSuccessorOf.getParent();
			while(sParent.getElement().getKey() != null && sParent.getRight() == nodeIWantSuccessorOf)
			{
				nodeIWantSuccessorOf = sParent;
				sParent = nodeIWantSuccessorOf.getParent();
			}
			return sParent.getElement();
		}
	}
	/**
	 * @param Key k
	 * @return the predecessor, if it exists
	 * predecessor is the next smallest value in the tree.
	 */
	public Record predecessor(Key k)
	{
		//if the tree has only the root and null children, there is no predecessor
		if(this.root.getLeft().getElement().getKey() == null && this.root.getRight().getElement().getKey() == null)
		{
			return null;
		}
		//if the node I want the predecessor of has a left sub tree.
		Node<Record> nodeIWantPredecessorOf = treeSearch(k, this.root);
		if(nodeIWantPredecessorOf.getLeft().getElement().getKey() != null)
		{
			//look for the right most node in the left subtree
			Node<Record> pLeftChild = nodeIWantPredecessorOf.getLeft();
			while(pLeftChild.getRight().getElement().getKey() != null)
			{
				pLeftChild = pLeftChild.getRight();
			}
			return pLeftChild.getElement();
		}
		//if the root does not have a left subtree
		else
		{
			Node<Record> pParent = nodeIWantPredecessorOf.getParent();
			while(pParent.getElement().getKey() != null && pParent.getLeft() == nodeIWantPredecessorOf)
			{
				nodeIWantPredecessorOf = pParent;
				pParent = nodeIWantPredecessorOf.getParent();
			}
			return pParent.getElement();
		}
	}
	public Record smallest() //go all the way to the left until you
	//reach a leaf node
	{
		Node<Record> temp = this.root;
		while(temp.getLeft().getElement().getKey() != null)
		{
			 temp = temp.getLeft();
		}
		return temp.getElement();
	}
	public Record largest()//go all the way to the right until you
	//reach a leaf node
	{
		Node<Record> temp = this.root;
		while(temp.getRight().getElement().getKey() != null)
		{
			temp = temp.getRight();
		}
		return temp.getElement();
	}
	/**
	 * 
	 * @param Key k
	 * @param Node type Record root
	 * @return the node that contains the key, if it exists in the tree
	 */
	private Node<Record> treeSearch(Key k, Node<Record> root)
	{
		if(root.getElement().getKey() == null || k.compareTo(root.getElement().getKey()) == 0)
		{
			return root;
		}
		else
		{
			if(k.compareTo(root.getElement().getKey()) < 0)
			{
				return treeSearch(k, root.getLeft());
			}
			else
			{
				return treeSearch(k, root.getRight());
			}
		}
	}
	private Node<Record> newNode(Record r)
	{
		Record empty = new Record(null, "");
		Node<Record> node = new Node<Record>(r);
		Node<Record> emptyNode = new Node<Record>(empty);
		node.setLeft(emptyNode);
		node.setRight(emptyNode);
		node.setParent(treeSearch(r.getKey(), this.root));
		node.getLeft().setParent(node);
		node.getRight().setParent(node);
		return node;
	}
}
