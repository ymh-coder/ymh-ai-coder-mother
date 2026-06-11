import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class test5 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val=x;
        }
    }

    private List<List<Integer>> ans=new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root,int sum){
        if(root==null){
            return ans;
        }
        dfsStart(root,sum);
        return  ans;
    }

    private void dfsStart(TreeNode root, int sum) {
        if(root==null) return;
        findPath(root,sum,new ArrayList<>());
        dfsStart(root.left,sum);
        dfsStart(root.right,sum);
    }

    private void findPath(TreeNode root, int sum, List<Integer> path) {
        if(root==null) return;
        path.add(root.val);
        sum-=root.val;
        if(sum==0){
            ans.add(new ArrayList<>(path));
        }
        findPath(root.left,sum,path);
        findPath(root.right,sum,path);
        path.remove((path.size()-1));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        test5 solution=new test5();
        List<List<Integer>> paths = solution.pathSum(root, 8);
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

    }
}
