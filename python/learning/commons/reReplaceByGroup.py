import re

# 2017-07-12  ==> 07/12/2017
#捕获组  \1 \2 \3

log = ''
with open('d:/test/weixinmpmenu.log') as f:
     logs = str(f.readlines())
     for s in logs:
          log += s


log = re.sub('(?P<year>\d{4})-(\d{2})-(\d{2})',r'\2/\3/\g<year>',log)

print(log[:200])
