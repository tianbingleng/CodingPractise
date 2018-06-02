package OOD.FactoryPattern;

/**
 * Created by tianbingleng on 22/10/2017.
 */
public class Square implements Shape {
    public Square() {

    }
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
