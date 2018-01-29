

s1 = ['<qwe113>','<gg>',"<223>",'<rrr>']

# 字符串的+重载了__add__函数

r = ''.join(s1)#数据量大时用+连接资源耗费巨大

print(r)

s2 = ['<qwe113>',True,"<223>",'<rrr>',1221,False]

r = ''.join((str(i) for i in s2)) #(str(i) for i in s2)生成器表达式

print(r)
