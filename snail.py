move = [[1, 0], [0, 1], [-1, 0], [0, -1]]
direct = 0

N = int(input())
number = int(input())

numCount = N * N
snail = [[0 for col in range(N)] for row in range(N)]
xCoor = 0
yCoor = 0

while numCount > 0:
    snail[xCoor][yCoor] = numCount
    if (xCoor + move[direct][0]) < 0 or (xCoor + move[direct][0]) >= N \
            or (yCoor + move[direct][1]) < 0 or (yCoor + move[direct][1]) >= N \
            or (snail[xCoor + move[direct][0]][yCoor + move[direct][1]]) != 0:
        direct = (direct + 1) % 4

    xCoor = xCoor + move[direct][0]
    yCoor = yCoor + move[direct][1]
    numCount -= 1

result = [(col+1, row+1) for col in range(N) for row in range(N) if snail[col][row] == number]

for col in snail:
    for row in col:
        print(row, end=" ")
    print('')
print('{0} {1}'.format(result[0][0], result[0][1]))