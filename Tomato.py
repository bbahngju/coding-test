#1. M, N, H를 입력받는다 (M: 가로칸 수, N: 세로칸 수, H: 상자 수
#2. 현재 토마토 상태를 입력받는다.
#3. [H][N][M] 형식의 3차원 리스트를 생성한다.
#4. 토마토가 모두 익는 데 걸리는 최소 일수를 구한다. //BFS
#4-1. 각 익은 토마토는 위,아래,왼쪽,오른쪽,앞,뒤에 영향을 준다.
#4-2. 익은 토마토를 찾을 때 안익은 토마토의 총 개수도 구한다.
#4-3. 안익은 토마토가 익을때마다 안익은 토마토의 개수 -1 감소한다.
#4-4. 안익은 토마토의 개수가 0이되면 현재시간+1 리턴한다.
#5. 최소 일수를 출력한다.
#5-1. 처음 상태에 토마토가 모두 익어있다면 0, 토마토가 모두 익을 수 없다면 -1을 출력한다.

import sys
from collections import deque

def bfs():
    global N,M,H

    q = deque()
    green = 0

    for h in range(H):
        for n in range(N):
            for m in range(M):
                if tomato_map[h][n][m] == 1:
                    q.append([h,n,m,0])
                elif tomato_map[h][n][m] == 0:
                    green += 1
    if green == 0:
        return 0

    while q:
        high, n, m, time = q.popleft()

        for dh, dx, dy in [[high+1, n, m], [high-1, n, m], [high, n, m+1], [high, n, m-1], [high, n+1, m], [high, n-1, m]]:
            if 0 <= dh < H and 0 <= dx < N and 0 <= dy < M:
                if tomato_map[dh][dx][dy] == 0:
                    tomato_map[dh][dx][dy] = 1
                    green -= 1
                    if green == 0:
                        return time+1
                    else:
                        q.append([dh,dx,dy,time+1])

    if green != 0:
        return -1

M, N, H = map(int, input().split())
tomato_map = [[[-1 for _ in range(M)] for _ in range(N)] for _ in range(H)]

for h in range(H):
    for n in range(N):
        tomato_map[h][n] = list(map(int, (sys.stdin.readline()).split()))

result = bfs()
print(result)