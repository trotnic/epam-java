package shop.util;/*
 * @project lab2
 * @author vladislav on 3/31/20
 */

import shop.entity.Item;
import shop.entity.Store;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Task {
    public static Boolean isPriceGreaterThan(Integer price, Item[] items) {
        return Arrays.stream(items).anyMatch(e -> e.getPrice() > price);
    };

    public static Item[] boundaryItems(Item[] items) {
        List<Item> tmp = Arrays.stream(items)
                .sorted((e1, e2) -> e1.getCount().compareTo(e2.getCount()))
                .collect(Collectors.toList());
        return new Item[]{tmp.get(0), tmp.get(tmp.size() - 1)};
    }

    public static Item[] itemsFilteredWith(Predicate<Item> p, Item[] items) {
        return Arrays.stream(items).filter(p).collect(Collectors.toList()).toArray(Item[]::new);
    }

    public static Item[] itemsSortedByPriceCount(Item[] items) {
        return Arrays.stream(items).sorted((e1, e2) ->
                                            e1.getPrice().compareTo(e2.getPrice()) == 0 ?
                                                e1.getCount().compareTo(e2.getCount()) :
                                                    e1.getPrice().compareTo(e2.getPrice()))
                .collect(Collectors.toList()).toArray(Item[]::new);
    }

    public static Store[] distinctStores(Store[] stores) {
        return Arrays.stream(stores).distinct().collect(Collectors.toList()).toArray(Store[]::new);
    }
}
