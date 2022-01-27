package matrices;


import java.util.Scanner;

public class TheMatrix
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    int rows = 0;
    do {
      System.out.print("Брой редове: ");
      rows = in.nextInt();
    }
    while (rows <= 0 || rows >= 10);

    int cols = 0;
    do {
      System.out.print("Брой колони: ");
      cols = in.nextInt();
    }
    while (cols <= 0 || cols >= 10);

    int[][] matrix1 = new int[rows][cols];
    System.out.println("Въведете матрица 1:");
    addElementsInMatrix(matrix1, in);

    int[][] matrix2 = new int[rows][cols];
    System.out.println("Въведете матрица 2:");
    addElementsInMatrix(matrix2, in);

    printMatrices(matrix1, matrix2);
    System.out.println("Сумата на матриците е:");
    sumOfMatrices(matrix1, matrix2);
    System.out.println("Директната сума на матриците е:");
    directSumOfMatrices(matrix1, matrix2);

  }

  public static void addElementsInMatrix(int[][] matrix, Scanner in)
  {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.printf("Ред %d, Колона %d = ", i, j);
        matrix[i][j] = in.nextInt();
      }
    }
  }

  public static void printMatrices(int[][] m1, int[][] m2)
  {
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[i].length; j++) {
        System.out.printf("%-4d", m1[i][j]);
      }
      System.out.print(" | ");
      for (int j = 0; j < m2[i].length; j++) {
        System.out.printf("%-4d", m2[i][j]);
      }
      System.out.println();
    }
  }

  public static void sumOfMatrices(int[][] m1, int[][] m2)
  {
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[i].length; j++) {
        System.out.printf("%-4d", m1[i][j] + m2[i][j]);
      }
      System.out.println();
    }
  }

  public static void directSumOfMatrices(int[][] m1, int[][] m2)
  {
    int[][] matrix = new int[m1.length + m2.length][m1[0].length + m2[0].length];
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[i].length; j++) {
        matrix[i][j] = m1[i][j];
      }
    }
    for (int i = m1.length; i < m2.length + m1.length; i++) {
      for (int j = m1.length; j < m2.length + m1.length; j++) {
        matrix[i][j] = m2[i - m1.length][j - m1[0].length];
      }
    }

    //print
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.printf("%-4d", matrix[i][j]);
      }
      if (i < matrix.length - 1) {
        System.out.println();
      }
    }
  }

}