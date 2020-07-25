from collections import deque
import sys



MAX = 100001

def bfs(start, end):
    q = deque()
    q.append([start, 0]) #location, current time
    visited[start] = True

    while q:
        loc, time = q.popleft()
        if loc == end:
            history.append(str(loc))
            x = path[loc]

            while x != -1 and x != start:
                history.append(str(x))
                x = path[x]

            if time != 0:
                history.append(str(start))

            return time

        for next in [loc-1, loc+1, loc*2]:
            if 0 <= next < MAX and not visited[next]:
                q.append([next, time + 1])
                visited[next] = True
                path[next] = loc

visited = [False for _ in range(MAX)]
path = [-1 for _ in range(MAX)]
history = []
input = sys.stdin.readline
start, end = map(int, input().split())

result_time = bfs(start, end)
print(result_time)
print(" ".join(reversed(history)))