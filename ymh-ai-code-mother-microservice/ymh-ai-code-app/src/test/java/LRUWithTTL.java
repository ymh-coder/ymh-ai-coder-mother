
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
public class LRUWithTTL {
    private final int capacity;
    private final long ttlMillis;
    private final LinkedHashMap<Integer, Node> map;

    public LRUWithTTL(int capacity,long ttlMillis){
        this.capacity=capacity;
        this.ttlMillis=ttlMillis;

        this.map=new LinkedHashMap<Integer, Node>(16,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Node> eldest) {
                return size()>LRUWithTTL.this.capacity;
            }
        };
    }

    public int get(int key){
        Node node=map.get(key);
        if(node==null){
            return -1;
        }

        if(node.expireTime<System.currentTimeMillis()){
            map.remove(key);
            return -1;
        }
        return node.value;
    }

    public void put(int key,int value){
        cleanupExpired();
        long expireTime=System.currentTimeMillis()+ttlMillis;
        map.put(key,new Node(key,value,expireTime));
    }

    private void cleanupExpired() {
        Iterator<Map.Entry<Integer, Node>> iterator = map.entrySet().iterator();
        long now=System.currentTimeMillis();
        while(iterator.hasNext()){
            Map.Entry<Integer, Node> entry = iterator.next();
            if(entry.getValue().expireTime<now){
                iterator.remove();
            }
        }
    }

    static class Node{
        int key;
        int value;
        long expireTime;

        Node(int key,int value,long expireTime){
            this.key=key;
            this.value=value;
            this.expireTime=expireTime;
        }
    }

    public static void main(String[] args) throws Exception {
        LRUWithTTL cache = new LRUWithTTL(2, 3000);

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.get(1)); // 10，访问后 1 变成最近使用
        cache.put(3, 30);                  // 容量超了，淘汰最久未使用的 2
        System.out.println(cache.get(2)); // -1

        Thread.sleep(3100);
        System.out.println(cache.get(1)); // -1，过期
        System.out.println(cache.get(3)); // -1，过期
    }
}
