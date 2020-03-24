package programmers;

/**
 * created by victory_woo on 2020/03/24
 * 다트 게임. -> 2017 카카오 블라인드 코딩 테스트 2번.
 * 특별한 알고리즘을 사용하는게 아닌 단순 구현 문제라고 보면 될 것 같다.
 * 대신, 문자열을 가지고 구현 문제를 풀어내야 한다.
 * 여기서 주의해야 할 점은 10이 등장할 때이다.
 * 나는 1~9까지의 숫자만 주어지는 줄 알고 이 부분을 고려하지 않고 풀었었다.
 * 하지만, 10도 등장할 수 있기 때문에 1이 등장했을 때, 다음 문자열이 0이 등장한다면 10을 current 값으로 사용해야 한다.
 * 이를 위해 1이 등장했을 때, 1을 cache 라는 변수에 저장한 뒤, 다음 문자를 확인할 때, cache == 1이고 다음 문자가 0이라면
 * current = 10을 저장한다.
 * 그렇지 않다면 다른 연산을 할때, cache = 0으로 초기화한다.
 */
public class PGM17682 {
    public static void main(String[] args) {
        //solution("1S2D*3T"); // 2 8 27
        //solution("1S*2T*3S"); // 4 16 3
        //System.out.println(solution("1D2S#10S")); // 이 테케는 안됨.
        System.out.println(solution("1D2S0T")); // 1 2 0
        //solution("1D#2S*3S"); // -2 4 3
        //solution("1T2D3D#"); // 1 4 -9
        //System.out.println(solution("1D2S3T*")); // 1 4 54
    }

    public static int solution(String dartResult) {
        int answer = 0;
        int current = 0;
        int index = 1;
        int cache = 0;
        int[] result = new int[4];
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (isSingleOrDoubleOrTriple(c)) {
                result[index++] = pow(c, current);
                cache = 0;
            } else if (isStarOrAcha(c)) {
                switch (c) {
                    case '*':
                        if (index == 2) {
                            result[index - 1] *= 2;
                        } else {
                            result[index - 2] *= 2;
                            result[index - 1] *= 2;
                        }
                        break;
                    case '#':
                        result[index - 1] = -result[index - 1];
                        break;
                }
                cache = 0;
            } else {
                current = c - '0';
                if (current == 1) {
                    cache = 1;
                    continue;
                }

                if (cache == 1 && current == 0) current = 10;
            }
        }

        for (int i = 1; i <= 3; i++) answer += result[i];

        return answer;
    }

    public static boolean isSingleOrDoubleOrTriple(char c) {
        return c == 'S' || c == 'D' || c == 'T';
    }

    public static boolean isStarOrAcha(char c) {
        return c == '*' || c == '#';
    }

    public static int pow(char c, int current) {
        switch (c) {
            case 'S':
                return (int) Math.pow(current, 1);
            case 'D':
                return (int) Math.pow(current, 2);
            case 'T':
                return (int) Math.pow(current, 3);
        }
        return -1;
    }
}
