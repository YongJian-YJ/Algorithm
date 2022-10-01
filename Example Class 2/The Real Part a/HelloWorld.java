import java.util.ArrayList;

class HelloWorld {
    public static void main(String[] args) {
        ArrayList<Integer> h = new ArrayList<Integer>(5);
        h.add(1);
        h.add(3);
        int x = h.get(1);
        System.out.print(x);
    }
}