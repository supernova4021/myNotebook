/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while(!nodes.isEmpty()){
            int max = nodes.element().val;
            int n = nodes.size();
            for(int i=0; i<n; i++){
                TreeNode node = nodes.poll();
                if(max < node.val){
                    max = node.val;
                }
                if(node.left != null){
                    nodes.offer(node.left);
                }
                if(node.right != null){
                    nodes.offer(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
	
	public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            long sum = 0;
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(sum/(double)size);
        }
        return res;
    }
	
	public List<Double> levelTraversal(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
			TreeNode node = queue.poll();
			res.add(node.val);
			if(node.left != null){
				queue.offer(node.left);
			}
			if(node.right != null){
				queue.offer(node.right);
			}
        }
        return res;
    }
}