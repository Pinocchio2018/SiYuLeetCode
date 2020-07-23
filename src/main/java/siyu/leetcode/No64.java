package siyu.leetcode;

public class No64 {


    public static void main(String[] args) {
        int[][] grid = new int[3][3];
        grid[0] = new int[]{1, 3, 1};
        grid[1] = new int[]{1, 5, 1};
        grid[2] = new int[]{4, 2, 1};


        System.out.println(new No64().minPathSum(grid));
        grid = new int[2][3];
        grid[0] = new int[]{1, 2, 5};
        grid[1] = new int[]{3, 2, 1};
        System.out.println(new No64().minPathSum(grid));


        grid = new int[][]{
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3}, {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3}, {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5}, {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2}, {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0}, {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0}, {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}};
        System.out.println(new No64().minPathSum(grid));

    }

    public int minPathSum2(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0) {//代表上边界
                    if (j < grid[i].length - 1) {
                        grid[i][j + 1] = grid[i][j + 1] + grid[i][j];
                        continue;
                    } else {
                        continue;
                    }

                }
                if (j == 0) {//代表左边界
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                    continue;
                }

                grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);

            }


        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public int minPathSum(int[][] grid) {
//        return getSmallestSum(grid, 0, 0);
        return minPathSum2(grid);

    }

    private int getSmallestSum(int[][] grid, int x, int y) {

        //如果 x,y 已经到了右下角
        if (y == grid.length - 1 && grid[0].length - 1 == x) {
            return grid[y][x];
        }

        // 如果到了最下方，只能向右
        if (y == grid.length - 1) {
            int goRight = getSmallestSum(grid, x + 1, y) + grid[y][x];
            return goRight;//返回向右以后的最小值加上当前值
        } else if (x == grid[0].length - 1) {
            // 如果到了最右，只能向下
            int goDown = getSmallestSum(grid, x, y + 1) + grid[y][x];
            return goDown;//返回向下以后的最小值加上当前值
        } else {
            int curGrid = grid[y][x];
            //向右找最小
            int goRight = getSmallestSum(grid, x + 1, y) + curGrid;
            //向左找最小
            int goDown = getSmallestSum(grid, x, y + 1) + curGrid;
            return Math.min(goRight, goDown);
        }
    }


}
