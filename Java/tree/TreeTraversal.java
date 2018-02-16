package interview.tree;

public class TreeTraversal {
	
	public static void preOrderTraverse(TreeNode<Character> root){
		
		if(root == null){
			return;
		}
		
		System.err.print(root.value);
		preOrderTraverse(root.left);
		preOrderTraverse(root.right);
	}
	
	public static void inOrderTraverse(TreeNode<Character> root){
		
		if(root == null){
			return;
		}
		
		inOrderTraverse(root.left);
		System.err.print(root.value);
		inOrderTraverse(root.right);
	}
	
	public static void postOrderTraverse(TreeNode<Character> root){
		
		if(root == null){
			return;
		}
		
		postOrderTraverse(root.left);
		postOrderTraverse(root.right);
		System.err.print(root.value);
	}
	
	/**
	 *  return the post order corresponding to preOrder and inOrder
	 * @param preOrder
	 * @param inOrder
	 */
	public static String getPostOrder(String preOrder, String inOrder){
		
		if(preOrder.isEmpty()){// .equals("")
			return "";
		}
		
		Character rootVal = preOrder.charAt(0);
		
		// find the index of root in the inorder string..
		int rootIndex = inOrder.indexOf(rootVal);
		// the left tree's preOrder and inOrder...
		return getPostOrder(preOrder.substring(1, rootIndex+1), inOrder.substring(0, rootIndex))
				+ getPostOrder(preOrder.substring(rootIndex+1), inOrder.substring(rootIndex+1))
				+ rootVal;
	}
	
}
