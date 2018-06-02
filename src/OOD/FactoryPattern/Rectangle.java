package OOD.FactoryPattern;

/**
 * Created by tianbingleng on 22/10/2017.
 */
public class Rectangle implements Shape {
    public Rectangle() {

    }
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
