import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
public class test6 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[] w=new int[m];
        int[] v=new int[m];
        for(int i=0;i<m;i++){
            w[i]=sc.nextInt();
            v[i]=sc.nextInt();
        }

        int[] dp=new int[n+1];
        for(int i=0;i<m;i++){
            for(int j=w[i];j<=n;j++){
                dp[j]=Math.max(dp[j],dp[j-w[i]]+v[i]);
            }
        }
        System.out.println(dp[n]);
    }
}
