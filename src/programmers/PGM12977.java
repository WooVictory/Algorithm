package programmers;

/**
 * created by victory_woo on 2020/05/29
 * 소수 만들기.
 */
public class PGM12977 {
    public static void main(String[] args) {
        //System.out.println(solution(new int[]{1, 2, 3, 4}));
        System.out.println(solution(new int[]{1, 2, 7, 6, 4}));
    }

    private static int[] set;
    private static int[] arr;
    private static int count = 0;

    public static int solution(int[] nums) {
        arr = nums;
        set = new int[3];
        combination(nums.length, 3, 0, 0);

        return count;
    }

    // 조합을 이용해서 nums 배열에서 3개만 뽑는다.
    private static void combination(int N, int R, int index, int target) {
        if (R == 0) {
            getResult();
            return;
        }

        if (N == target) return;
        set[index] = target;
        combination(N, R - 1, index + 1, target + 1);
        combination(N, R, index, target + 1);
    }

    // 3개를 뽑으면 3개를 더한다.
    // 더한 값인 sum 이 소수인지 아닌지 판별한다.
    private static void getResult() {
        // 3개의 숫자를 더한다.
        int sum = 0;
        for (int index : set) sum += arr[index];

        // 더한 값 sum 이 소수인지 아닌지 판별한다.
        // 소수는 1과 자기 자신으로만 나누어지므로
        // 다른 숫자로 나누어진다면 소수가 아니다.
        boolean isPrime = true;
        for (int i = 2; i < sum; i++) {
            if (sum % i == 0) {
                isPrime = false;
                break;
            }
        }

        // 소수인 경우만 카운트!
        if (isPrime) count++;
    }
}
