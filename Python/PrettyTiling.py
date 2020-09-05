# 18230. 2xN 예쁜 타일링
# https://www.acmicpc.net/problem/18230

import sys
sys.setrecursionlimit(10**6)

def dp(n):
    global idx_a, idx_b
    odd = 0
    if n % 2 != 0:
        odd = a[idx_a]
        idx_a -= 1
        n -= 1

    for i in range(2, n+1, 2):
        best = 0

        if idx_a < 1 or idx_b < 0: #가능 타일 수 확인
            if idx_a < 1:
                best = pretty[i-2]+b[idx_b]
                idx_b -= 1
            else:
                best = pretty[i-2]+a[idx_a]+a[idx_a-1]
                idx_a -= 2
        else:
            candidate1 = pretty[i-2]+a[idx_a]+a[idx_a-1]
            candidate2 = pretty[i-2]+b[idx_b]
            if candidate1 >= candidate2:
                best = candidate1
                idx_a -= 2
            else:
                best = candidate2
                idx_b -= 1
        pretty[i] = best

    return pretty[n] + odd

#=====main=====
N, A, B = map(int, input().split())
a = sorted(list(map(int, sys.stdin.readline().split())))
b = sorted(list(map(int, sys.stdin.readline().split())))

pretty = [0 for i in range(N+1)]

idx_a = len(a)-1
idx_b = len(b)-1

result = dp(N)
print(result)