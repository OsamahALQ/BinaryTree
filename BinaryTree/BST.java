import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.File;  // Import the File class


public class BST extends BinaryTree{

	// You MUST have a zero argument constructor that
	// creates an empty binary search tree
	// You can can add code to this if you want (or leave it alone).
  // We will create all BSTs for testing using this constructor 
   String [] arr = new String[this.size];
  int counter=0;
  boolean notExist;
  public BST(){

  }



  @Override
  public boolean contains(String s){
    Node tmp = this.getRoot();
    while (tmp != null) {
      // pass right subtree as new tree
      if (s.compareTo(tmp.getData()) > 0){
          tmp = tmp.right;

      // pass left subtree as new tree
      }else if (s.compareTo(tmp.getData()) < 0){
          tmp = tmp.left;
      }else{
          return true; // if the key is found return 1
      }
  }
  return false;
  }


  @Override
  public void add(String s){
    Node tmp = this.root;

    if (this.root == null){
      this.root = new Node(s);
    }else{

    while(this.root != null){
      if (this.root.getData().compareTo(s) > 0){
        if(this.root.left == null){
          this.root.left = new Node (s);
          this.root = tmp;
          break;
        }else{
          this.root = this.root.left;
        }
      }else if (this.root.getData().compareTo(s) < 0){
        if (this.root.right == null){
          this.root.right = new Node (s);
          this.root = tmp;
          break;
      }else{
        this.root = this.root.right;
      }
      }
    }
  }
}

public void inOrder(Node node) {
  if (node == null) {
    return;
  } 
  inOrder(node.left);
  this.add(node.data);
  System.out.printf("%s ", node.data);
  inOrder(node.right);
}
public BST makeBalanced(){
  BST t = new BST();
  t.inOrder(this.root);
    return t;
}
public boolean saveToFile(String fname){

    try {
      File myObj = new File(System.getProperty("user.dir")+"/Desktop/Assignment 5/"+fname);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


  try {
    FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/Desktop/Assignment 5/"+fname);
    myWriter.write(PrintBinaryTree.toString(this));
    myWriter.close();
    return true;
  } catch (IOException e) {
    System.out.println("An error occurred.");
    return false;
  }


}
}
