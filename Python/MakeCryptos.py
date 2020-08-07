import sys

def backtracking(i, j, con, vo, c):
    global L, C
    if con + vo == L:
        if con >= 2 and vo >= 1:
            print(''.join(c))
        return

    for m in range(j, C):
        if not visited[m]:
            c[i] = alpha[m]
            visited[m] = True
            if alpha[m] in vowel:
                backtracking(i + 1, m, con, vo+1, c)
            else:
                backtracking(i + 1, m, con+1, vo, c)
            visited[m] = False

vowel = ['a', 'i', 'u', 'e', 'o']

L, C = map(int, input().split())
alpha = sys.stdin.readline().split()
alpha.sort()
visited = [False for _ in range(C)]
cry = ['' for _ in range(L)]

backtracking(0, 0, 0, 0, cry)