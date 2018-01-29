
dic = {
     "harass":1200,
     "gree":211,
     "blue":123121,
     "bilibili":229912
}

length = max(map(len,dic.keys()))

for k in dic:
     print(k.ljust(length) + ":"+str(dic[k]))
