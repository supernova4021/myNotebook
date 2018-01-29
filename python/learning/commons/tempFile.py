from tempfile import TemporaryFile, NamedTemporaryFile

tf = TemporaryFile()
print(tf.name)
#tf.write('asdasv'*100000)
tf.seek(0)

#print(tf.read(100))
#print(tf.read(100))


