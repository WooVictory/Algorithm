package programmers;

/**
 * created by victory_woo on 2020/03/24
 * 체육복. -> 그리디(탐욕법)을 이용한 문제라고 한다.
 * 설명은 코드의 각 줄마다 달았다.
 */
public class PGM42862 {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] lost2 = {3};
        int[] reserve = {1, 3, 5};
        int[] reserve2 = {3};
        int[] reserve3 = {1};
        System.out.println(solution(n, lost, reserve));
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int[] arr = new int[n + 1];
        // arr 배열을 1로 초기화한다.
        for (int i = 1; i <= n; i++) arr[i] = 1;
        // 옷을 잃어버린 학생에 대해서 arr 배열의 값을 0으로 만들어준다.
        for (int value : lost) arr[value] = 0;
        // 여벌 옷이 있는 학생인 경우, arr 배열의 값에 +1을 해준다.
        for (int value : reserve) arr[value] += 1;

        // 배열에서 값을 확인한다.
        // 체육복이 없는 학생은 무조건 왼쪽부터 검사할 수 있도록 한다. 왼쪽에 여벌 옷을 빌려줄 수 있는
        // 학생이 존재하는 경우 빌리고, 없다면 오른쪽을 검사해서 빌리도록 한다.
        // 랜덤으로 빌릴 경우, 오른쪽에 체육복을 빌릴 수 있음에도 빌리지 못하는 경우가 존재하기 때문에
        // 학생이 체육복을 빌릴 수 있는 순서를 강제한다.
        for (int i = 0; i < lost.length; i++) {
            if (arr[lost[i]] == 0) {
                if (1 <= lost[i] - 1 && arr[lost[i] - 1] == 2) {
                    arr[lost[i]] = 1;
                    arr[lost[i] - 1]--;
                } else if (lost[i] + 1 <= n && arr[lost[i] + 1] == 2) {
                    arr[lost[i]] = 1;
                    arr[lost[i] + 1]--;
                }
            }
        }

        // 체육복이 있는 학생의 수를 계산한다.
        int count = 0;
        for (int i = 1; i <= n; i++)
            if (arr[i] != 0) count++;

        return count;
    }
}
