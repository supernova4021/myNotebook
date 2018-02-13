/**
¿ìÂıÖ¸Õë¼ì²âÑ­»·

*/

class Solution {
public:
    
    int digitsSquareSum(int n){
        int res = 0;
        while(n!=0){
            res += (n%10)*(n%10);
            n /= 10;
        }
        return res;
    }
    
    bool isHappy(int n) {
        int slow,fast;
        slow = fast = n;
        do{
            slow = digitsSquareSum(slow);
            fast = digitsSquareSum(digitsSquareSum(fast));
            if(fast == 1){
                return true;
            }
        }while(slow != fast);
        return false;
    }
};