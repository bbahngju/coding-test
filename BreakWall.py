#1. N과 M 입력받은 후 맵을 입력받는다.
#2. (0,0)에서 (M-1,M-1)까지 최단 경로를 구한다. //BFS
#2-1. 벽이 존재하는 데, 부신적이 없다면 벽을 부시고 진행한다.
#2-2. 벽을 부시고 온 경로와 부시지 않고 온 경로가 겹치는 경우를 생각하여 방문여부를 따로 구분한다.
#3. 모든 경우에도 길이 없다면 -1, 최단 경로가 존재한다면 최단 거리를 출력한다.

import sys
from collections import deque

def bfs(start_x, start_y):
    minimum = -1
    q = deque()
    q.append([start_x, start_y, 1, False])
    visited[start_x][start_y] = True

    while q:
        x, y, cnt, destroy = q.popleft()
        if x == N-1 and y == M-1:
            if minimum < 0:
                minimum = cnt
            else:
                minimum = min(cnt, minimum)

        for dx, dy in [[x - 1, y], [x + 1, y], [x, y - 1], [x, y + 1]]:
            if 0 <= dx < N and 0 <= dy < M:
                if not destroy:
                    if create_map[dx][dy] == 0: #벽X
                        if not visited[dx][dy]:
                            q.append([dx, dy, cnt + 1, destroy])
                            visited[dx][dy] = True
                    else:
                        if not broke[dx][dy]:
                            broke[dx][dy] = True
                            q.append([dx, dy, cnt + 1, True])
                else:
                    if create_map[dx][dy] == 0:  # 벽X
                        if not broke[dx][dy]:
                            broke[dx][dy] = True
                            q.append([dx, dy, cnt + 1, destroy])
    return minimum

N, M = map(int, input().split())
create_map = [[0 for _ in range(M)] for _ in range(N)]
visited = [[False for _ in range(M)] for _ in range(N)]
broke = [[False for _ in range(M)] for _ in range(N)]

for i in range(N):
    create_map[i] = list(map(int, sys.stdin.readline().strip()))

result = bfs(0,0)
print(result)