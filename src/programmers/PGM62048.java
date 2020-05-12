package programmers;

/**
 * created by victory_woo on 2020/05/12
 */
public class PGM62048 {
    public static void main(String[] args) {
        System.out.println(solution(8, 12));
    }

    public static long solution(int w, int h) {
        long wl = (long) w;
        long hl = (long) h;
        return wl * hl - ((w + h) - gcd(w, h));
    }

    private static long gcd(int w, int h) {
        long big = w >= h ? w : h;
        long small = w >= h ? h : w;

        while (small != 0) {
            long mod = big % small;
            big = small;
            small = mod;
        }

        return big;
    }
}
