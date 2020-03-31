package shop.util;

import org.junit.Assert;
import org.junit.Test;
import shop.entity.Item;
import shop.entity.Store;

import static org.junit.Assert.assertEquals;

/*
 * @project lab2
 * @author vladislav on 3/31/20
 */
public class SessionTest {

    @Test
    public void isPriceGreaterThan() {
        Item[] testable = new Item[]{
                new Item.Builder()
                        .withCount(12432)
                        .withPrice(342)
                        .withName("Potato")
                        .build(),
                new Item.Builder()
                        .withCount(30000)
                        .withPrice(4345)
                        .withName("Beans")
                        .build(),
                new Item.Builder()
                        .withCount(543)
                        .withPrice(625)
                        .withName("Apple")
                        .build()
        };

        Boolean expected = true;
        assertEquals(expected, Task.isPriceGreaterThan(500, testable));
    }

    @Test
    public void boundaryItems() {
        Item[] testable = new Item[]{
                new Item.Builder()
                        .withCount(12432)
                        .withPrice(342)
                        .withName("Potato")
                        .build(),
                new Item.Builder()
                        .withCount(30000)
                        .withPrice(4345)
                        .withName("Beans")
                        .build(),
                new Item.Builder()
                        .withCount(543)
                        .withPrice(625)
                        .withName("Apple")
                        .build()
        };
        Item[] expected = new Item[]{
                new Item.Builder()
                        .withCount(543)
                        .withPrice(625)
                        .withName("Apple")
                        .build(),
                new Item.Builder()
                        .withCount(30000)
                        .withPrice(4345)
                        .withName("Beans")
                        .build(),
        };
        assertEquals(expected, Task.boundaryItems(testable));
    }

    @Test
    public void itemsFilteredWith() {
        Item[] testable = new Item[]{
                (new Item.Builder()
                        .withCount(12432)
                        .withPrice(342)
                        .withName("Potato")
                        .build()),
                new Item.Builder()
                        .withCount(30000)
                        .withPrice(4345)
                        .withName("Beans")
                        .build(),
                new Item.Builder()
                        .withCount(543)
                        .withPrice(625)
                        .withName("Apple")
                        .build()
        };
        testable[0]
                .addStore(new Store.Builder()
                .withName("BestStore")
                .build());
        Item[] expected = new Item[]{testable[0]};

        assertEquals(expected, Task.itemsFilteredWith(e -> e.getStores().size() == 1, testable));
    }

    @Test
    public void itemsSortedByPriceCount() {
        Item[] testable = new Item[]{
                (new Item.Builder()
                        .withCount(12432)
                        .withPrice(342)
                        .withName("Potato")
                        .build()),
                new Item.Builder()
                        .withCount(30000)
                        .withPrice(4345)
                        .withName("Beans")
                        .build(),
                new Item.Builder()
                        .withCount(543)
                        .withPrice(625)
                        .withName("Apple")
                        .build()
        };


        Item[] expected = new Item[]{
                new Item.Builder()
                        .withCount(12432)
                        .withPrice(342)
                        .withName("Potato")
                        .build(),
                new Item.Builder()
                        .withCount(543)
                        .withPrice(625)
                        .withName("Apple")
                        .build(),
                new Item.Builder()
                        .withCount(30000)
                        .withPrice(4345)
                        .withName("Beans")
                        .build(),
        };

        assertEquals(expected, Task.itemsSortedByPriceCount(testable));
    }

    @Test
    public void distinctStores() {
        Store[] testable = new Store[]{
                new Store.Builder()
                        .withName("BestStore")
                        .build(),
                new Store.Builder()
                        .withName("BestStore")
                        .build(),
                new Store.Builder()
                        .withName("ElegantStore")
                        .build(),
                new Store.Builder()
                        .withName("TwiStore")
                        .build()

        };

        Store[] expected = new Store[]{
                new Store.Builder()
                        .withName("BestStore")
                        .build(),
                new Store.Builder()
                        .withName("ElegantStore")
                        .build(),
                new Store.Builder()
                        .withName("TwiStore")
                        .build()
        };
        Assert.assertEquals(expected, Task.distinctStores(testable));
    }
}