import struct
import array

#---------------------------------
#              .wav
#-----------------------

# f = open('py3.txt','wt',encoding='utf-8')


with open('D:/test/waterFlowing.wav','rb') as f:
     info = f.read(44)

     print(struct.unpack('h',info[22:24])) #track num
     print(struct.unpack('i',info[24:28])) # hz
     print(struct.unpack('h',info[34:36])) #bitsPerSample
     #struct.unpack('h','\x01\x02') #short two byte...513


     
     f.seek(0,2) #将指针移动到相对于文件尾0字节的位置（即文件尾
     #f.tell() 告诉当前指针的位置
     n = (f.tell() - 44)//2
     buf = array.array('h',(0 for _ in range(n)))
     f.seek(44)
     f.readinto(buf)


     for i in range(n):
          buf[i]//=8 #lower volume
     f2 = open('D:/test/demo2.wav','wb')
     f2.write(info)
     buf.tofile(f2)
     f2.close()





