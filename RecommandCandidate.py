import sys

def sort_frame():
    res = sorted(frame.items(), key=(lambda x: x[1]))
    return res[0][0]

N = int(input())
M = int(input())

recommand = list(map(int, sys.stdin.readline().split()))
frame = dict()

for i in recommand:
    if i in frame:
        frame[i] += 1
    else:
        if len(frame.keys()) == N:
            pop_index = sort_frame()
            del frame[pop_index]
        frame[i] = 1

result = sorted(frame.keys())
for r in result:
    print(r, end=' ')