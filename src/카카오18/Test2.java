package 카카오18;

/**
 * created by victory_woo on 2020/04/29
 * 카카오 18 기출.
 * [1차] 다트 게임.
 */
public class Test2 {
    public static void main(String[] args) {
        solution("1S2D*3T");
        solution("1D2S#10S");
        solution("1D2S0T");
        solution("1S*2T*3S");
        solution("1D#2S*3S");
        solution("1T2D3D#");
        solution("1D2S3T*");
    }

    private static int solution(String dartResult) {
        int answer = 0;
        int current = 0;
        int cache = 0;
        int index = 1;
        char c;
        int[] result = new int[4];

        for (int i = 0; i < dartResult.length(); i++) {
            c = dartResult.charAt(i);
            if (isNumber(c)) {
                current = c - '0';
                if (current == 1) {
                    cache = 1;
                    continue;
                }

                if (cache == 1 && current == 0) current = 10;

            } else if (isSingleOrDoubleOrTriple(c)) {
                result[index++] = pow(current, c);
                cache = 0;
                // 캐시 값을 초기화하는 부분은 다른 연산을 진행할 때, 해준다.
            } else if (isStarOrAcha(c)) {
                switch (c) {
                    case '*':
                        if (i == 2) {
                            result[index - 1] = result[index - 1] * 2;
                        } else {
                            result[index - 1] = result[index - 1] * 2; // index 가 이미 증가해버렸기 때문에 이게 자신.
                            result[index - 2] = result[index - 2] * 2; // 이게 그 앞에 원소.
                        }
                        break;
                    case '#':
                        result[index - 1] = result[index - 1] * -1;
                        break;
                }
            }
        }

        for (int i = 1; i < 4; i++) answer += result[i];

        System.out.println(answer);

        return answer;
    }

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

    private static boolean isNumber(char c) {
        return 48 <= c && c <= 57;
    }

    private static boolean isSingleOrDoubleOrTriple(char c) {
        return c == 'S' || c == 'D' || c == 'T';
    }

    private static boolean isStarOrAcha(char c) {
        return c == '*' || c == '#';
    }
}