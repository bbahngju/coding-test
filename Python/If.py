import sys
#시간복잡도: O(M*logN), O(1000000*6) = 6000000

N, M = map(int, input().split())
title = []
power = []

for i in range(N):
    t, p = sys.stdin.readline().split()
    power.append(int(p))
    if i != 0 and power[i-1] == int(p):
        title.append(title[i-1])
    else:
        title.append(t)

for x in range(M):
    character = int(sys.stdin.readline())
    start = 0
    end = N - 1

    while 1:
        if start == end:
            print(title[start])
            break

        mid = (start+end) // 2
        if power[mid] == character:
            print(title[mid])
            break
        elif power[mid] > character:
            end = mid
        else:
            start = mid+1