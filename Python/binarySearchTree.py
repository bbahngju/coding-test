import sys
sys.setrecursionlimit(10 ** 4 * 2)

def search(start, end):
    root = node[start]
    left = None
    right = None

    if start >= end:
        return
    if start+1 <= end and root > node[start+1]:
        left = start+1

    for i in range(start, end+1):
        if node[i] > root:
            right = i
            break

    if left is not None:
        left_end = right-1 if right is not None else end
        search(left, left_end)
        print(node[left])

    if right is not None:
        search(right, end)
        print(node[right])

cnt = 0
node = []
number = sys.stdin.readline().strip()
while number:
    node.append(int(number))
    number = sys.stdin.readline().strip()
    cnt += 1

search(0, cnt-1)
print(node[0])