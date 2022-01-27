package tocastobjects1506.continueshapes;

public class Square extends Shape
{
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getArea()
    {
        return side * side;
    }

    public double getPerimeter()
    {
        return 4 * side;
    }

    public double getSide() {
        return side;
    }

    public void setHeight(double side) {
        this.side = side;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Квадрат");
        return builder.toString();
    }
}
