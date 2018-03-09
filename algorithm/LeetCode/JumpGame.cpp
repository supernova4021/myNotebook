// Given an array of non-negative integers, you are initially positioned at the first index of the array.
// Each element in the array represents your maximum jump length at that position.
// Determine if you are able to reach the last index.
// A = [2,3,1,1,4], return true.
// A = [3,2,1,0,4], return false


// just find the maxreach distance while it can reach to index 'i'
class Solution {
public:
    bool canJump(vector<int>& nums) {
        int i=0, maxreach=0;
        int n = nums.size();
        for(; i<n-1 && i<=maxreach ; i++){ // make sure it can reach to 'i'
            maxreach = max(maxreach, nums[i] + i);
        }
        return maxreach >= n-1;
    }
};