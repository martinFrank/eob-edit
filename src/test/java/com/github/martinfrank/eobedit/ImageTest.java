package com.github.martinfrank.eobedit;

import com.github.martinfrank.eobedit.data.Item;
import com.github.martinfrank.eobedit.data.Items;
import com.github.martinfrank.eobedit.data.Portrait;
import com.github.martinfrank.eobedit.image.ImgeProvider;
import org.junit.Assert;
import org.junit.Test;

public class ImageTest {

    private ImgeProvider imgeProvider = new ImgeProvider();

    @Test
    public void testItems() {
        for (Item it : Items.ITEMS) {
            System.out.println("item " + it);
            Assert.assertNotNull(imgeProvider.getItem(it));
        }
    }

    @Test
    public void testPortraits() {
        for (Portrait port : Portrait.values()) {
            System.out.println("portrait " + port);
            Assert.assertNotNull(imgeProvider.getPortrait(port));
        }
    }

    @Test
    public void testGui() {
        Assert.assertNotNull(imgeProvider.getGuiPageA());
        Assert.assertNotNull(imgeProvider.getGuiPageB());

    }
}
