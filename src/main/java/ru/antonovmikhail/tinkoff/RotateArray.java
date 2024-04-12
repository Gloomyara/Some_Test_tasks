package ru.antonovmikhail.tinkoff;

import java.util.Arrays;
import java.util.Scanner;

class RotateArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] in = scanner.nextLine().split(" ");
        int n = Integer.parseInt(in[0]);
        String clock = in[1];
        long[][] matrix = new long[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream((scanner.nextLine().split(" "))).mapToLong(Long::parseLong).toArray();
        }
        rotateMatrix(n, clock.equals("R"), matrix);
    }

    public static void rotateMatrix(int n, boolean clock, long[][] matrix) {
        long temp;
        int operations = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                operations += 1;
                temp = matrix[j][i];
                if (clock) {
                    sb.append(String.format("%d  %d  %d  %d\n",
                            j, i, (n - i - 1), (n - j - 1)));
                    matrix[j][i] = matrix[n - i - 1][j];
                    matrix[n - i - 1][j] = temp;
                } else {
                    sb.append(String.format("%d  %d  %d  %d\n",
                            j, i, i, (n - j - 1)));
                    matrix[j][i] = matrix[i][n - j - 1];
                    matrix[i][n - j - 1] = temp;
                }
            }
        }
        System.out.println(operations + "\n" + sb);
    }
}