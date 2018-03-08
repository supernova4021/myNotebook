import datetime

class Person:
	def __init__(self, msg):
		print(str(datetime.datetime.now()) + "  ------    " + msg)
		
	@classmethod
	def bb(cls, msg):
		return cls(msg)  # just like Person(msg)

person = Person.bb('Hey!')
