import sys
from collections import Counter

S, P = map(int, input().split())
dna = list(sys.stdin.readline().strip())
minimum = list(map(int, sys.stdin.readline().split()))

naming = ['A', 'C', 'G', 'T']

start = 0
end = P-1
result = 0
count = {'A':0, 'C':0, 'G': 0, 'T':0}
for i in range(P):
    count[dna[i]] += 1

while end < len(dna):
    available = True
    for i in range(4):
        if count[naming[i]] < minimum[i]:
            available = False
            break;
    if available:
        result += 1

    count[dna[start]] -= 1

    start += 1
    end += 1
    try:
        count[dna[end]] += 1
    except:
        continue

print(result)


