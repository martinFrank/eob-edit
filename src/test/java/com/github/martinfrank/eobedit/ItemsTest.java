package com.github.martinfrank.eobedit;

import com.github.martinfrank.eobedit.data.ByteArrayTool;
import com.github.martinfrank.eobedit.data.Item;
import com.github.martinfrank.eobedit.data.Items;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ItemsTest {

    @Test
    public void testIntegrity() {
        for (int i = 0; i < Items.ITEMS.length; i++) {
            byte[] id = ByteArrayTool.fromInt(i, 2);
            System.out.println(Arrays.toString(id));
            Item item = Items.getItem(id);
            Assert.assertNotNull(item);
        }
    }
}
