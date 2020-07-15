import collections

def DFS(V):
    visited[V] = True
    print(V+1, end=" ")
    loc = 0

    for i in graph[V]:
        if i == 1 and visited[loc] is not True:
            DFS(loc)
        loc += 1
    return 0

def BFS(V):
    BFS_queue = collections.deque()
    BFS_queue.append(V) #push
    visited[V] = True

    while BFS_queue:
        loc = 0
        pop = BFS_queue.popleft()
        print(pop+1, end=" ")
        for i in graph[pop]:
            if i == 1 and visited[loc] is not True:
                BFS_queue.append(loc)
                visited[loc] = True
            loc += 1
    return 0


N, M, V = input().split()
N = int(N)
M = int(M)
V = int(V)

graph = [[0 for i in range(N)] for j in range(N)]  # 이중 list[정점번호][간선연결 유무]
visited = [False for v in range(N)]  # 정점 방문 유무
while M > 0:
    input1, input2 = input().split()
    scr = int(input1) - 1
    dst = int(input2) - 1
    graph[scr][dst] = 1
    graph[dst][scr] = 1

    M -= 1

DFS(V - 1)
visited = [False for v in range(N)] #초기화
print()
BFS(V - 1)