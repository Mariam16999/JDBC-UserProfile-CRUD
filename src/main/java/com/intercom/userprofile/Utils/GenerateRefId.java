package com.intercom.userprofile.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenerateRefId {
    public static String generateRefNum(){
        String refNum = "";
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddHHmmssSSS");//yyyyMMddhhmmssMs
        String datetime = ft.format(dNow);
        Random rand = new Random();
        int fNum = rand.nextInt(10);
        refNum = ""+ (fNum == 0?1:fNum ) + rand.nextInt(10) + datetime + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);

        return refNum ;
    }

}
