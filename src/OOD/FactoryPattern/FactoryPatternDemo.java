package OOD.FactoryPattern;

/**
 * Created by tianbingleng on 22/10/2017.
 */


/**
 * In class-based programming,
 * the factory method pattern is a creational pattern that uses factory methods to deal with the problem of creating objects
 * without having to specify the exact class of the object that will be created.
 *
 * This is done by creating objects by calling a factory method
 * —either specified in an interface and implemented by child classes,
 * or implemented in a base class and optionally overridden by derived classes
 *
 * —rather than by calling a constructor.
 *
 * 重要事情说三遍！！ ---> IT IS A METHOD~!!!!!! shapeFactory
 * 1. Separate instance/project creation logic from its usage.
 * 2. More clean especially when we have complicated instance creation logic.
 * 3. Provide chances to have different object allocation strategies (what if we want to reuse shape objects.)
 *
 *
 * Abstract factory 是一个Object.
 *
 * 区别在下边
 * https://stackoverflow.com/questions/5739611/differences-between-abstract-factory-pattern-and-factory-method
 *
 * */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
