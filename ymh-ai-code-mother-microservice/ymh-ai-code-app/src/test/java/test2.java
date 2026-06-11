import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test2 {
    private static final Object lock=new Object();
    private static int count=1;
    private static final int MAX_COUNT=10;
    public static void main(String[] args) {
        Runnable printOdd=()->{
            synchronized (lock){
                while(count<=MAX_COUNT){
                    if(count%2==0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+":"+count++);
                        lock.notify();
                    }
                }
            }
        };

        Runnable printEven=()->{
            synchronized (lock){
                while(count<=MAX_COUNT){
                    if(count%2==0){
                        System.out.println(Thread.currentThread().getName()+":"+count++);
                        lock.notify();
                    }else{
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        new Thread(printOdd).start();
        new Thread(printEven).start();
    }
}
