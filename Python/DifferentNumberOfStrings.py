#1개: 1000번, 2개: 999, 3개: 998 ... 1000개: 1
#따라서 시간복잡도 = (1000+1)/(1000/2) = 1001 * 500 = 500500
import sys

S = list(sys.stdin.readline().strip())
N = len(S)
result = set()
for i in range(N):
    for j in range(N-i):
        result.add(''.join(S[j:j+1+i]))

print(len(result))