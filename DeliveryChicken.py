import sys
from collections import deque
sys.setrecursionlimit(10 ** 4 * 2)

N, M = map(int, input().split())
x = [[0 for j in range(N)] for i in range(N)]

def search(c, c_m, max):
    global minimum
    global house
    house_min = [None for i in range(len(house))]

    if c_m == max:
        for v in range(len(visited)):
            if not visited[v]: continue
            i = 0
            c_x, c_y = chicken[v][0], chicken[v][1]
            for h_x, h_y in house:
                value = abs((h_x+1) - (c_x+1)) + abs((h_y+1) - (c_y+1))
                try:
                    if value < house_min[i]:
                        house_min[i] = value
                except:
                    house_min[i] = value
                i += 1

        result = sum(house_min)
        minimum = min(minimum, result)
        return

    for r in range(c, len(chicken)):
        if visited[r] is not True:
            visited[r] = True
            search(r+1, c_m+1, max)
            visited[r] = False


house = []
chicken = []
q = deque()

for i in range(N):
    x[i] = list(map(int, sys.stdin.readline().split()))

for i in range(N):
    for j in range(N):
        if x[i][j] == 1:
            house.append([i,j])
        elif x[i][j] == 2:
            chicken.append([i,j])
        else: continue

minimum = 999999
for max in range(1, M+1):
    visited = [False for i in range(len(chicken))]
    search(0, 0, max)

print(minimum)