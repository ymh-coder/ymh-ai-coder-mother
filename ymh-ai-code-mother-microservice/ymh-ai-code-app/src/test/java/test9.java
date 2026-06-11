import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
public class test9 {
    private static int count = 0;
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition c1 = lock.newCondition();
    private static final Condition c2 = lock.newCondition();
    private static final Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        solution2();
    }

    private static void solution2() {
        new Thread(()->{
            lock.lock();
            try {
                for(int i=0;i<10;i++){
                    while(count !=0){
                        c1.await();
                    }
                    System.out.print("A");
                    count=(count+1)%3;
                    c2.signal();
                }
            }catch (InterruptedException e){

            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                for(int i=0;i<10;i++){
                    while(count !=1){
                        c2.await();
                    }
                    System.out.print("B");
                    count=(count+1)%3;
                    c3.signal();
                }
            }catch (InterruptedException e){

            }finally {
                lock.unlock();
            }
        }).start();


        new Thread(()->{
            lock.lock();
            try {
                for(int i=0;i<10;i++){
                    while(count !=2){
                        c3.await();
                    }
                    System.out.println("C");
                    count=(count+1)%3;
                    c1.signal();
                }
            }catch (InterruptedException e){

            }finally {
                lock.unlock();
            }
        }).start();

    }

    public static void solution1(){
        Semaphore sA=new Semaphore(1);
        Semaphore sB=new Semaphore(0);
        Semaphore sC=new Semaphore(0);

        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    sA.acquire();
                    System.out.print("A");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    sB.release();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    sB.acquire();
                    System.out.print("B");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    sC.release();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    sC.acquire();
                    System.out.println("C");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    sA.release();
                }
            }
        }).start();
    }
}
