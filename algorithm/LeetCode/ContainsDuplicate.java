class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numset = new HashSet<Integer>();
        for(int num : nums){
            if(numset.contains(num)){
                return true;
            }
            numset.add(num);
        }
        return false;
    }
	
	//a sliding window. The front of the window is at i, the rear of the window is k
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0; i<nums.length; i++){
            if(i > k){
                set.remove(nums[i-k-1]);
            }
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}