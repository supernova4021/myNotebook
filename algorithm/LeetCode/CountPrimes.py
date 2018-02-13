class Solution(object):
    def countPrimes(self, n):
        """
        :type n: int
        :rtype: int
        """
        if(n < 3):
            return 0
        
        a=[True]*n
        a[0]=a[1]=False
        for i in range(2,n):
            if(a[i]):
                for j in range(2,n):
                    if(i*j > n):
                        break
                    a[i*j] = False
        return sum(a)
		
	def countPrimes2(self, n):
        """
		faster version...
        :type n: int
        :rtype: int
        """
        if n < 3:
            return 0;
        isPrime = [True]*n
        isPrime[0] = isPrime[1] = False
        for i in range(2,int(n**0.5) + 1):
            if(isPrime[i]):
                isPrime[i*i:n:i] = [False]*len(isPrime[i*i:n:i])
        return sum(isPrime)
        
        