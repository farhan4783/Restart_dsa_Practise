class Solution {
    private Boolean[][][] memo;
    private int rows, cols;

    public boolean hasValidPath(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        
        
        if ((rows + cols - 1) % 2 != 0) {
            return false;
        }

     
        if (grid[0][0] == ')' || grid[rows - 1][cols - 1] == '(') {
            return false;
        }

        int maxBalance = (rows + cols) / 2;
        memo = new Boolean[rows][cols][maxBalance + 1];

        return dfs(0, 0, 0, grid);
    }

    private boolean dfs(int r, int c, int balance, char[][] grid) {
      
        balance += (grid[r][c] == '(') ? 1 : -1;

 
        if (balance < 0) {
            return false;
        }

        int maxPossible = (rows + cols - 1) / 2;
        if (balance > maxPossible) {
            return false;
        }

      
        if (r == rows - 1 && c == cols - 1) {
            return balance == 0;
        }

        
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }

        boolean found = false;

        
        if (r + 1 < rows) {
            found = dfs(r + 1, c, balance, grid);
        }

        
        if (!found && c + 1 < cols) {
            found = dfs(r, c + 1, balance, grid);
        }

        return memo[r][c][balance] = found;
    }
}
