def Permutation(index, N, M):
    if index == M:
        for i in range(M):
            print(result[i], end = " ")
        print()
        return 0

    start = 1
    if index != 0:
        start = result[index-1]
    for i in range(start,N+1):
        result[index] = i
        Permutation(index + 1, N, M)

N, M = map(int, input().split())

visited = [False for i in range(N+1)]
result = [0 for i in range(M)]

Permutation(0, N, M)