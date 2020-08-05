#1. 공간크기 N을 입력받는다 -> N*N 공간을 생성한다
#2. N개의 줄에 공간 상태를 입력받는다
#3. 아기 상어의 위치를 구한다.
#4. 아기 상어의 위치를 중심으로 최소 거리의 물고기 위치를 구한다. //BFS
#4-1. 아기 상어의 위치는 0으로 초기화한다.
#4-2. 아기 상어가 먹을 수 있는 물고기는 자신보다 작은 크기의 물고기다.
#4-3. 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 곳에 이동할 수는 있다.
#5. 아기 상어가 어디로 이동할 지 결정한다.
#5-1. 먹을 수 있는 물고기가 없다면 총 시간 반환 => 끝
#5-2. 먹을 수 있는 물고기가 여러마리라면,
    #1) 거리가 가장 가까운 물고기를 먹으러 간다. (기준: 지나가야 하는 칸의 최소값)
    #2) 이러한 물고기들이 많다면 가장 위쪽에 있는 물고기, 가장 왼쪽에 있는 물고기를 먹는다.
#6. 먹은 물고기 수 + 1, 총 시간 + 현재 걸린 시간
#6-1. 아기 상어는 자신의 크기와 같은 수만큼 물고기를 먹으면 크기가 커진다. (ex) 크기가 2라면 물고기 2마리를 먹으면 +1이 된다.)
#7. 먹은 물고기 위치로 아기 상어의 위치를 초기화한다.
#8. #4~ 반복한다.

import sys
from collections import deque

def bfs(current_x, current_y):
    global shark_size
    q = deque()
    visited = [[False for _ in range(N)] for _ in range(N)]
    loc = []
    min_t = None

    q.append([current_x, current_y, 0])
    visited[current_x][current_y] = True
    ocean[current_x][current_y] = 0

    while q:
        x, y, t = q.popleft()
        if ocean[x][y] != 0 and ocean[x][y] < shark_size:
            if min_t is None:
                min_t = t
                loc.append([x, y])
            elif min_t == t:
                loc.append([x, y])

        for dx, dy in [[x+1, y], [x-1, y], [x, y+1], [x, y-1]]:
            if 0 <= dx < N and 0 <= dy < N and not visited[dx][dy]:
                if ocean[dx][dy] <= shark_size:
                    q.append([dx, dy, t+1])
                    visited[dx][dy] = True
    return loc, min_t



def self_baby_shark(start_x, start_y):
    global shark_size
    init_x = start_x
    init_y = start_y
    ate_time = 0
    grow = 0

    while 1:
        min_loc, min_t = bfs(init_x, init_y)
        if len(min_loc) == 0:
            break

        next_x = min_loc[0][0]
        next_y = min_loc[0][1]

        if len(min_loc) > 1:
            next_x, next_y = min(min_loc)
        ate_time += min_t
        init_x = next_x
        init_y = next_y
        grow += 1

        if grow == shark_size:
            shark_size += 1
            grow = 0

    return ate_time

N = int(input())
ocean = [[0 for _ in range(N)] for _ in range(N)]

for i in range(N):
    ocean[i] = list(map(int, sys.stdin.readline().split()))

shark_size = 2

start = [[i,j] for i in range(N) for j in range(N) if ocean[i][j] == 9]
result = self_baby_shark(start[0][0], start[0][1])
print(result)