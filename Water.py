import sys
A4 = A7 = A8 = A12 = A14 = "00"
A11 = "02"
a11 = 0x02
a6 = 0x4b
ID = 00
Money = sys.argv[1] + "00"
Decmoney = int(Money,10)
Hexmoney = hex(Decmoney)              
a2 = int(Hexmoney[4:6],16)
a3 = int(Hexmoney[2:4],16)
a1 = a2 + a3
a5 = a1 ^ 0xff

if a1 > 0xff:
  a10 = 0xfe
  a15 = 0x2ff - a1 - a6 - a10
else:
  a10 = 0xec
  a15 = 0x1ff - a1 - a6 - a10

a0 = a2 ^ a3 ^ a6 ^ a10 ^ a11
a9 = a10 + a11
a13 = a9 ^ 0xff

A0 = hex(a0)[len(hex(a0))-2:]
A1 = hex(a1)[len(hex(a1))-2:]
A2 = hex(a2)[len(hex(a2))-2:]
A3 = hex(a3)[len(hex(a3))-2:]
A5 = hex(a5)[len(hex(a5))-2:]
A6 = hex(a6)[len(hex(a6))-2:]
A9 = hex(a9)[len(hex(a9))-2:]
A10 = hex(a10)[len(hex(a10))-2:]
A11 = "02"
A13 = hex(a13)[len(hex(a13))-2:]
A15 = hex(a15)[len(hex(a15))-2:]
data = A0+A1+A2+A3+A4+A5+A6+A7+A8+A9+A10+A11+A12+A13+A14+A15
data = data.replace("x","0").upper()
print data
