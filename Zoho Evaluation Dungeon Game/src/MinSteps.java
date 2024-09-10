import java.util.ArrayList;
import java.util.Arrays;
public class MinSteps {
    public int getMinSteps(int adventurer1,int  adventurer2,int gold1,int gold2,int m,int n,ArrayList<ArrayList<String>> paths){
        int dp[][] = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for(int i[]:dp){
            Arrays.fill(i,-1);
        }
        ArrayList<String> vals = new ArrayList<>();
        vals.add((adventurer1+1)+" "+(adventurer2+1));
        int ans = fun(adventurer1,adventurer2,gold1,gold2,m,n,dp,visited);
        getPath(dp,adventurer1,adventurer2,gold1,gold2,m,n,vals,paths);
        return ans;
    }
    private int fun(int i, int j, int g1, int g2, int m,int n,int dp[][],boolean visited[][]){
        if(i==g1 && j==g2){
            return 0;
        }
        if(i>=m || j>=n || i<0 || j<0)
            return m*n;
        if(dp[i][j]!=-1)
            return dp[i][j];
        int up= m*n,down=m*n,left=m*n,right=m*n;
        visited[i][j] = true;
        if(i+1<m && !visited[i+1][j]){
            visited[i+1][j]=true;
            down = fun(i+1,j,g1,g2,m,n,dp,visited);
            visited[i+1][j]=false;
        }

        if(i-1>=0 && !visited[i-1][j]){
            visited[i-1][j]=true;
            up = fun(i-1,j,g1,g2,m,n,dp,visited);
            visited[i-1][j]=false;
        }

        if(j-1>=0 && !visited[i][j-1]){
            visited[i][j-1]=true;
            left = fun(i,j-1,g1,g2,m,n,dp,visited);
            visited[i][j-1]=false;
        }

        if(j+1<n && !visited[i][j+1]){
            visited[i][j+1]=true;
            right = fun(i,j+1,g1,g2,m,n,dp,visited);
            visited[i][j+1]=false;
        }
        visited[i][j]=false;
        dp[i][j] = Math.min(up,Math.min(down, Math.min(left,right)))+ 1;
        return dp[i][j];
    }
    private void getPath(int[][] dp,int i,int  j,int gold1,int gold2,int m,int n,ArrayList<String> vals,ArrayList<ArrayList<String>> paths){
        if(i==gold1 && j==gold2){
            paths.add(new ArrayList<>(vals));
            return;
        }
        int up=m*n,down=m*n,left=m*n,right=m*n;
        if(i-1>=0)
            up=dp[i-1][j];
        if(i+1<m)
            down=dp[i+1][j];
        if(j-1>=0)
            left=dp[i][j-1];
        if(j+1<n)
            right=dp[i][j+1];
        int min = Math.min(up,Math.min(down, Math.min(left,right)));
        if(min == up){
            vals.add(i+" "+(j+1));
            getPath(dp,i-1,j,gold1,gold2,m,n,vals,paths);
            vals.remove(vals.size()-1);
        }
        if(min == down){
            vals.add((i+2)+" "+(j+1));
            getPath(dp,i+1,j,gold1,gold2,m,n,vals,paths);
            vals.remove(vals.size()-1);
        }
        if(min == left){
            vals.add((i+1)+" "+(j));
            getPath(dp,i,j-1,gold1,gold2,m,n,vals,paths);
            vals.remove(vals.size()-1);
        }
        if(min == right){
            vals.add((i+1)+" "+(j+2));
            getPath(dp,i,j+1,gold1,gold2,m,n,vals,paths);
            vals.remove(vals.size()-1);
        }
    }

}
