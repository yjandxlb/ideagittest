package com.yanjian;

interface Lomda{
   public void say();
    public default int div(int i,int b){
        int c=i/b;
        return c;
    }
    public static int mv(int a,int b){
        int c=a*b;
        return c;
    }
}
public class Lomdaa {

    public static void main(String[] args) {

      Lomda  ad=() ->{
           System.out.println("123");
       };
        ad.say();
        System.out.println(ad.div(4,2));
        int mv = Lomda.mv(1, 2);
        System.out.println(mv);
    }

}

