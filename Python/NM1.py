def Permutation(index, N, M):
    if index == M:
        for i in range(M):
            print(result[i], end = " ")
        print()
        return 0

    for i in range(1,N+1):
        if visited[i] is not True:
            result[index] = i
            visited[i] = True
            Permutation(index+1, N, M)
            visited[i] = False # backtracking


N, M = map(int, input().split())

visited = [False for i in range(N+1)]
result = [0 for i in range(M)]

Permutation(0, N, M)