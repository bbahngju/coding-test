# 백준 19582번 https://www.acmicpc.net/problem/19582
# 시간 복잡도: 처음엔 다 돌리려했는데 그러면 O(N^2)이라서 10만*10만 억이넘는다. (N이 10만인 경우 한 개당 그 안에서 또 10만개를 돌리니까)
# 그래서 O(N)으로 생각해봄. 대회는 순서대로 이루어지니까 이전에 통과한 대회들은 조건을 다 만족한 것. 이번 대회가 조건이 맞지않는다면 이전 대회중 가장 큰 상금 대회를 빼더라도 조건은 다 만족한다.
# + 뺏어도 안되면 지금 대회를 뺀다. (인터넷 검색으로 알게된 조건 ㅜㅜ)
import sys

N = int(input())
info = [[0 for _ in range(2)] for _ in range(N)]

cancel = False #건너뛴 대회 여부
maxAward = 0
totalAward = 0
fail = False

for i in range(N):
    cutLine, award = map(int, sys.stdin.readline().split())
    if totalAward > cutLine:
        if cancel:
            print("Zzz")
            fail = True
            break
        else: #cancel == False
            if totalAward-maxAward > cutLine:
                cancel = True
                continue

            else:
                totalAward -= maxAward
                totalAward += award
                cancel = True
    else:
        totalAward += award
        maxAward = max(maxAward, award)

if not fail:
    print("Kkeo-eok")