package com.spimex.spxfeedmds.general.constant;

import org.springframework.beans.factory.annotation.Value;

public class GoofingTest {

    @Value("${spimex.teststring}")
    private static String spimexTestring;

    public static void main(String[] args) {

        String myNewSid = "NSDTDPA-MNEC";
        String sbrSid = "Y8-CBRF";

        //System.out.println(MnecSidPostfix.fromMnecPostfixValueIsEquals(myNewSid));
        //System.out.println(CbrfSidPostfix.fromCbrfPostfixValueIsEquals(sbrSid));

        System.out.println(spimexTestring);

    }

}
