import org.testng.annotations.Test;

import java.util.Random;

public class pertest {

    @Test
    public void test11(){
        int n = 0 ;
        while(n < 100000){
            n = (int)(Math.random()*1000000);
        }
        System.out.println(n);

    }

    @Test
    public void test12(){
        Random random = new Random();
        String result="";
        for(int i=0;i<6;i++){
            result+=random.nextInt(10);
        }
        System.out.print(result);
    }

    @Test
    public void test13(){
        System.out.println((int)((Math.random()*9+1)*100000));
    }

}
