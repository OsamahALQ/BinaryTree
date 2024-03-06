import java.io.File;   
import java.util.Scanner; 


public class BinaryTree {
	protected Node root = null;
	protected int  size = 0;
	Node highest;
	public BinaryTree(){
		size = 0;
	}

  public BinaryTree(String s){
		root = new Node(s);
		size = 1;
		highest = root;
	}

	public int getSize(){ return this.size; }
	public Node getRoot(){ return this.root; }

/*
	public boolean contains(String target){
		if( root == null ){ return false; }
		if( root.getData().equals(target) ){
			return true;
		}
		return false;
	}
	*/
	public boolean contains(String s){
		Node temp = root;
		if (root == null){
			return false;
		}else{
			if (root.getData().equals(s)){
				this.root = highest;				
				return true;
			}else{
				if (this.root.left != null){this.root = this.root.left;

				if(this.contains(s)){
					return true;
				}}

				this.root = temp;
				if (this.root.right != null){this.root = this.root.right;

				if(contains(s)){
					return true;
				}}

			}
		}
		
		this.root = temp;
		return false;
	}
	public boolean isBST(){
		Node temp = this.root;
		if (root == null){
			return false;
		}else{
		if (this.root.right != null){
			if(this.root.getData().compareTo(this.root.right.getData()) > 0){
				return false;	
			}
		}

		if (this.root.left !=null){

			if(this.root.getData().compareTo(this.root.left.getData()) < 0){
				return false;
			}
		}
		if (this.root.left != null){
			this.root = this.root.left;
			if(!this.isBST()){
				return false;
			}
		}

		this.root = temp;

		if(this.root.right != null){
			this.root = this.root.right;
			if(!this.isBST()){
				return false;
			}
		}

	}

		this.root = temp;

		return true;
	
}
	public void loadFromFile(String fname){
		String fileDir = "/Users/m7md/Desktop/Assignment 5";
		BinaryTree bt = new BinaryTree();
		try{
			Scanner file = new Scanner(new File(fileDir +"/"+ fname) );
			while(file.hasNextLine()){
				bt.add(file.nextLine().strip());
				//String data = file.nextLine();
        		//System.out.println(data);
			}
		}catch(Exception e){
			System.out.println("Something went wrong!!");
		}
		this.root = bt.root;
		this.size = bt.size;
	}

	public void add(String s){
		addRandom(s);
	}

	
	/* add a node in a random place in the tree. */
	private void addRandom(String s){
		if(root == null && size == 0){
			root = new Node(s);
		}else{
		  Node tmp = root;
		  boolean left = Math.random() < 0.5; 
		  Node child = left ? tmp.getLeft():tmp.getRight();
		  while(child != null){
			tmp = child;
			left = Math.random() < 0.5;
			child = left ? tmp.getLeft() : tmp.getRight();
		  }
		  // assert: child == null
		  // yea! we have a place to add s
		  if(left){
		  	tmp.setLeft(new Node(s));
		  }else{
			  tmp.setRight(new Node(s));
		  }
		}
		size += 1;
	}




	/** Computes the height of the binary tree
	  *
		* The height is the length of the longest path from
		* the root of the tree to any other node.
		*
		* @return the height of the tree
		*/
	public final int height(){
	  if( root == null ){ return -1; }
	  if( size == 1){ return 0; }
	  return heightRecursive(root);
	}
	protected final static int heightRecursive(Node root){
		if( root == null ){
			return -1;
		}
		int leftHeight = heightRecursive(root.getLeft());
		int rightHeight = heightRecursive(root.getRight());
		if( leftHeight < rightHeight){
			return 1 + rightHeight;
		}else{
			return 1 + leftHeight;
		}
	}


	public static void main(String[] args){
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		BinaryTree t = new BinaryTree("cat");
		System.out.println("height = " + t.height() + ",  size = " + t.getSize());
		t.add("dog");
		t.add("eel");
		t.add("cow");
		t.add("rat");
		System.out.println("height = " + t.height() + ",  size = " + t.getSize());
		System.out.println(t);

		/*System.out.println(" -----------------");
		System.out.println("Find (eel)");
		
		if (t.contains("eel")){
			System.out.println("Yes it does exist \n");
		}else{
			System.out.println("No it doesn't exist \n");
		}
		System.out.println("Find (bat)");
		if (t.contains("bat")){
			System.out.println("Yes it does exist \n");
		}else{
			System.out.println("No it doesn't exist \n");
		}
		System.out.println(" -----------------");
		*/
		if(t.isBST()){
			System.out.println("---------");
			System.out.println("the tree is BST");
			System.out.println("---------");
		}else{
			System.out.println("---------");
			System.out.println("the tree is not BST");
			System.out.println("---------");
		}

		System.out.println("height = " + t.height() + ",  size = " + t.getSize());
		t.loadFromFile("sample.txt");
		System.out.println(t);



	}
	


	@Override
	public String toString() {
		return PrintBinaryTree.toString(this);
	}


}
