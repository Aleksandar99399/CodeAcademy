package firstexercise;

public class Strings
{
  public static void main(String[] args)
  {

//    String num = "0x7DEDEDE1";
//
//    System.out.println(Integer.parseInt(num,36));

    int num = 0x7DEDEDE1;

    System.out.println("0b" + Integer.toBinaryString(num));
    System.out.println("0" + Integer.toOctalString(num));
    System.out.println(Integer.parseInt(String.valueOf(num), 10));
    System.out.println(String.valueOf("0x" + Integer.toHexString(num)).toLowerCase());
    System.out.println(Integer.toString(num, 36).toUpperCase());

  }
}
