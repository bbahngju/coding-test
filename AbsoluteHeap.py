import heapq
import sys

heap = []
N = int(input())

for _ in range(N):
    x = int(sys.stdin.readline())
    if x != 0:
        heapq.heappush(heap, (abs(x), x))
    else:
        try:
            _, min = heapq.heappop(heap)
            print(min)
        except:
            print(0)