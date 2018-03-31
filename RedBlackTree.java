package sjsu.patel.cs146.project3;

public class RedBlackTree<Key extends Comparable<Key>> {	
	static RedBlackTree.Node<String> root; 
	private  RedBlackTree.Node<String> node; 
	public static class Node<Key extends Comparable<Key>> { //changed to static 
		
		  Key key;  		  
		  Node<String> parent;
		  Node<String> leftChild;
		  Node<String> rightChild;
		  boolean isRed;
		  int color;
		  
		  public Node(Key data){
			  this.key = data;
			  leftChild = null;
			  rightChild = null;
		  }		
		  
		  public int compareTo(Node<Key> n){ 	//this < that  <0
		 		return key.compareTo(n.key);  	//this > that  >0
		  }
		  
		  public boolean isLeaf(){
			  if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
			  if (this.equals(root)) return false;
			  if (this.leftChild == null && this.rightChild == null){
				  return true;
			  }
			  return false;
		  }	  
	}

	 public boolean isLeaf(RedBlackTree.Node<String> n){
		  if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
		  if (n.equals(root)) return false;
		  if (n.leftChild == null && n.rightChild == null){
			  return true;
		  }
		  return false;
	  }
	
	public interface Visitor<Key extends Comparable<Key>> {
		/**
		This method is called at each node.
		@param n the visited node
		*/
		void visit(Node<Key> n);  
	}
	
	public void visit(Node<Key> n){
		System.out.println(n.key);
	}
	
	public void printTree(){  //preorder: visit, go left, go right
		RedBlackTree.Node<String> currentNode = root;	
		printTree(currentNode);
	}
	
	public void printTree(RedBlackTree.Node<String> node){
		System.out.print(node.key);
		if (node.isLeaf()){
			return;
		}
		printTree(node.leftChild);
		printTree(node.rightChild);
	}
	
	// place a new node in the RB tree with data the parameter and color it red. 
	public void addNode(String data) { // this < that <0. this > that >0
		// fill
		RedBlackTree.Node<String> node = new RedBlackTree.Node<String>(data);
		RedBlackTree.Node<String> nullNode = null;
		RedBlackTree.Node<String> rootNode = root;

		
		while (rootNode != null)
		{
		nullNode = rootNode;
		if (node.key.compareTo(rootNode.key) < 0) {
		rootNode = rootNode.leftChild;
		}
		else {
		rootNode = rootNode.rightChild;
		}
		}
		node.parent = nullNode;

		if (nullNode == null)
		root = node;
		else if (node.key.compareTo(nullNode.key) < 0)
		nullNode.leftChild = node;
		else
		nullNode.rightChild = node;

		node.leftChild = null;
		node.rightChild = null;
		node.color = 1;

		// Call fixTree
		fixTree(node);

		}


	public void insert(String data){
		addNode(data);	
	}
	
	public RedBlackTree.Node<String> lookup(String k){ 
		 node.key = k;
	        node = root.rightChild;
	        
	        for(;;) 
	        {
	            if( k.compareTo(node.key) < 0) 
	            {
	                node = node.leftChild;
	            }
	            else if( k.compareTo(node.key) > 0)
	            {
	                node = node.rightChild;
	            }
	            else if(node != null)
	            {
	                return node;
	            }
	            else
	            {
	                return null;
	            }
	        }
	}
 	
	
	public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){  
		 if(root == null || n.parent == null)
	        {
	            return null;
	        }
	        
	        if(n.leftChild == n)
	        {
	            return n.rightChild;
	        }
	        else if(n.rightChild == n)
	        {
	            return n.leftChild;
	        }
	        else
	        {
	            return null;
	        }
	}
	
	
	public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
		 if(n == null)
	        {
	            return null;
	        }
	        
	        if(n.leftChild != null && n.leftChild == n.parent)
	        {
	            return n.rightChild;
	        }
	        else if(n.rightChild != null && n.rightChild == n.parent)
	        {
	            return n.leftChild;
	        }
	        else
	        {
	            return null;
	        }
	}
	
	public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
		return n.parent.parent;
	}
	
	public void rotateLeft(RedBlackTree.Node<String> n){
		RedBlackTree.Node<String> tempChild = n.leftChild;
        n.leftChild = tempChild.rightChild;
        tempChild.rightChild = n;
	}
	
	public void rotateRight(RedBlackTree.Node<String> n){
		RedBlackTree.Node<String> tempChild = n.rightChild;
        n.rightChild = tempChild.leftChild;
        tempChild.leftChild = n;
	}
	
	public void fixTree(RedBlackTree.Node<String> current) {
		
		RedBlackTree.Node<String> parentCurrent = null;
		if(current.parent!= null){
        parentCurrent = current.parent;
		}
		else
		{
			parentCurrent = root;
		}
        RedBlackTree.Node<String> greatParent = null;
       if(parentCurrent.parent ==null){
    	   greatParent = parentCurrent.parent;
       }
        
        RedBlackTree.Node<String> aunt;
        aunt = getAunt(current);

       
            if (parentCurrent.color == 1)
             if (parentCurrent.color == 0) {
                if (aunt == null || aunt.color == 1)
                    if (current != parentCurrent.rightChild || parentCurrent != greatParent.leftChild) {
                        if (current == parentCurrent.leftChild && parentCurrent == greatParent.rightChild) {
                        	RedBlackTree.Node<String> currentParent = current.parent;
                            current.parent = currentParent.parent;

                            currentParent.parent.rightChild = current;

                            RedBlackTree.Node<String> currentRight = current.rightChild;
                            currentRight.parent = currentParent;
                            currentParent.leftChild = currentRight;

                            current.rightChild = currentParent;
                            currentParent.parent = current;

                            rotateLeft(current.rightChild);
                        } else {
                            if (parentCurrent.leftChild == current && greatParent.leftChild == parentCurrent) {
                                rotateRight(current);
                            } else if (parentCurrent.rightChild == current && greatParent.rightChild == parentCurrent)
                                rotateLeft(current);
                        }
                    } else {
                    	RedBlackTree.Node<String> currentParent = current.parent;
                    	current.parent = currentParent.parent;
                        currentParent.parent.leftChild = current;

                        RedBlackTree.Node<String> currentLeft = current.leftChild;
                        currentLeft.parent = currentParent;
                        currentParent.rightChild = currentLeft;
                        current.leftChild = currentParent;
                        currentParent.parent = current;


                        rotateRight(current.leftChild);
                    }
                else {
                    if (aunt != null && aunt.color == 0) {
                        parentCurrent.color = 1;
                        aunt.color = 1;
                        if (greatParent == root) {
                        }
                        greatParent.color = 0;
                    }
                }
            }
        }
	
	public boolean isEmpty(RedBlackTree.Node<String> n){
		if (n.key == null){
			return true;
		}
		return false;
	}
	 
	public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
	{
		if (child.compareTo(parent) < 0 ) {//child is less than parent
			return true;
		}
		return false;
	}

	public void preOrderVisit(Visitor<String> v) {
	   	preOrderVisit(root, v);
	}
	 
	 
	private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
	  	if (n == null) {
	  		return;
	  	}
	  	v.visit(n);
	  	preOrderVisit(n.leftChild, v);
	  	preOrderVisit(n.rightChild, v);
	}	
	
}

