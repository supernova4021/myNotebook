from collections import ChainMap

# chain dicts together

dict1 = {"abc":111,"bbc":222,"ccc":444}
dict2 = {"abcd":1111,"bbc":2222,"cccc":4444}

dict12 = ChainMap(dict1,dict2)
print(dict12)
print(dict12.maps) # dict list

new_dict = dict12.new_child({"aaaaaaaaaa":999})
print(new_dict)

for k,v in dict12.items():
     print(k,v)
