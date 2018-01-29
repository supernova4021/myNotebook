import csv

rf = open('plain.csv','rb')

reader = csv.reader(rf)

print(reader.next())
print(reader.next())
print(reader.next())
print(reader.next())

for row in reader:
     print(row)

wf = open('plainw.csv','wb')

writer = csv.writer(wf)

writer.writerow(reader.next())
writer.writerow(reader.next())
writer.writerow(reader.next())
