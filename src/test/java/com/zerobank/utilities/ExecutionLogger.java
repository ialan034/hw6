package com.zerobank.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecutionLogger {
    private static Logger logger;
//    public static void main(String[] args) {
//        logger.info("I am in the main method");
//        LoginTest();
//    }
//
//    public static void LoginTest(){
//        logger.error("I got failed");
//    }

    public static Logger getLogger(){
        logger= LogManager.getLogger("zerobank");
        return logger;
    }
}
