package shop.util;/*
 * @project lab2
 * @author vladislav on 3/31/20
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.entity.Item;
import shop.entity.Store;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Task {
    static Logger logger = LogManager.getLogger();

    public static Boolean isPriceGreaterThan(Integer price, Item[] items) {
        logger.info("Execute isPriceGreaterThan:price:items with params: " + price + " " + Arrays.toString(items));
        return Arrays.stream(items)
                .peek(e -> {logger.info("Peek: " + e);})
                .anyMatch(e -> e.getPrice() > price);
    };

    public static Item[] boundaryItems(Item[] items) {
        logger.info("Execute boundaryItems:items with params: " + Arrays.toString(items));
        List<Item> tmp = Arrays.stream(items)
                .sorted((e1, e2) -> e1.getCount().compareTo(e2.getCount()))
                .peek(e -> logger.info("Peek sorted: " + e))
                .collect(Collectors.toList());
        return new Item[]{tmp.get(0), tmp.get(tmp.size() - 1)};
    }

    public static Item[] itemsFilteredWith(Predicate<Item> p, Item[] items) {
        logger.info("Execute itemsFilteredWith:predicate:items with params: " + p + " " + Arrays.toString(items));
        return Arrays.stream(items)
                .filter(p)
                .peek(e -> logger.info("Peek filtered: " + e))
                .collect(Collectors.toList()).toArray(Item[]::new);
    }

    public static Item[] itemsSortedByPriceCount(Item[] items) {
        logger.info("Execute itemsSortedByPriceCount:items with params: " + Arrays.toString(items));
        return Arrays.stream(items).sorted((e1, e2) ->
                                            e1.getPrice().compareTo(e2.getPrice()) == 0 ?
                                                e1.getCount().compareTo(e2.getCount()) :
                                                    e1.getPrice().compareTo(e2.getPrice()))
                .peek(e -> logger.info("Peek sorted: " + e))
                .collect(Collectors.toList()).toArray(Item[]::new);
    }

    public static Store[] distinctStores(Store[] stores) {
        logger.info("Execute distinctStores:stores with params: " + Arrays.toString(stores));
        return Arrays.stream(stores)
                .distinct()
                .peek(e -> logger.info("Peek distincted: + " + e))
                .collect(Collectors.toList()).toArray(Store[]::new);
    }
}
