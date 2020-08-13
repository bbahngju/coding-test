import sys
from collections import deque

def move(x, y, dx, dy):
   step = 0
   while map[x+dx][y+dy] != '#':
       x = x+dx
       y = y+dy
       step += 1

       if map[x][y] == 'O':
           break

   next_x = x
   next_y = y
   return next_x, next_y, step

def bfs(red_x, red_y, blue_x, blue_y):
    way = [[0, -1], [0, 1], [-1, 0], [1, 0]]  # LEFT, RIGHT, UP, DOWN
    q = deque()
    q.append([red_x, red_y, blue_x, blue_y, 1])
    visited[red_x][red_y][blue_x][blue_y] = True

    while q:
        rx, ry, bx, by, cnt = q.popleft()
        if cnt > 10:
            return 0
        for dx, dy in way:
            next_rx, next_ry, step_r = move(rx, ry, dx, dy)
            next_bx, next_by, step_b = move(bx, by, dx, dy)

            if map[next_rx][next_ry] == 'O':
                if map[next_bx][next_by] == 'O':
                    continue
                else:
                    return 1
            elif map[next_bx][next_by] == 'O':
                continue

            if next_rx == next_bx and next_ry == next_by:
                if step_r > step_b:
                    next_rx -= dx
                    next_ry -= dy
                else:
                    next_bx -= dx
                    next_by -= dy

            if not visited[next_rx][next_ry][next_bx][next_by]:
                visited[next_rx][next_ry][next_bx][next_by] = True
                q.append([next_rx, next_ry, next_bx, next_by, cnt + 1])
    return 0

N, M = map(int,input().split())
map = [list(sys.stdin.readline().strip()) for _ in range(N)]
visited = [[[[False]*M for _ in range(N)] for _ in range(M)] for _ in range(N)]

loc = {}

for i in range(N):
    for j in range(M):
        if map[i][j] in ['R', 'B']:
            loc[map[i][j]] = [i,j]

result = bfs(loc['R'][0], loc['R'][1], loc['B'][0], loc['B'][1])
print(result)