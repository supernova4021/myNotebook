from functools import wraps
import time
import logging


def warn(timeout):
	def decorator(func):
		def wrapper(*args, **kwargs):
			start = time.time()
			res = func(*args, **kwargs)
			used = time.time() - start
			if used > timeout:
				msg = '"%s":%s  >  timeout:%s' % (func.__name__,used,timeout)
				logging.warn(msg)
			return res
		return wrapper
	return decorator

@warn(3)	
def test():
	print("test start....")
	time.sleep(4)
	print("test end....")
	
test()

# decorator = warn(3)
# func = decorator(test)
# func()


