from functools import update_wrapper, wraps


def memo(func):
	cache = {}  
	@wraps(func)  # update_wrapper syntactic sugar
	def wrapper(*arg):
		if arg not in cache:
			cache[arg] = func(*arg)
		return cache[arg]
	#update_wrapper(wrapper, func)  # 把元数据的信息(__name__, __doc__...)更新到wrapper上
	return wrapper

# mfibonacci = memo(fibonacci)
# mfibonacci(60)
	
@memo  # syntactic sugar
def fibonacci(n):
	if n <= 1:
		return 1
	return fibonacci(n-1)+fibonacci(n-2)

print(fibonacci(60))