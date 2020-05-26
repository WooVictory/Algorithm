package programmers;

/**
 * created by victory_woo on 2020/05/25
 * 2016년.
 */
public class PGM12901 {
    public static void main(String[] args) {
        System.out.println(solution(5, 24));
    }

    public static String solution(int a, int b) {
        String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        String[] year = new String[366];
        // 월별로 몇일까지 있는지 미리 구한다.
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int day = 0;

        // 1월 1일은 금요일이다. 따라서 금요일을 year 배열의 0번째 원소로 지정하여 차례로 요일을 설정한다.
        for (int i = 0; i < year.length; i++) {
            year[i] = days[(i + 5) % 7];
        }

        // 해당 월까지 존재하는 일수를 다 더해준다.
        for (int i = 0; i < a - 1; i++) {
            day += month[i];
        }

        // -1을 하지 않으면 1월 1일 금요일을 이미 하루가 지났다고 계산하기 때문에 -1을 해준다.
        day += (b - 1);

        // year 배열을 구해놓았기 때문에 day 일이 무슨 요일에 해당하는지 찾아 반환한다.
        return year[day];
    }
}
