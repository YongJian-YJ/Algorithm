import java.util.Random;

public class test {
    public static void main(String[] args) {
        //random number generator
        int numberOfEdge=0;
        Random rand = new Random();

        for(int i=0; numberOfEdge<10; i=rand.nextInt(10)){
            if(numberOfEdge==10) break;
            System.out.println(i);
            numberOfEdge++;
            
        }

    }
}
