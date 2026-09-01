import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        int startX = 0, startY = 0;
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];

        int initialMask = 0;
        if (classroom[startX].charAt(startY) == 'L') {
            initialMask |= (1 << litterId[startX][startY]);
        }

        queue.offer(new int[]{startX, startY, energy, initialMask});
        visited[startX][startY][energy][initialMask] = true;

        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int e = curr[2];
                int mask = curr[3];

                if (mask == targetMask) {
                    return moves;
                }

                if (e == 0) {
                    continue;
                }

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        char nextCell = classroom[nr].charAt(nc);
                        int nextEnergy = (nextCell == 'R') ? energy : e - 1;
                        int nextMask = mask;

                        if (nextCell == 'L') {
                            nextMask |= (1 << litterId[nr][nc]);
                        }

                        if (!visited[nr][nc][nextEnergy][nextMask]) {
                            visited[nr][nc][nextEnergy][nextMask] = true;
                            queue.offer(new int[]{nr, nc, nextEnergy, nextMask});
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}
