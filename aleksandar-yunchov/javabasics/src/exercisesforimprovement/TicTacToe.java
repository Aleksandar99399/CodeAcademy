package exercisesforimprovement;

import java.util.Scanner;
public class TicTacToe
{
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    char input;
    char[][] arr = {
        {'1', '2', '3'},
        {'4', '5', '6'},
        {'7', '8', '9'}
    };
    char player1 = 'X', player2 = 'O';
    print(arr);
    int turn = 0;
    char mark;
    boolean win = false;
    do {
      System.out.print("Играч " + (turn % 2 + 1) + ": ");
      if ((turn % 2 + 1) == 1) {
        mark = 'X';
      }
      else {
        mark = 'O';
      }
      //System.out.println((turn % 2 + 1));
      input = sc.next().charAt(0);
      mark(arr, input, mark);
      print(arr);
      win = state(arr);
      turn++;
    }
    while (!win && turn < 9);
    if (win) {
      System.out.println("Победа!");
    }
  }
  public static void print(char[][] matrix)
  {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
  public static boolean state(char[][] arr)
  {
    boolean win = false;
    //check lines
    for (int i = 0; i < 3; i++) {
      if (arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2]) {
        win = true;
        break;
      }
    }
    //check columns
    if (!win) {
      for (int col = 0; col < 3; col++) {
        if (arr[0][col] == arr[1][col] && arr[0][col] == arr[2][col]) {
          win = true;
          break;
        }
      }
    }
    if (!win) {
      if ((arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]) ||    // diagonal \
          (arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0])) {    // diagonal /
        win = true;
      }
    }
    return win;
  }
  public static void mark(char[][] arr, char input, char marker)
  {
    for (int row = 0; row < arr.length; row++) {
      for (int col = 0; col < arr[row].length; col++) {
        if (arr[row][col] == input) {
          if (arr[row][col] != 'X' && arr[row][col] != 'O') {
            arr[row][col] = marker;
          }
        }
      }
    }
  }
}