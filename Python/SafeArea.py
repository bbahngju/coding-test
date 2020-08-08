#1. 최대 높이를 구한다(범위: 0 ~ 최대높이) // max() 사용
#2. 각 높이마다 안전영역을 구한다. //BFS
    # 높이가 더 높고, 방문하지 않은 경우 bfs()를 실행한다.
    # 한번 실행된 bfs()는 한 개의 안전영역을 다 방문하고 온다. -> result += 1
#3. 각 높이가 끝날 때마다 최대 안전영역 값을 구한다. // max() 사용
    # 초기 안전영역 값은 1이다. (아무도 물에 잠기지 않았을 때)
#4. 최대 안전영역 값을 출력한다.

import sys
from collections import deque

def bfs(row, col, cut):
    global N
    q = deque()
    q.append([row, col])
    visited[row][col] = True

    while q:
        x, y = q.popleft()
        for dx, dy in [[x+1, y], [x-1, y], [x, y+1], [x, y-1]]:
            if 0 <= dx < N and 0 <= dy < N and area[dx][dy] > cut:
                if not visited[dx][dy]:
                    q.append([dx, dy])
                    visited[dx][dy] = True


N = int(input())
area = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

r = max(max(area))
maximum = 1

#차피 최대면 다 잠김.
for i in range(r):
    visited = [[False for _ in range(N)] for _ in range(N)]
    result = 0
    for row in range(N):
        for col in range(N):
            if area[row][col] > i and not visited[row][col]:
                bfs(row, col, i)
                result += 1

    maximum = max(maximum, result)

print(maximum)