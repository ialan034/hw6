package com.zerobank.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {
    static final Logger logger= LogManager.getLogger("com.zerobank.utilities.MyLogger");
    public static void main(String[] args) {
        logger.info("I am in the main method");
        LoginTest();
    }

    public static void LoginTest(){
        logger.error("I got failed");
    }
}
