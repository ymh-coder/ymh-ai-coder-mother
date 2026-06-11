import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        BounderBuffer buffer=new BounderBuffer(3);

        Runnable producer=()->{
            int value=1;
            while(true){
                try{
                    buffer.produce(value++);
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable consumer=()->{
            while(true){
                try {
                    buffer.consume();
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(producer,"Producer-1").start();
        new Thread(producer,"Producer-2").start();

        new Thread(consumer,"Consumer-1").start();
        new Thread(consumer,"Consumer-2").start();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.execute(()->{
            System.out.println("yes");
        });
    }




}

class BounderBuffer {
    private final Queue<Integer> queue=new LinkedList<>();
    private final int capacity;

    private final Lock lock=new ReentrantLock();
    private final Condition notFull=lock.newCondition();
    private final Condition notEmpty=lock.newCondition();

    public BounderBuffer(int capacity) {
        this.capacity=capacity;
    }

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try{
            while(queue.size()==capacity){
                System.out.println(Thread.currentThread().getName()+"队列满，等待。。。");
                notFull.await();
            }

            queue.offer(value);
            System.out.println(Thread.currentThread().getName()+"生产了"+value);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try{
            while(queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + "队列为空，等待。。。");
                notEmpty.await();
            }

            int value=queue.poll();
            System.out.println(Thread.currentThread().getName()+"消费了"+value);
            notFull.signal();
            return value;
        }finally {
            lock.unlock();
        }
    }
}


