from collections import OrderedDict



# python2 中的dict不是ordered，而python3中的dict默认是ordered

odict = OrderedDict()

odict["hey"] = 99999
odict["hi"] = 33333
odict["bye"] = 2222
odict["doom"] = 9999999

print(odict)

print(odict.popitem())

odict.move_to_end("bye")
print(odict)
