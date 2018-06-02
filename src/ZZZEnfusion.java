/**
 * Created by tianbingleng on 21/6/2017.
 */
import java.util.*;
public class ZZZEnfusion {
    static public void main(String[] args) {
        /*
        * Given a list on an object with String, Int as data fields.
        * Return sorted by count in descending order and by String in alphabetical order
        * */
        List<Data> list = new ArrayList<>();
        Data a = new Data (1, "bc");
        Data b = new Data (2,"ab");
        Data c = new Data (3,"ca");
        Data d = new Data (4,"av");
        Data e = new Data (1,"12a");
        Data f = new Data (2,"s3");
        Data g = new Data (3,"a");
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(f);
        list.add(g);
        System.out.println(list);
        Collections.sort(list, new Comparator<Data>(){
            public int compare(Data d1, Data d2) {
                if (d1.count == d2.count) {
                    return d1.word.compareTo(d2.word);
                }
                return d2.count - d1.count;
            }
        });
        System.out.println(list);
    }
}
class Data{
    int count;
    String word;
    public Data(int count, String word) {
        this.count = count;
        this.word = word;
    }
    public String toString() {
        return "[" + count + ","+ word + "]";
    }
}
