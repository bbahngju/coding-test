W0, I0, T = map(int, input().split())
D, I, A = map(int, input().split())

B = I0
flag = False
C = I - (B+A)
noChange = W0 + (D*C)

if noChange <= 0:
    print("Danger Diet")
else:
    print(noChange, end = ' ')
    print(B)

W0 += C
if abs(C) > T:
    B += C//2

for i in range(D-1):
    if W0 <= 0 or B <= 0:
        print("Danger Diet")
        flag = True
        break

    C = I - (B+A)
    W0 += C
    if abs(C) > T:
        B += C // 2

if not flag and (W0 <= 0 or B <= 0):
    print("Danger Diet")
    flag = True

if not flag:
    print(W0, end=' ')
    print(B, end=' ')

    if I0 - B > 0:
        print("YOYO")
    else:
        print("NO")