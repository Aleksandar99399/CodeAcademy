package bigdigits;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EMI
{
  public static void main(String[] args)
  {

    BigDecimal chislo =
        (BigDecimal.valueOf(10.5).divide(BigDecimal.valueOf(100)));
    BigDecimal num = chislo.divide(BigDecimal.valueOf(12));



    BigDecimal firstRow = num.multiply((BigDecimal.valueOf(1).add(num)).pow(120));



    BigDecimal secondRow = ((BigDecimal.valueOf(1).add(num)).pow(120)).subtract(BigDecimal.valueOf(1));


    BigDecimal sum =
        BigDecimal.valueOf(10000000).multiply(firstRow.divide(secondRow, RoundingMode.HALF_UP));

    System.out.println(sum.setScale(10, RoundingMode.HALF_UP));

  }
}
