package interview.tree;

public class TreeCreator {
	/**
	 *  create a sample tree
	 * @return the root of the tree..
	 */
	public static TreeNode<Character> createSampleTree(){
		TreeNode<Character> root = new TreeNode<Character>('A');
		root.setLeft(new TreeNode<Character>('B'));
		root.setRight(new TreeNode<Character>('C'));
		
		root.left.setLeft(new TreeNode<Character>('D'));
		root.left.setRight(new TreeNode<Character>('E'));
		
		root.right.setRight(new TreeNode<Character>('F'));
		return root;
	}
	
	/**
	 * 
	 * @param preOrder
	 * @param inOrder
	 * @return the root of the tree..
	 */
	public static TreeNode<Character> createTree(String preOrder, String inOrder){
		
		if(preOrder.isEmpty()){// .equals("")
			return null;
		}
		
		Character rootVal = preOrder.charAt(0);
		TreeNode<Character> root = new TreeNode<Character>(rootVal);
		
		// find the index of root in the inorder string..
		int rootIndex = inOrder.indexOf(rootVal);
		// the left tree's preOrder and inOrder...
		root.left = createTree(preOrder.substring(1, rootIndex+1), inOrder.substring(0, rootIndex));
		root.right = createTree(preOrder.substring(rootIndex+1), inOrder.substring(rootIndex+1));
		return root;
	}
	
}

