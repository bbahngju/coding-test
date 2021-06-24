import sys

def move():
    for i in range(1, N+1):
        cur = i
        for j in range(1, H+1):
            if ladder[j][cur] == 1:
                cur += 1
            elif ladder[j][cur-1] == 1:
                cur -= 1
        if cur != i:
            return False
    return True

result = 999999
def bf(x, y, cnt):
    global result
    if cnt > 3 or result <= cnt:
        return

    for src in range(x, H+1):
        dest = 1
        while dest < N:
            if ladder[src][dest] != 0:
                dest += 2
                continue
            elif ladder[src][dest+1] != 0:
                dest += 3
                continue
            elif ladder[src][dest-1] != 0:
                dest += 1
                continue
            else:
                ladder[src][dest] = 1
                if move():
                    result = min(result, cnt)
                else:
                    bf(src, dest+1, cnt+1)
                ladder[src][dest] = 0
            dest += 1

N, M, H = map(int, input().split())

ladder = [[0 for _ in range(N+1)] for _ in range(H+1)]

for _ in range(M):
    h, n = map(int, sys.stdin.readline().split())
    ladder[h][n] = 1

if not move():
    bf(1, 1, 1)
    if result == 999999:
        result = -1
    print(result)
else:
    print(0)