package com.github.martinfrank.eobedit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Locale;

public class ByteArrayTest {

    @Test
    public void arrayCopyTest(){
        byte[] first = new byte[]{1,1,1,1,1,1,1,1,1,1};
        byte[] second = new byte[]{2,2,2};

        int lengthBefore = first.length;
        System.arraycopy(second,0, first,2,2);
        System.out.println(Arrays.toString(first));
        Assert.assertEquals(lengthBefore, first.length);
    }

    @Test
    public void conversionTest(){
        String str = "EE";
        int strAsInt = Integer.parseInt(str, 16);
        byte strAsByte = (byte)strAsInt;
        int back = 0xff & strAsByte;
        Assert.assertEquals(strAsInt, back);
        Assert.assertTrue(str.equalsIgnoreCase(Integer.toHexString(back)));
    }
}
