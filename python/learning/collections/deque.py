from collections import deque

# deque是线程安全(通过GIL)的，list并不是

user_deque = deque(["gem","john",["monster","monster3"],"walker"])

user_deque.appendleft("beauty")
print(user_deque)
user_deque.popleft()


# shallow copy   immutable copy, otherwise reference
user_deque2 = user_deque.copy()
user_deque2[2].append("monster2")
user_deque2[1] = "Hey! John"

print("user_deque2=%s",user_deque2)
print("user_deque=%s",user_deque)

# deep copy   copy anything
import copy  
user_deque2 = copy.deepcopy(user_deque)


user_deque3 = deque(["gem22","john22"])
user_deque.extend(user_deque3)
user_deque.extendleft(user_deque3)
print(user_deque)

# 魔法函数   __getitem__  ====>  user[1] ........
