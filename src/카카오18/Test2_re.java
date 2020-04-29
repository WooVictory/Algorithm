package 카카오18;

/**
 * created by victory_woo on 2020/04/29
 * 카카오 18 기출.
 * [1차] 다트 게임.
 * 문자열 처리 및 구현.
 */
public class Test2_re {
    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T"));
        System.out.println(solution("1D2S#10S"));
        System.out.println(solution("1D2S0T"));
    }

    public static int solution(String dartResult) {
        int current = 0, index = 1, cache = 0;
        int[] result = new int[4];
        char c;

        // result 배열을 사용해서 각 세트의 점수를 구분해서 담는다.
        // 그리고 스타상과 아차상일 경우에만 이를 처리해주도록 한다.
        // 10인 경우도 처리해주면 된다.
        for (int i = 0; i < dartResult.length(); i++) {
            c = dartResult.charAt(i);
            if (isNumber(c)) {
                current = c - '0';

                // 10인 경우를 확인하기 위해서 cache 값에 1을 저장하고 반복문의 위로 올라간다.
                if (current == 1) {
                    cache = 1;
                    continue;
                }

                // 10인지 확인하고, 맞다면 current 값에 10을 저장한다.
                if (cache == 1 && current == 0) current = 10;
            } else if (isSingleOrDoubleOrTriple(c)) {
                // pow() 함수를 통해 제곱한 값을 result 배열에 저장하고 index 값을 증가시켜
                // 다음 칸에 다음 세트의 값을 담도록 한다. 즉, 1,2,3 세트를 따로 값을 저장한다.
                result[index++] = pow(current, c);
                cache = 0;
            } else if (isStarOrArcha(c)) {
                switch (c) {
                    case '*':
                        // 첫 번째 기회에서 스타상이 나온 경우, 첫 번째 점수만 스타상을 적용한다.
                        if (i == 2) {
                            result[index - 1] *= 2;
                        } else {
                            // 그렇지 않은 경우, 현재 점수와 이전에 등장했던 점수에 스타상을 적용한다.
                            result[index - 1] *= 2;
                            result[index - 2] *= 2;
                        }
                        break;
                    case '#':
                        // 아차상은 현재 점수에만 적용할 수 있다.
                        result[index - 1] = -result[index - 1];
                        break;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < 4; i++) answer += result[i];
        return answer;
    }

    // 0~9 사이의 숫자인지 확인한다.
    private static boolean isNumber(char c) {
        return 48 <= c && c <= 57;
    }

    // S,D,T 연산인지 확인한다.
    private static boolean isSingleOrDoubleOrTriple(char c) {
        return c == 'S' || c == 'D' || c == 'T';
    }

    // S,D,T에 따라서 1제곱, 2제곱, 3제곱을 해준다.
    private static int pow(int value, char c) {
        switch (c) {
            case 'S':
                return (int) Math.pow(value, 1);
            case 'D':
                return (int) Math.pow(value, 2);
            default:
                return (int) Math.pow(value, 3);
        }
    }

    // 스타상인지 아차상인지 확인한다.
    private static boolean isStarOrArcha(char c) {
        return c == '*' || c == '#';
    }
}
