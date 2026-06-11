import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Semaphore;

@SpringBootTest
public class test1 {

    private static Semaphore s1=new Semaphore(1);
    private static Semaphore s2=new Semaphore(0);
    private static Semaphore s3=new Semaphore(0);

    public static void main(String[] args) {
        new Thread(()->{
            try {
                for(int i=0;i<10;i++){
                    s1.acquire();
                    System.out.print("A");
                    s2.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()->{
            try {
                for(int i=0;i<10;i++){
                    s2.acquire();
                    System.out.print("B");
                    s3.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(()->{
            try {
                for(int i=0;i<10;i++){
                    s3.acquire();
                    System.out.println("C");
                    s1.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
