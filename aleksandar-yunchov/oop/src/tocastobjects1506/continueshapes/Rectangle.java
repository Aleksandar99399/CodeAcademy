package tocastobjects1506.continueshapes;

public class Rectangle extends Shape
{
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    public double getArea()
    {
        return height * width;
    }

    public double getPerimeter()
    {
        return 2 * height + 2 * width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    private double getMaxPossibleSquareInside(){
        if (height >= width){
            return width;
        }else {
            return height;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Правоъгълник с височина ").append(this.height);
        builder.append(" и широчина ").append(this.width);
        builder.append("Страната на най-големия квадрат който се събира в правоъгълника е: ")
            .append(this.getMaxPossibleSquareInside());
        return builder.toString();
    }
}
