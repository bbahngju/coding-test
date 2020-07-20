def Permutation(index, node, M):
    if index == M:
        for i in range(M):
            print(result[i], end = " ")
        print()
        return 0

    for i in node:
        if index != 0 and result[index-1] > i:
            continue
        result[index] = i
        Permutation(index + 1, node, M)

N, M = map(int, input().split())
node = list(map(int, input().split()))
node.sort()

result = [0 for i in range(M)]

Permutation(0, node, M)