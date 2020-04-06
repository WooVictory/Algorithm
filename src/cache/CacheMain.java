package cache;

/**
 * created by victory_woo on 2020/03/30
 */
public class CacheMain {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache();

        cache.put(0, "A");
        cache.show();
        cache.put(1, "B");
        cache.show();
        cache.put(2, "C");
        cache.show();
        cache.put(3, "D");
        cache.show();
        cache.put(4, "E");
        cache.show();
        System.out.println(cache.getMapSize());
        cache.printMap();

        cache.get(2);
        cache.show();

        /*System.out.println(cache.getHead());
        System.out.println(cache.getTail());
        System.out.println(cache.get(1).getPreviousNode());
        cache.show();
        System.out.println(cache.get(2).getPreviousNode());*/

        /*

        // 캐시에 존재하는 데이터 사용함으로써 우선순위가 변경됨.
        cache.put(2, "c");
        cache.show();

        cache.put(5, "F");
        cache.show();

        cache.put(6, "G");
        cache.show();

        System.out.println(cache.get(6));
        cache.show();

        System.out.println(cache.get(3));
        cache.show();

        System.out.println(cache.get(4));
        cache.show();*/


    }
}
