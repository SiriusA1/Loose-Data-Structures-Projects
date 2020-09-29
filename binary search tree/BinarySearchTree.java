//Author: Conor McGullam
public class BinarySearchTree {
	private Node root;
	public BinarySearchTree() { root = null; }
	public void add(Comparable obj)
	{
		Node newNode = new Node();
		newNode.data = obj;
		newNode.left = null;
		newNode.right = null;
		if (root == null) { root = newNode; }
		else { root.addNode(newNode, root); }
	}
	public String toString() {
		return root.toString();
	}
	class Node {
		public Comparable data;
		public Node left;
		public Node right;
		public void addNode(Node newNode, Node currentNode) {
			if(newNode.data.compareTo(currentNode.data) < 0) {
				if(currentNode.left == null) {
					currentNode.left = newNode;
				}else {
					addNode(newNode, currentNode.left);
				}
			}else if(newNode.data.compareTo(currentNode.data) > 0) {
				if(currentNode.right == null) {
					currentNode.right = newNode;
				}else {
					addNode(newNode, currentNode.right);
				}
			}
		}
		public String toString() {
			if(this.left == null && this.right == null) {
				return this.data.toString();
			}else if(this.left == null) {
				return "(" + this.data.toString() + " (" + null + ")" + " (" +  this.right.toString() + ")";
			}else if(this.right == null) {
				return "(" + this.data.toString() + " (" +  this.left.toString() + ")" + " (" + null + ")";
			}else {
				return "(" + this.data.toString() + " (" +  this.left.toString() + ")" + " (" +  this.right.toString() + ")";
			}
		}
	}
}
