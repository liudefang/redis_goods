import org.testng.annotations.Test;

public class pertest {

    @Test
    public void test11(){
        int n = 0 ;
        while(n < 100000){
            n = (int)(Math.random()*1000000);
        }
        System.out.println(n);

    }

}
