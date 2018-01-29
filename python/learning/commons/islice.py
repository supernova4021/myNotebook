from itertools import islice

l = [1,12,3,123,12,31]

i = iter(l)

for j in i:
     print(j)

for j in i:  #无结果，前面一次迭代已消耗完生成器生成的值
     print(j)

f = open("E:/setup.ini")

for j in islice(f,5,30): #用islice对文件进行切片
     print(j)
