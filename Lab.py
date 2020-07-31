import sys
import copy
from collections import deque
max_safety = 0

def install_wall(n_idx, m_idx, cnt): #DFS + backtracking
    global max_safety
    if cnt == 3:
        s_c = copy.deepcopy(space_copy)
        spread_virus(s_c)
        cnt_safety = count_safety_section(s_c)
        max_safety = max(max_safety, cnt_safety)
        return

    for x in range(n_idx, N):
        for y in range(m_idx, M):
            if space_copy[x][y] == 0:
                space_copy[x][y] = 1
                install_wall(x, y+1, cnt+1)
                space_copy[x][y] = 0
        m_idx = 0



def spread_virus(s_c): #BFS
    global N, M
    q = deque()

    for i in range(N):
        for j in range(M):
            if s_c[i][j] == 2: #virus
                q.append([i,j])
    while q:
        x, y = q.popleft()
        for dx, dy in [[x-1, y], [x+1, y], [x, y-1], [x, y+1]]:
            if 0 <= dx < N and 0 <= dy < M:
                if s_c[dx][dy] == 0:
                    s_c[dx][dy] = 2
                    q.append([dx, dy])

def count_safety_section(s_c):
    global N, M
    cnt = 0
    for i in range(N):
        for j in range(M):
            if s_c[i][j] == 0:
                cnt += 1
    return cnt

N, M = map(int, input().split())
space = [0 for x in range(N)]

for i in range(N):
    space[i] = list(map(int, (sys.stdin.readline()).split()))

space_copy = copy.deepcopy(space)
install_wall(0, 0, 0)
print(max_safety)