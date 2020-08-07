from collections import deque
MAX = 100001

def bfs(start, end):
    q = deque()
    q.append([start, 0]) #location, current time
    visited[start] = True

    while q:
        loc, time = q.popleft()
        if loc == end:
            return time

        if loc-1 >= 0 and not visited[loc-1]:
            q.append([loc-1, time+1])
            visited[loc-1] = True

        if loc+1 < MAX and not visited[loc+1]:
            q.append([loc+1, time+1])
            visited[loc+1] = True

        if 2*loc < MAX and not visited[2*loc]:
            q.append([2*loc, time+1])
            visited[2*loc] = True

visited = [False for _ in range(MAX)]
start, end = map(int, input().split())

result = bfs(start, end)
print(result)