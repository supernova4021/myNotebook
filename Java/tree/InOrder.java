package interview.tree;

public class InOrder {
	
	/**
	 * 获取中序遍历的下一个节点
	 * @param node
	 * @return
	 */
	public static TreeNode<Character> next(TreeNode<Character> node){
		
		if(node == null){
			return null;
		}
		
		if(node.right != null){ 
			return first(node.right);
		}
		
		// 一直往parent走，直到该节点是parent的左孩子或parent为null，之后返回parent
		while(node.parent!=null && node.parent.left != node){
			node = node.parent;
		}
		
		return node.parent;
	}
	
	// 返回右子树中序遍历的第一个节点，一直向左...
	public static TreeNode<Character> first(TreeNode<Character> node){
		
		if(node == null){
			return null;
		}
		
		while(node.left != null){
			node = node.left;
		}
		return node;
	}

}
