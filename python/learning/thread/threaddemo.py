from threading import Thread
'''
t = Thread(target=handle, args=(1,))
t.start()
'''

class MyThread(Thread):
	def __init__(self,param):
		Thread.__init__(self)
		self.param = param
	
	def run(self):
		handle(self.param)

t = MyThread(232)
t.start()

t.join() # t join into the execution and main thread is waiting t to finish