import sys

def find(a):
    if idx[a] == a:
        return a
    else:
        p = find(idx[a])
        idx[a] = p
        return p

def union(a, b):
    c1 = find(a)
    c2 = find(b)

    if c1 != c2:
        if fee[c1] < fee[c2]:
            fee[c2] = 0
            idx[c2] = c1
        else:
            fee[c1] = 0
            idx[c1] = c2

N, M, K = map(int, input().split())
n = 1
idx = [i for i in range(N)]
fee = list(map(int, sys.stdin.readline().split()))

for i in range(M):
    r1, r2 = map(int, input().split())
    union(r1-1, r2-1)

if sum(fee) <= K:
    print(sum(fee))
else:
    print("Oh no")