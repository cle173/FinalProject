
public class BinarySearchTree {
	
	//Root of the Binary Search Tree
	private Node root;
	
	public BinarySearchTree(){
		root = null;
	}
	
	//Node inner class to compare left and right nodes
	class Node {
		public Comparable data;
		public Node left;
		public Node right;
		public int count = 1; // Count starts at 1 because the root isnt counted at first
		public String path;  // string of "1" and "0" (Huffman tree inspired), eg. "101" means R L R
		public int depth;
		
		//Determines if nodes are added to the left or the right
		public void addNode(Node node, String p, int d) {
			//Compares the data of the nodes
			int comp = node.data.compareTo(data);
			if(comp < 0) {
				if(left == null) {
					node.path = p + "0";
					node.depth = d + 1;
					left = node;
				} else {
					left.addNode(node, (p + "0"), d+1);
					} 
			}
			
			
			if(comp > 0) {
				if(right == null) {
					node.path = p + "1";
					node.depth = d + 1;
					right = node;
				} else {
					right.addNode(node, (p + "1"), d+1);
				}
			}
			count++;
		}
	}
	
	public void add(Comparable obj) {
		
		Node newNode = new Node();
		newNode.data = obj;
		newNode.left = null;
		newNode.right = null;
		newNode.path = "";
		newNode.depth = 0;
		
		if(root == null) { root = newNode;}
		else {
			root.addNode(newNode, "", 0);
		}
		
	}
	
	public boolean find(Comparable obj) {
		
		int count = 0;
		Node current = root;
		while(current != null) {
			int x = current.data.compareTo(obj);
			if(x == 0) {
				//System.out.println("Node: " + count);
				return true;
			} 
			else if(x > 0) {current = current.left;}
			else {current = current.right;}			
			count++;
		}
		
		return false;
	}
	
	public int getData(Comparable obj) {
			
			Node current = root;
	
			while(current != null) {
				int x = current.data.compareTo(obj);
				if(x == 0) { 
					return (int) current.data;
				}
				
				if(x > 0) {
					current = current.left;
				}
				else {
					current = current.right;
				}
			}
						
			return 0;
		}
	
	public String getPath(Comparable obj) {
		
		Node current = root;

		while(current != null) {
			int x = current.data.compareTo(obj);
			if(x == 0) { 
				return current.path;
			}
			
			if(x > 0) {
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
					
		return "";
	}
	
	public int getDepth(Comparable obj) {
			
			Node current = root;
	
			while(current != null) {
				int x = current.data.compareTo(obj);
				if(x == 0) { 
					return current.depth;
				}
				
				if(x > 0) {
					current = current.left;
				}
				else {
					current = current.right;
				}
			}
						
			return 0;
		}
	
	public int getCount(){
		return root.count;
	}
	
	public void clear(){
		root = null;
	}
	
	public void remove(Comparable obj) {
		
		Node toBeRemoved = root;
		Node parent = null;
		boolean found = false;
		
		while(!found && toBeRemoved != null) {
			int x = toBeRemoved.data.compareTo(obj);
			if (x == 0) { found = true; }
			else {
				parent = toBeRemoved;
				if(x > 0) {
					toBeRemoved = toBeRemoved.left;
				}
				else {
					toBeRemoved = toBeRemoved.right;
				}
			}
		}
		
		//Returns nothing if the object is not found
		if(!found){ return;}
		
		//Comparing the children of the children
		if(toBeRemoved.left == null || toBeRemoved.right == null) {
			
			Node newChild;
			if(toBeRemoved.left == null) {
				newChild = toBeRemoved.right;
			}
			else {
				newChild = toBeRemoved.left;
			}
			
			if(parent == null){
				root = newChild;
			}
			else if(parent.left == toBeRemoved) {
				parent.left = newChild;
			}
			else {
				parent.right = newChild;
			}
			return;
		}
		
		Node smallestParent = toBeRemoved;
		Node smallest = toBeRemoved.right;
		
		while(smallest.left != null) {
			smallestParent = smallest;
			smallest = smallest.left;
		}
		
		toBeRemoved.data = smallest.data;
		
		if(smallestParent == toBeRemoved) {
			smallestParent.right = smallest.right;
		}
		else {
			smallestParent.left = smallest.left;
		}		
	}
	
	//Prints the Tree using InOrder Traversal
	public void iPrint() {
		System.out.println("Inorder: ");
		iPrint(root);
		System.out.println();
	}
	
	public static void iPrint(Node parent) {
		if(parent == null) { return; }
		iPrint(parent.left);
		System.out.print(parent.data + " ");
		iPrint(parent.right);
	}

	
	//Prints the Tree using PreOrder Traversal
	public void prePrint() {
		System.out.println("Preorder: ");
		prePrint(root);
		System.out.println();
	}
	
	public void prePrint(Node parent) {
		if(parent == null ) {return;}
		System.out.print(parent.data + " ");
		prePrint(parent.left);
		prePrint(parent.right);
	}
	
	//Prints the tree using PostOrder traversal
	public void postPrint() {
		System.out.println("Postorder: ");
		postPrint(root);
		System.out.println();
	}
	
	public void postPrint(Node parent) {
		if(parent == null ) {return;}
		postPrint(parent.left);
		postPrint(parent.right);
		System.out.print(parent.data + " ");
	}
	
}
