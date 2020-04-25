package 카카오19;


import java.util.*;

/**
 * created by victory_woo on 2020/04/24
 * 카카오 19 기출.
 * 실패율.
 */
public class Test2 {
    public static void main(String[] args) {
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] stages2 = {4, 4, 4, 4, 4};
        System.out.println(solution(5, stages));
        System.out.println(solution(4, stages2));
    }

    public static int[] solution(int n, int[] stages) {
        Arrays.sort(stages);
        int[] a = new int[n + 2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int s : stages) {
            a[s]++;
            int total = 0;
            for (int stage : stages) {
                if (s <= stage) {
                    total++;
                }
            }

            map.put(s, total);
        }

        ArrayList<Node> list = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            int key = map.getOrDefault(i, i);
            float fail = (float) a[i] / key;
            System.out.println(i + "/" + key + " = " + fail);
            list.add(new Node(i, fail));
        }

        Collections.sort(list);
        int[] answer = new int[n];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).index;
            System.out.println(answer[i]);
        }

        return answer;
    }


    static class Node implements Comparable<Node> {
        int index;
        float failRate;

        public Node(int index, float failRate) {
            this.index = index;
            this.failRate = failRate;
        }


        @Override
        public int compareTo(Node that) {
            if (this.failRate == that.failRate) {
                return Integer.compare(this.index, that.index);
            }
            return Float.compare(that.failRate, this.failRate);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", failRate=" + failRate +
                    '}';
        }
    }
}
