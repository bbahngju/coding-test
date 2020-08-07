import sys
sys.setrecursionlimit(10**8)

T = int(input())
cnt = 0

def dfs(x, y):
    Field[x][y] = -1 #visited

    if y+1 < N and Field[x][y+1] == 1: #right
        dfs(x, y+1)
    if x+1 < M and Field[x+1][y] == 1: #down
        dfs(x+1, y)
    if 0 <= y-1 and Field[x][y-1] == 1: #left
        dfs(x, y-1)
    if 0 <= x-1 and Field[x-1][y] == 1: #up
        dfs(x-1, y)

    return 0


for _ in range(T):
    M, N, K = map(int, input().split())
    Field = [[0 for _ in range(N)] for _ in range(M)]

    for _ in range(K):
        X, Y = map(int, input().split())
        Field[X][Y] = 1

    for i in range(M):
        for j in range(N):
            if Field[i][j] == 1:
                dfs(i, j)
                cnt += 1

    print(cnt)
    cnt = 0
