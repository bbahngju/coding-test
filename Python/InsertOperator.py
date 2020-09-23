import sys

def calculate(a, b, op):
    return {'+': a+b, '-': a-b, '*': a*b, '/': a//b}[op]

def dfs(cnt):
    global select_op, trans_op, visited, number
    global minimum, maximum
    pre = '!'
    if cnt == N-1:
        interim = number[0]
        idx = 1
        sign = False
        for x in select_op:
            if x == '/':
                if interim < 0:
                    sign = True
                    interim = abs(interim)
            interim = calculate(interim, number[idx], x)
            if sign:
                interim = -interim
                sign = False
            idx += 1
        maximum = max(maximum, interim)
        minimum = min(minimum, interim)
        return

    for i in range(N-1):
        if not visited[i] and pre != trans_op[i]:
            select_op.append(trans_op[i])
            visited[i] = True
            dfs(cnt+1)
            pre = select_op.pop()
            visited[i] = False


#======main======
trans = ['+', '-', '*', '/']
N = int(input())
number = list(map(int, sys.stdin.readline().split()))
input_op = list(map(int, sys.stdin.readline().split()))
trans_op = []
select_op = []
visited = [False for i in range(N-1)]
minimum = 1000000000
maximum = -1000000000

for i in range(len(input_op)):
    for j in range(input_op[i]):
        trans_op.append(trans[i])

dfs(0)
print(maximum)
print(minimum)