package OOD.Singleton;

/**
 * Created by tianbingleng on 22/10/2017.
 */
public class SingletonExample {
    private static final SingletonExample INSTANCE = new SingletonExample();

    private SingletonExample(){

    }

    public static SingletonExample getInstance() {
        return INSTANCE;
    }

    public void print() {
        System.out.println("Only object, method to print.");
    }
}
