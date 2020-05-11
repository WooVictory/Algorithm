package programmers;

import java.util.LinkedList;

/**
 * created by victory_woo on 2020/05/11
 */
public class PGM42587_2 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 1;
        LinkedList<Document> list = new LinkedList<>();
        LinkedList<Document> result = new LinkedList<>();

        // 1. Document 클래스를 통해 인덱스와 우선 순위를 함께 관리한다.
        for (int i = 0; i < priorities.length; i++) list.add(new Document(i, priorities[i]));

        // 2. 리스트에서 제일 앞의 원소를 꺼낸다.
        while (list.size() > 1) {
            Document doc = list.remove(0);
            boolean flag = true;
            // 3. 나머지 원소와 비교하여 우선 순위가 큰 원소가 하나라도 있다면
            // doc 원소를 리스트의 마지막에 넣는다.
            for (int j = 0; j < list.size(); j++) {
                if (doc.priority < list.get(j).priority) {
                    list.addLast(doc);
                    flag = false;
                    break;
                }
            }

            // 4. flag 값이 true 라면 doc 원소보다 큰 우선 순위를 갖는 원소가 없으므로
            // result 에 추가한다.
            // result : 인쇄할 목록이 순서대로 담겨있다.
            if (flag) result.add(doc);
        }

        // 5. 마지막으로 남아있는 원소 한개를 추가한다.
        result.addAll(list);

        // 6. 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 찾는다.
        // 찾을 때까지 answer 값을 증가시켜 찾아가며, 찾으면 반복문을 종료한다.
        for (Document doc : result) {
            if (doc.index != location) answer++;
            else break;
        }

        return answer;
    }

    static class Document {
        int index;
        int priority;

        Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "Document{" +
                    "index=" + index +
                    ", priority=" + priority +
                    '}';
        }
    }
}
