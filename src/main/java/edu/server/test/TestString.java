package edu.server.test;

public class TestString {
    public static void main(String[] args) {
        String test = new String("0x00");
        String test1 = new String("0x00");
        switch (test1){
            case "0x00":
                System.out.println("This is 0x00");
                break;
            case "0x01":
                System.out.println("This is 0x01");
                break;
        }
        switch (test){
            case "0x00":
                System.out.println("This is 0x00");
                break;
            case "0x01":
                System.out.println("This is 0x01");
                break;
        }
    }
}
