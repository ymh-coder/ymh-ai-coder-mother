import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
public class test8 {
    public static void main(String[] args) {
        int n,m;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        int[][] sum=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                int x=sc.nextInt();
                sum[i][j]=sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1]+x;
            }
        }
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<n-1;i++){
            ans=Math.min(ans,Math.abs(sum[n][m]-2*sum[i+1][m]));
        }
        for(int j=0;j<m-1;j++){
            ans=Math.min(ans,Math.abs(sum[n][m]-2*sum[n][j+1]));
        }
        System.out.println(ans);
    }

}
