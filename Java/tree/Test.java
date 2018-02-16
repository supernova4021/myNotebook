package interview.tree;

public class Test {
	public static void main(String[] args) {
		
//		TreeNode<Character> root = TreeCreator.createSampleTree();
//		TreeTraversal.preOrderTraverse(root);
//		System.err.println();
//		TreeTraversal.inOrderTraverse(root);
//		System.err.println();
		
		//TreeNode<Character> root = TreeCreator.createTree("ABDEGCF", "DBGEACF");
		TreeNode<Character> root = TreeCreator.createSampleTree();
		TreeTraversal.preOrderTraverse(root);
		System.err.println();
		TreeTraversal.inOrderTraverse(root);
		System.err.println();
		TreeTraversal.postOrderTraverse(root);
		System.err.println();
		
		System.err.println(
				TreeTraversal.getPostOrder("ABDEGCF", "DBGEACF")
				);
		
		
		for(TreeNode node = InOrder.first(root); node != null; node = InOrder.next(node)){
			System.err.print(node.value);
		}
//		TreeNode<Character> next = InOrder.next(root);
//		System.err.println(next.value);

	}
}
