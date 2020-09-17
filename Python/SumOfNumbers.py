#시간 복잡도: start가 끝까지 갈 경우 N, end가 끝까지 갈 경우 N => O(2N)
import sys

N, M = map(int, input().split())
numbers = list(map(int, sys.stdin.readline().split()))

start = 0
end = 0
hap = numbers[0]
count = 0

while end < N:
    if hap == M:
        count += 1
        hap -= numbers[start]
        start += 1
        end += 1
        if end < N:
            hap += numbers[end]

    elif hap > M:
        if start == end:
            hap -= numbers[start]
            start += 1
            end += 1
            if end < N:
                hap += numbers[end]
        else:
            hap -= numbers[start]
            start += 1
    else:
        end += 1
        if end < N:
            hap += numbers[end]

print(count)