import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        for(int i=0; i< 10; i++) {
            myLinkedList.add(""+i);
        }
        myLinkedList.reverse();
    }
}