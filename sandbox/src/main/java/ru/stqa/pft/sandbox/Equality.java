package ru.stqa.pft.sandbox;

/**
 * Created by Ксюшенька on 06.03.2016.
 */
public class Equality {

    public static void main (String args[]){
        String s1 = "firefox";
//        String s2 = new String (s1);
        String s2 = "firefox";

        System.out.println(s1 == s2);// физическое сравнение (ссылки)
        System.out.println(s1.equals(s2));// логическое сравнение (значение)
    }
}
