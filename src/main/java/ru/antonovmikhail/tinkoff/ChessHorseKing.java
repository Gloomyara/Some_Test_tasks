package ru.antonovmikhail.tinkoff;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class ChessHorseKing {

    private static final int[][] HORSE_DIRECTIONS = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };
    private static final int[][] KING_DIRECTIONS = {
            {-1, -1}, {-1, 1}, {-1, -1}, {-1, 1},
            {1, -1}, {1, 2}, {1, -1}, {1, 1}
    };

    private static int n;
    private static char[][] desk;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        desk = new char[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            desk[i] = scanner.next().toCharArray();
        }
        int sx = -1, sy = -1, fx = -1, fy = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (desk[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (desk[i][j] == 'F') {
                    fx = i;
                    fy = j;
                }
            }
        }
        if (sx == -1 || sy == -1 || fx == -1 || fy == -1) {
            System.out.println(-1);
            return;
        }
        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(sx, sy, 0, true));
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            if (visited[cell.x][cell.y]) {
                continue;
            }
            visited[cell.x][cell.y] = true;
            if (cell.x == fx && cell.y == fy) {
                System.out.println(cell.distance);
                return;
            }
            for (int i = 0; i < HORSE_DIRECTIONS.length; i++) {
                boolean isHorse = cell.isHorse;
                int[] horseDir = HORSE_DIRECTIONS[i];
                int[] kingDir = KING_DIRECTIONS[i];
                int nx;
                int ny;
                if (isHorse) {
                    nx = cell.x + horseDir[0];
                    ny = cell.y + horseDir[1];
                } else {
                    nx = cell.x + kingDir[0];
                    ny = cell.y + kingDir[1];
                }
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (desk[nx][ny] == 'G') {
                        isHorse = false;
                    } else if (desk[nx][ny] == 'K') {
                        isHorse = true;
                    }
                    queue.add(new Cell(nx, ny, cell.distance + 1, isHorse));
                }
            }
        }
        System.out.println(-1);
    }

    private static class Cell {

        int x;
        int y;
        int distance;
        boolean isHorse;

        public Cell(int x, int y, int distance, boolean isHorse) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.isHorse = isHorse;
        }
    }
}