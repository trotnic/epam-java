package shop.util;/*
 * @project lab2
 * @author vladislav on 3/30/20
 */

import shop.entity.Item;
import shop.entity.Store;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Process {
    private static Item[] items;
    private static Store[] stores;

    private static final String[] storesNames = {
            "lol",
            "name",
            "morename",
            "seazhopa",
            "prostozhopa",
            "elegant",
    };

    private static final String[] itemsNames = {
            "cucumber",
            "porridge",
            "tomato",
            "potato",
            "apple",
            "melon"
    };

    private static void initialize(Integer size) {
        items = new Item[size];
        stores = new Store[size];
    }

    public static void setup(Integer size) {
        initialize(size);

        for (int i = 0; i < size; i++) {
            items[i] = (new Item.Builder()
                    .withName(itemsNames[(int) (Math.random() * 5) + 1])
                    .withPrice((int) (Math.random() * 1000) + 100)
                    .withCount((int)(Math.random() * 200) + 10)
                    .build());

            stores[i] = (new Store.Builder()
                    .withName(storesNames[(int)(Math.random() * 5) + 1])
                    .build());
        }

        for(int i = 0; i < size; i++) {
            Integer choice = (int)(Math.random() * (size - 1)) + 1;
            stores[i].addItem(items[choice]);
            items[choice].addStore(stores[i]);
        }
    }

    public static Boolean isPriceGreaterThan(Integer price) {
        return Arrays.stream(items).anyMatch(e -> e.getPrice() > price);
    };

    public static Item[] boundaryItems() {
        List<Item> tmp = Arrays.stream(items)
                        .sorted((e1, e2) -> e1.getCount().compareTo(e2.getCount()))
                        .collect(Collectors.toList());
        return new Item[]{tmp.get(0), tmp.get(tmp.size() - 1)};
    }

    public static List<Item> itemsFilteredWith(Predicate<Item> p) {
        return Arrays.stream(items).filter(p).collect(Collectors.toList());
    }

    public static Store[] getStores() {
        return stores;
    }

    public static List<Item> itemsSortedByPriceCount() {
        return Arrays.stream(items).sorted((e1, e2) -> {
            return e1.getPrice().compareTo(e2.getPrice()) == 0 ? e1.getCount().compareTo(e2.getCount()) : e1.getPrice().compareTo(e2.getPrice());
        }).collect(Collectors.toList());
    }

    public static List<Store> distinctStores() {
        return Arrays.stream(stores).distinct().collect(Collectors.toList());
    }

    public static void showStores() {
        Arrays.stream(stores).forEach(System.out::println);
    }

    public static void showItems() {
        Arrays.stream(items).forEach(System.out::println);
    }



}
