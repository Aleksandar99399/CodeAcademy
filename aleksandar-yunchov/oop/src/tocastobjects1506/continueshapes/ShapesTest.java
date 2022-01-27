package tocastobjects1506.continueshapes;

public class ShapesTest {

    public static void main(String[] args) {

       Shape circle = new Circle(5);
       Shape triangle = new TriangleShape(9,12,91);
       Shape rectangle = new Rectangle(6,8);
       Shape square = new Square(4);

       if (circle instanceof Circle){
         System.out.println(circle.toString());
       }
       if (triangle instanceof TriangleShape){
         if (((TriangleShape) triangle).checkTriangleObtuse()){
           System.out.println(triangle.toString());
         }
       }
       if (rectangle instanceof Rectangle){
         System.out.println(rectangle.toString());
       }
       if (square instanceof Square){
         System.out.println(square.toString());
       }
    }
}
