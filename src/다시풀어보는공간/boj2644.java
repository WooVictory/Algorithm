package 다시풀어보는공간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * created by victory_woo on 2020/03/27
 * 촌수 계산.
 * 다시 풀어보니 틀렸다. 이유가 뭘까?
 * 처음 접근 : 나는 1부터 탐색을 시작했다. 그러다보니 4,5에 대해서는 탐색을 하지 않았다.
 * 그냥 시작점을 1부터 했기 때문이다.
 * <p>
 * 다음 접근 : for 반복문을 돌며 Component 별로 탐색을 진행했다. 그러면 모든 정점을 탐색할 수 있다.
 * 하지만, 7과 3의 촌수 관계를 원할 때 원하는 답을 얻을 수 있는데 7과 4,5,6의 촌수 관계에 대해서도 관계가 있음을 반환한다.
 * 결국, 같은 컴포넌트 내에서만 촌수 관계 계산을 해야 한다.
 * <p>
 * 다다음 접근 : 촌수 계산에 필요한 x,y를 입력받는다. x는 부모, y가 자식이 된다.
 * 부모부터 탐색하여 연결된 정점에 대해서 다른 정점으로 가는데 얼만큼의 거리가 걸리는지 계산한다.
 * 그리고 y 즉, 자식에 대하여 거리 값을 확인했을 때, 0이라면 촌수 관계가 없고 아니라면 촌수 관계가 있으므로 해당 값을 반환한다.
 * 이렇게 하면 부모와 자식이 들어있는 컴포넌트에 대해서만 값을 계산할 수 있다. 즉, 4,5,6은 다른 컴포넌트이기 때문에 값을 계산할 필요가 없다.
 * 처음 접근과 비슷하지만, 1부터 시작할 경우 내가 알고 싶은 촌수의 부모 자식 관계가 4,5일 때는 성립되지 않기 때문에
 * 내가 계산하고 싶은 부모부터 탐색을 시작하는 게 맞다.
 */
public class boj2644 {
    private static int n, m;
    private static ArrayList<Integer>[] map;
    private static int[] distance;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = toInt(br.readLine());

        map = (ArrayList<Integer>[]) new ArrayList[n + 1];
        distance = new int[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) map[i] = new ArrayList<>();

        String[] in = br.readLine().split(" ");
        // 부모 자식 간의 관계를 나타내는 x,y
        // x : 부모, y : 자식
        int x = toInt(in[0]);
        int y = toInt(in[1]);

        m = toInt(br.readLine());
        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            int v1 = toInt(in[0]);
            int v2 = toInt(in[1]);

            map[v1].add(v2);
            map[v2].add(v1);
        }

        // 두 사람의 촌수 계산을 원하는 것이기 때문에 부모인 x부터 탐색을 진행한다.
        // 그래야 y가 같은 컴포넌트 상에 있을 때, 정확한 촌수 관계 값을 반환한다.
        // 같은 컴포넌트가 아니라면 0이기 때문에 -1을 반환한다.
        // 그리고 같은 컴포넌트만 탐색하기 때문에 효율적이다.
        bfs(x);
        System.out.println(distance[y] != 0 ? distance[y] : -1);
    }

    private static void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(v);
        distance[v] = 0;

        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int vv : map[cur]) {
                if (!visit[vv]) {
                    visit[vv] = true;
                    distance[vv] = distance[cur] + 1;
                    q.add(vv);
                }
            }
        }
    }

    private static int toInt(String value) {
        return Integer.parseInt(value);
    }
}
