#系统调用，I/O操作是分块进行的，内容不足一块也是按一块写入

f = open('D:/test/weixinmpmenu.log',buffering=2048)
#f = open('D:/test/buf.txt',buffering=1) 行缓冲 一行作为缓冲
#f = open('D:/test/buf.txt',buffering=0) 无缓冲
import mmap
#memory map .....
#把文件映射到内存,就是把整个文件放到内存中？进程可以共享
try: 
     m = mmap.mmap(f.fileno(),0,access=mmap.ACCESS_WRITE)
     print(m[4:12])
except:
     print()
#.......os version...........
import os
import stat
s = os.stat('D:/test/weixinmpmenu.log')
print(s)
print(stat.S_ISDIR(s.st_mode))
print(s.st_mode & stat.S_IXUSR)
import time
print(time.localtime(s.st_ctime))
print(s.st_size)

#-----------os.path version-------
print(os.path.isdir('D:/test/weixinmpmenu.log'))
print(os.path.getctime('D:/test/weixinmpmenu.log'))
print(os.path.getsize('D:/test/weixinmpmenu.log'))
