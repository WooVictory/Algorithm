package 카카오18;

/**
 * created by victory_woo on 2020/05/06
 * 카카오 18 기출.
 * 다시 푸는 중.
 * 다트 게임.
 */
public class RE_Test2 {
    public static void main(String[] args) {
        System.out.println(solution("1S2D*3T"));
        System.out.println(solution("1D2S#10S"));
        System.out.println(solution("1D2S0T"));
        System.out.println(solution("1S*2T*3S"));
        System.out.println(solution("1D#2S*3S"));
        System.out.println(solution("1T2D3D#"));
        System.out.println(solution("1D2S3T*"));
    }

    public static int solution(String dartResult) {
        int[] result = new int[4];
        int current = 0, index = 1;
        int cache = 0;
        char c;
        for (int i = 0; i < dartResult.length(); i++) {
            c = dartResult.charAt(i);
            // 1. 숫자인지 판별한다.
            if (isNumber(c)) {
                current = c - '0';

                // 1이라면 10이 나올 수 있는 가능성이 존재하므로 cache=1 저장하고 올라간다.
                if (current == 1) {
                    cache = 1;
                    continue;
                }

                // 10이면 current = 10을 저장한다.
                if (cache == 1 && current == 0) current = 10;
            } else if (isBonus(c)) { // 2. 보너스인지 판별한다.
                // S,D,T에 맞도록 제곱하여 result 배열에 저장해놓는다.
                result[index++] = pow(current, c);
                cache = 0;
            } else if (isOption(c)) { // 3. 옵션인지 판별한다.
                switch (c) {
                    case '*': // 스타상인 경우.
                        if (i == 2) { // 첫 등장은 첫 번째 점수에만 적용.
                            result[index - 1] = result[index - 1] * 2;
                        } else { // 그렇지 않은 경우, 해당 점수와 그 앞의 점수에 적용.
                            result[index - 1] = result[index - 1] * 2;
                            result[index - 2] = result[index - 2] * 2;
                        }
                        break;
                    case '#': // 아차상인 경우.
                        // 해당 점수를 음수로 만든다.
                        result[index - 1] = -result[index - 1];
                        break;
                }
            }
        }
        int answer = 0;
        for (int i = 1; i < result.length; i++) answer += result[i];
        return answer;
    }

    // 0 ~ 9 사이의 숫자인지 판별한다.
    private static boolean isNumber(char c) {
        return 48 <= c && c <= 57;
    }

    // S, D, T bonus 중 하나인지 판별한다.
    private static boolean isBonus(char c) {
        return c == 'S' || c == 'D' || c == 'T';
    }

    // num 값을 c에 따라 제곱하여 반환한다.
    private static int pow(int num, char c) {
        switch (c) {
            case 'S':
                return (int) Math.pow(num, 1);
            case 'D':
                return (int) Math.pow(num, 2);
            case 'T':
                return (int) Math.pow(num, 3);
        }
        return 0;
    }

    // 스타상, 아차상 옵션인지 판별한다.
    private static boolean isOption(char c) {
        return c == '*' || c == '#';
    }
}
