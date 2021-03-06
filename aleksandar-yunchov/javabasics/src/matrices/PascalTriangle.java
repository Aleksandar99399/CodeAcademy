package matrices;

public class PascalTriangle
{
  public static void main(String[] args)
  {
    int n = 5;
    int[][] triangle = new int[n][];

    for (int i = 0; i < n; i++) {
      triangle[i] = new int[i + 1];
      triangle[i][0] = 1;
      triangle[i][i] = 1;
      for (int j = 1; j < i; j++) {
        triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
      }
    }
    for (int row = 0; row < triangle.length; row++) {
      for (int col = 0; col < triangle[row].length; col++) {
        System.out.print(triangle[row][col] + " ");
      }
      System.out.println();
    }
  }
}
