package main.java.se.io.keyboard;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class KeyboardInput {

    public static void getOneByte(){
        System.out.println("请输入：");
        InputStream is = System.in;
        int i = 0;
        while(i != -1){
            try {
                i = is.read();
                if(i == 32){
                    // 如果输入的是空格
                    break ;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public static void getSomeByte(){
        System.out.println("请输入：");
        InputStream is = System.in;
        byte[] byteAry = new byte[4];
        int i = 0;
        while(i != -1){
            try {
                // i = is.read(byteAry);
                i = is.read(byteAry, 2, 2);
                if(i == 1){
                    // 如果什么都不输入直接回车 则退出
                    break ;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(byteAry);
        }
    }

    public static void scanner(){
        System.out.println("请输入：");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(","); // 采用 ， 作为分隔符
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

    }

    public static void main(String[] args)  {
        //getOneByte();
        getSomeByte();
    }
}
