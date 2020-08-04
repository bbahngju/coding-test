#1. R*C 크기의 필드를 생성한다
#2. 필드 범위 내 & 각 울타리 내에서의 양의 수와 늑대의 수를 구한다 //BFS
#2-1. 양의 수가 더 많다면 sheep+1, 늑대의 수가 더 많다면 wolf+1
#2-2. 방문하지 않은 곳이 있는지 체크하며 모든 곳을 다 방문한다
#3. 모든 필드를 다 방문했다면 최종 sheep, wolf 값을 반환한다.
import sys
from collections import deque

def check():
    for i in range(R):
        for j in range(C):
            if field[i][j] != '#' and not visited[i][j]:
                return i, j

def bfs(i, j):
    global R, C, win_sheep, win_wolf
    q = deque()
    sheep = 0
    wolf = 0

    if field[i][j] == 'v':
        wolf += 1
    elif field[i][j] == 'o':
        sheep += 1
    q.append([i,j])
    visited[i][j] = True


    while q:
        x, y = q.popleft()
        for dx, dy in [[x+1, y], [x-1, y], [x, y+1], [x, y-1]]:
            if 0 <= dx < R and 0 <= dy < C and not visited[dx][dy]:
                if field[dx][dy] == '#': continue
                if field[dx][dy] == 'v':
                    q.append([dx,dy])
                    visited[dx][dy] = True
                    wolf+=1
                elif field[dx][dy] == 'o':
                    q.append([dx, dy])
                    visited[dx][dy] = True
                    sheep+=1
                else:
                    q.append([dx, dy])
                    visited[dx][dy] = True
    if sheep == 0 and wolf == 0:
        return
    else:
        if sheep > wolf:
            win_sheep += sheep
        else: win_wolf += wolf





R, C = map(int,input().split())

field = [[None for _ in range(C)] for _ in range(R)]
visited = [[False for _ in range(C)] for _ in range(R)]
for i in range(R):
    field[i] = list(sys.stdin.readline().strip())

win_sheep = 0
win_wolf = 0

try:
    for i in range(R):
        for j in range(C):
            x, y = check()
            bfs(x,y)
except:
    print(win_sheep, win_wolf)