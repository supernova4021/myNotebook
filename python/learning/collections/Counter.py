from collections import Counter


users = ["Jack","Mike","Jack","Jack","Mike","John"]

user_counter = Counter(users)
print(user_counter)

char_counter = Counter("aslfhnqa;ov nasl;fbansasnfakrjhnqwkrnqjklw")
print(char_counter)
print("most common:"+str(char_counter.most_common(4))) # use heap


char_counter2 = Counter("asfasgfqwerq")
char_counter.update(char_counter2)
print(char_counter)
