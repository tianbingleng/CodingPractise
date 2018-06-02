package amazon;
import java.util.*;

/**
 * Created by tianbingleng on 4/9/2017.
 */
public class RectangleOverlap {
    public static void main(String[] args) {
        NodePoint l1 = new NodePoint(0, 10);
        NodePoint r1 = new NodePoint(10, 0);
        NodePoint l2 = new NodePoint(5, 5);
        NodePoint r2 = new NodePoint(15, 0);
//        Rectangle rec1 = new Rectangle(l1, r1);
//        Rectangle rec2 = new Rectangle(l2, r2);
//        if (isOverlap(rec1, rec2))
        if (isOverlap(l1, r1, l2, r2))
            System.out.println("Rectangles Overlap");
        else
            System.out.println("Rectangles Don't Overlap");
    }

    static boolean isOverlap(NodePoint l1, NodePoint r1, NodePoint l2, NodePoint r2){
        // recA l1.x               recB l2.x
        //                  r1.x                  r2.x
        if (l2.x >= r1.x || l1.x >= r2.x) {
            return false;
        }
        // recA l1.y
        //                  r1.y
        // recB l2.y
        //                  r2.y

        if (r1.y >= l2.y || r2.y >= l1.y) {
            return false;
        }
        return true;
    }
}

class NodePoint{
    double x;
    double y;
    public NodePoint(double x, double y){
        this.x=x;
        this.y=y;
    }
}
class Rectangle{
    NodePoint topLeft;
    NodePoint bottomRight;
    public Rectangle(NodePoint topLeft, NodePoint bottomRight){
        this.topLeft=topLeft;
        this.bottomRight=bottomRight;
    }
}