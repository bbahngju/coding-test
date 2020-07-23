from collections import deque
MAX = 100001

def bfs(start, end):
    q = deque()
    q.append([start, 0]) #location, current time
    visited[start][0] = True
    visited[start][1] = 0
    fast_time = -1
    fast_way = 0
    while q:
        loc, time = q.popleft()
        if loc == end:
            if fast_time == -1:
                fast_time = time
                fast_way += 1
            else:
                if fast_time == time:
                    fast_way += 1

        if 0 <= fast_time < time:
            return fast_time, fast_way


        if loc-1 >= 0 and (not visited[loc-1][0] or visited[loc-1][1] == time):
            q.append([loc-1, time+1])
            visited[loc-1][0] = True
            visited[loc-1][1] = time

        if loc+1 < MAX and (not visited[loc+1][0] or visited[loc+1][1] == time):
            q.append([loc+1, time+1])
            visited[loc+1][0] = True
            visited[loc+1][1] = time

        if 2*loc < MAX and (not visited[2*loc][0] or visited[2*loc][1] == time):
            q.append([2*loc, time+1])
            visited[2*loc][0] = True
            visited[2*loc][1] = time

    return fast_time, fast_way


visited = [[False, 0] for _ in range(MAX)]

start, end = map(int, input().split())

result_time, result_way = bfs(start, end)
print(result_time)
print(result_way)