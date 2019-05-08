package com.tensquare.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 14:54 2019/4/27
 */
public class Ceshi {
    public static void main(String[] args) {
        List list = new LinkedList();
        methodd(list, "4");
        System.out.println("汉子" == "汉子");
    }


    public static void methodd(List list, String str) {

        try {
            if (str == "1" || str == "2" || str == "3" || str == "4") {
                list.add(0, "U" + str);
                System.out.println(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List objects = Arrays.asList("U5", "U6", "U7", "U8");
    }
}
