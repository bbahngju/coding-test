import sys
from collections import deque

def puyo(boom_list):
    for x, y in boom_list:
        map[x][y] = '.'

def bfs(s_x, s_y, cnt):
    global boom
    q = deque()
    boom_list = []

    q.append([s_x,s_y,cnt])
    boom_list.append([s_x, s_y])
    visited[s_x][s_y] = True

    while q:
        x, y, c = q.popleft()

        for dx, dy in [[x+1, y], [x-1, y], [x, y+1], [x, y-1]]:
            if 0 <= dx < 12 and 0 <= dy < 6:
                if map[x][y] == map[dx][dy] and not visited[dx][dy]:
                    q.append([dx, dy, c+1])
                    boom_list.append([dx, dy])
                    visited[dx][dy] = True
                    c += 1

    if len(boom_list) >= 4:
        puyo(boom_list)
        return 1
    else:
        return 0

def relocate():
    for i in reversed(range(11)):
        for j in range(6):
            if map[i][j] != '.':
                down = i+1
                while down < 12 and map[down][j] == '.':
                    down += 1
                if down-1 != i:
                    map[down-1][j], map[i][j] = map[i][j], map[down-1][j]

map = [list(sys.stdin.readline().strip()) for i in range(12)]

boom = 0

while 1:
    visited = [[False for _ in range(6)] for _ in range(12)]
    check = 0
    for i in reversed(range(12)):
        for j in range(6):
            if map[i][j] != '.' and not visited[i][j]:
                check += bfs(i, j, 1)

    if check == 0:
        break
    else:
        boom += 1
        relocate()

print(boom)