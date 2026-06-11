import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class test3 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(3);

        for(int i=0;i<3;i++){
            int threadNum=i+1;
            new Thread(()->{

                try {
                    System.out.println("Thread:"+threadNum+"is working");
                    Thread.sleep(3000);
                    System.out.println("Thread:"+threadNum+"has finished");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        new Thread(()->{
            try {
                System.out.println("Waitting for other thread working");
                countDownLatch.await();
                System.out.println("this thread start to work");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
