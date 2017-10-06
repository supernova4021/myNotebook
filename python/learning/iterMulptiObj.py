from itertools import chain

l1 = [1,321,23,12,412,1]
l2 = [12,31,23,12,312,3,12]
l3 = ('aas','sda','qweqw','fsaf','asda')

#zip......parallel
for i,j,k in zip(l1,l2,l3):
     print(str(i)+"...."+str(j)+"...."+k)

print("-----------------------------")

#chain.....serial
for i in chain(l1,l2,l3):
     print(i)
