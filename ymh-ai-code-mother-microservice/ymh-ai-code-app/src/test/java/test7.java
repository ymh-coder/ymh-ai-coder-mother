import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class test7 {
    public static void main(String[] args) {
        int[] nums={4,3,2,1};
        int[] ans = getMinLexArray(nums);
        for(int x:ans){
            System.out.println(x);
        }
    }

    public static int[] getMinLexArray(int[] nums){
        int n=nums.length;
        int[] ans=new int[n];

        boolean[] exists=new boolean[n+1];
        for(int x:nums){
            exists[x]=true;
        }
        UnionFind uf=new UnionFind(n+1);
        for(int x=1;x<=n;x++){
            if(!exists[x]) continue;
            for(int y=2*x;y<=n;y+=x){
                if(!exists[y]) continue;
                uf.union(x,y);
            }
        }

        Map<Integer, List<Integer>> indexMap=new HashMap<>();
        Map<Integer, List<Integer>> valueMap=new HashMap<>();
        for(int i=0;i<n;i++){
            int root=uf.find(nums[i]);
            indexMap.computeIfAbsent(root,k -> new ArrayList<>()).add(i);
            valueMap.computeIfAbsent(root,k -> new ArrayList<>()).add(nums[i]);
        }

        for(int root:indexMap.keySet()){
            List<Integer> indexList = indexMap.get(root);
            List<Integer> rootList = valueMap.get(root);
            Collections.sort(rootList);
            for(int i=0;i<indexList.size();i++){
                ans[indexList.get(i)]=rootList.get(i);
            }
        }
        return ans;
    }
}

class UnionFind{
    int[] parent;
    int[] rank;

    public UnionFind(int n){
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }

    public int find(int x){
        if(parent[x]!=x){
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x,int y){
        int rootx=find(x);
        int rooty=find(y);
        if(rootx!=rooty){
            if(rank[rootx]<rank[rooty]){
                parent[rootx]=rooty;
            }else if(rank[rootx]>rank[rooty]){
                parent[rooty]=rootx;
            }else{
                parent[rooty]=rootx;
                rank[rootx]++;
            }
        }
    }
}
