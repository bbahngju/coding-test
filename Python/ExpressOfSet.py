#1. operation == 0: union을 이용하여
#2. operation == 1: find를 이용하여 둘의 루트노드가 같다면 YES 출력 / 같지않다면 NO 출력력import sys
import sys

class DisjointSet:
    def __init__(self, n):
        self.data = [i for i in range(n)]

    def find(self, index):
        value = self.data[index]

        if value == index:
            return index

        return self.find(value)

    def union(self, a, b):
        v1 = self.find(a)
        v2 = self.find(b)

        if v1 == v2:
            return

        if self.data[v1] < self.data[v2]:
            self.data[v2] = v1
        else:
            self.data[v1] = v2




N, M = map(int, input().split())
disjoint = DisjointSet(N+1)

for i in range(M):
    operation, a, b = map(int, sys.stdin.readline().split())

    if operation == 0:
        disjoint.union(a, b)
    elif operation == 1:
        if disjoint.find(a) == disjoint.find(b):
            print("YES")
        else:
            print("NO")

