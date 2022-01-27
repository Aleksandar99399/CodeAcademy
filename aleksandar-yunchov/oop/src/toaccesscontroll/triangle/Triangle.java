package toaccesscontroll.triangle;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static java.lang.Math.*;

public class Triangle
{
  private double aSide;
  private double bSide;
  private double cSide;
  private double alphaAngle;
  private double betaAngle;
  private double gammaAngle;
  private double areaOfTriangle;
  private double perimeterOfTriangle;

  public Triangle(double aSide, double bSide, double gammaAngle)
  {
    this.aSide = aSide;
    this.bSide = bSide;
    this.gammaAngle = gammaAngle;
  }

  public void findSideC()
  {
    double sum = (Math.pow(this.aSide, 2) + Math.pow(this.bSide, 2)) -
        (2 * this.aSide * this.bSide) * Math.cos(Math.toRadians(this.gammaAngle));
    this.setcSide(Math.sqrt(sum));
  }

  public void findBeta()
  {
    BigDecimal beta = BigDecimal.valueOf(acos((pow(this.aSide, 2) + pow(this.cSide, 2) - pow(this.bSide, 2)) / (2 * (this.aSide) * (this.cSide))));
    beta = beta.multiply(BigDecimal.valueOf(180 / PI));
    this.setbetaAngle(Double.parseDouble(beta.toString()));

  }

  public void findAlpha()
  {
    BigDecimal alpha = BigDecimal.valueOf(acos((pow(this.bSide, 2) + pow(this.cSide, 2) - pow(this.aSide, 2)) / (2 * (this.bSide) * (this.cSide))));
    alpha = alpha.multiply(BigDecimal.valueOf(180 / PI));
    this.setalphaAngle(Double.parseDouble(alpha.toString()));
  }

  public void areaOfTriangle()
  {
    double firstSum = this.aSide * this.bSide;
    double secondSum = firstSum * Math.sin(Math.toRadians(this.gammaAngle));
    double area = (1 / 2.0) * secondSum;
    this.areaOfTriangle = formatNumber(area);
  }

  public void perimeterOfTriangle()
  {
    this.perimeterOfTriangle = formatNumber(this.aSide + this.bSide + this.cSide);
  }

  public double getaSide()
  {
    return aSide;
  }

  public void setaSide(double aSide)
  {
    this.aSide = aSide;
  }

  public double getbSide()
  {
    return bSide;
  }

  public void setbSide(double bSide)
  {
    this.bSide = bSide;
  }

  public double getcSide()
  {
    return cSide;
  }

  public void setcSide(double cSide)
  {
    this.cSide = (cSide);
  }

  public double getalphaAngle()
  {
    return alphaAngle;
  }

  public void setalphaAngle(double alphaAngle)
  {

    this.alphaAngle = (alphaAngle);
  }

  private double formatNumber(double number)
  {
    String pattern = "#.###";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);
    String format = decimalFormat.format(number);
    return Double.parseDouble(format);
  }

  public double getbetaAngle()
  {
    return betaAngle;
  }

  public void setbetaAngle(double betaAngle)
  {
    this.betaAngle = (betaAngle);
  }

  public double getgammaAngle()
  {
    return gammaAngle;
  }

  public void setgammaAngle(double gammaAngle)
  {
    this.gammaAngle = gammaAngle;
  }

  @Override
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Side A: ").append(this.aSide);
    builder.append("\nSide B: ").append(this.bSide);
    builder.append("\nSide C: ").append(this.cSide);
    builder.append("\nAngle Alpha: ").append(this.alphaAngle);
    builder.append("\nAngle Beta: ").append(this.betaAngle);
    builder.append("\nAngle Gamma: ").append(this.gammaAngle);
    builder.append("\nPerimeter: ").append(this.perimeterOfTriangle);
    builder.append("\nArea: ").append(this.areaOfTriangle);
    builder.append("\nAngles: ").append(alphaAngle + betaAngle + gammaAngle);

    return builder.toString();
  }
}
