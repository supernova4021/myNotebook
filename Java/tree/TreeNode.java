package interview.tree;

public class TreeNode<T> {
	final T value;
	TreeNode left;
	TreeNode right;
	TreeNode parent;
	
	public TreeNode(T value) {
		this(value,null,null);
	}

	public TreeNode(T value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = null;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
		if(this.left != null){
			this.left.parent = this;			
		}
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
		if(this.right != null){
			this.right.parent = this;			
		}
	}
	
	
	
	
	
}
