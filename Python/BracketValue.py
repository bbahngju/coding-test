# 4가지 잘못된 경우 처리 필요
# 1. 총 수가 안맞는 경우
# 2. 수는 맞지만 짝이 안맞는 경우
# 3. 1,2는 만족하지만 괄호 닫힘 열림이 잘못된 경우 ex) )[](
# 4. 안에 괄호가 완성되기 전에 다른 괄호가 먼저 닫히는 경우 ex) [(])

# 닫힌 괄호인경우
# 1) 스택의 top이 열린 괄호라면, pop 후 ()라면 2, []라면 3을 push (4번 검사 필요)
# 2) 스택의 top이 숫자라면, top이 숫자가 아닐때까지 pop하면서 pop된 숫자를 더해준다
#    -> 반복문이 끝나면 pop한 후(열린 괄호 pop) ()라면 2, []라면 3을 더해둔 숫자에 곱한다. (4번 검사 필요)


import sys

# 1,2,3번 해결
def check():
    dic = {'(':0, ')':0, '[':0, ']':0}

    for i in brankList:
        dic[i] += 1
        if dic['('] < dic[')'] or dic['['] < dic[']']: #)[](
            return False

    if dic['('] != dic[')'] or dic['['] != dic[']']:
        return False
    return True

#===============Main===========================
brankList = list(sys.stdin.readline().strip())
stop = False

if len(brankList)%2 != 0 or not check():
    stop = True

else:
    s = []
    result = 0

    for cur in brankList:
        if cur == '(' or cur == '[':
            s.append(cur)
            continue
        # 닫힌 괄호인 경우
        else:
            # stack의 top이 괄호인 경우
            if s[-1] == '(' or s[-1] == '[':
                if (s[-1] == '(' and cur == ']') or (s[-1] == '[' and cur == ')'): # 4번 검사
                    stop = True
                    break
                s.pop()
                if cur == ')':
                    s.append(2)
                else:
                    s.append(3)

            # stack의 top이 숫자인 경우
            else:
                plus = 0
                while isinstance(s[-1], int):
                    value = s.pop()
                    plus += value

                if (s[-1] == '(' and cur == ']') or (s[-1] == '[' and cur == ')'): # 4번 검사
                    stop = True
                    break

                s.pop()
                if cur == ']':
                    s.append(plus * 3)
                else:
                    s.append(plus * 2)

if stop:
    print(0)
else:
    print(sum(s))

