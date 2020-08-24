import sys

def binary_search(start, end):
    global M
    maximum = 0

    while start <= end:
        mid = (start + end) // 2
        hap = 0
        for high in tree:
            if high <= mid:
                continue
            else:
                hap += (high-mid)
        if hap < M:
            end = mid-1
        else:
            maximum = max(maximum, mid)
            start = mid+1

    return maximum

N, M = map(int, input().split())

tree = list(map(int, sys.stdin.readline().split()))
H = max(tree)

result = binary_search(0, H)
print(result)