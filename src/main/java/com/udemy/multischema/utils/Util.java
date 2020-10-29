package com.udemy.multischema.utils;

import java.util.Date;
import java.util.logging.Logger;

public class Util {

    public Logger logger = Logger.getLogger("Util");

    public static Date getStartDate(Date date) {

        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);

        return (Date) date.clone();

    }



    public static Date getEndDate(Date date) {

        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);


        return (Date) date.clone();
    }







}
