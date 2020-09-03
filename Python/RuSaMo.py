import sys

def check(cnt):
    global Flag
    minimum = 0
    maximum = 0
    remain = E

    for i in cnt:
        minimum += x[i]
        maximum += y[i]

    if minimum <= E <= maximum:
        for i in cnt:
            result[i] += x[i]
        remain -= minimum

        for i in cnt:
            if remain == 0:
                break

            if remain <= y[i]-x[i]:
                result[i] += remain
                remain = 0
            else:
                result[i] += y[i]-x[i]
                remain -= y[i]-x[i]

        for i in result:
            print(i, end = ' ')
        Flag = True
        return

    else:
        return

Flag = False
def dfs(x, idx):
    global Flag

    if Flag:
        return

    if idx == P:
        check(cnt)
        return

    for i in range(x, N):
        if not visited[i]:
            visited[i] = True
            cnt[idx] = i
            dfs(i+1, idx+1)
            visited[i] = False

#==========Main===========
N, P, E = map(int, input().split())
x = [0 for _ in range(N)]
y = [0 for _ in range(N)]
result = [0 for _ in range(N)]

visited = [False for _ in range(N)]
cnt = [0 for _ in range(P)]

for i in range(N):
    x[i], y[i] = map(int, sys.stdin.readline().split())

dfs(0, 0)
if not Flag:
    print(-1)