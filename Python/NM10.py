def Permutation(index, start, M):
    global result
    global N

    if index == M:
        result_str = ' '.join(map(str, result))
        if result_str not in result_set:
            print(result_str)
            result_set.add(result_str)
        return 0

    for j in range(start, N+1):
        if visited[j][1] is not True:
            result[index] = visited[j][0]
            visited[j][1] = True
            Permutation(index + 1, j+1, M)
            visited[j][1] = False



N, M = map(int, input().split())
node = list(map(int, input().split()))
node.sort()
visited = {}
result_set = set()

for i in range(1, N+1):
    visited[i] = [node[i-1], False]

result = [0 for i in range(M)]
Permutation(0, 1, M)