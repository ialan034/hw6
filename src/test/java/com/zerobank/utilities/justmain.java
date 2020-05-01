package com.zerobank.utilities;

import java.io.File;

public class justmain {
    public static void main(String[] args) {
        String filePath=System.getProperty("user.home") + "/Downloads/"+"8534567-31-11-09.pdf";
        System.out.println("filePath = " + filePath);
        File temp=new File(filePath);
        System.out.println(temp.exists());
    }
}
