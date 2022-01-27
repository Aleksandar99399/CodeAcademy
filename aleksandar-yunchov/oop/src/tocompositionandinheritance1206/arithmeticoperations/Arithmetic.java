package tocompositionandinheritance1206.arithmeticoperations;

public class Arithmetic
{

  public long add(long firstNum, long secondNum){
    return  firstNum+secondNum;
  }

  public int add(int[] nums){
    int sum = 0;
    for (int num : nums) {
       sum += num;
    }
    return sum;
  }
}
