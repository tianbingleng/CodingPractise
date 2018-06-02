package OOD.FactoryPattern;

/**
 * Created by tianbingleng on 22/10/2017.
 */
public class Circle implements Shape {
    public Circle () {

    }
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
