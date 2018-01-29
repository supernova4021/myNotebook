from collections import namedtuple


User = namedtuple("User",["name","age","edu"])

# *args
user_tuple = ("sily",22)
user = User(*user_tuple,"MIT")

print(user)

# unpack
name,*other = user
print(name+str(other))

# user = User._make(iterable)

print(user._asdict())

# *kwargs
user_dict = {"name":"peasy","age":33}
user = User(**user_dict,edu="MMIIT")

print(user)
