import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>(true, true);
        for(int i=0; i< 1; i++) {
            myLinkedList.add(""+i);
        }
        myLinkedList.addIndex(0,"hello");
    }
}//