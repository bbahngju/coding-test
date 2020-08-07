import sys

moving = [[0, 1], [1, 0], [1, 1]] #갸로, 세로, 대각선
def check_range(dx, dy):
    global N
    if 0 <= dx < N and 0 <= dy < N:
        return True
    else:
        return False

def dfs(x, y, m):
    global way
    global N

    if x == N-1 and y == N-1:
        way += 1
        return

    for i in range(3):
        if check_range(x+moving[i][0], y+moving[i][1]) and house[x+moving[i][0]][y+moving[i][1]] == 0:
            if (i == 0 and m == 1) or (i == 1 and m == 0):
                continue
            if i == 2:
                if house[x][y+1] != 0 or house[x+1][y] != 0:
                    continue
            dfs(x+moving[i][0], y+moving[i][1], i)

N = int(input())
house = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

way = 0

dfs(0, 1, 0)
print(way)