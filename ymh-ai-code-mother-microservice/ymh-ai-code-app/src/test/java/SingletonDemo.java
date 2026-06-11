import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SingletonDemo {
    private static volatile SingletonDemo singleton;

    public static SingletonDemo getInstance(){
        if(singleton==null){
            synchronized(SingletonDemo.class){
                if(singleton==null){
                    singleton=new SingletonDemo();
                    System.out.println("creating");
                }
            }
        }else{
            System.out.println("done");
        }
        return singleton;
    }
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(()->{

                try {
                    System.out.println(SingletonDemo.getInstance());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
