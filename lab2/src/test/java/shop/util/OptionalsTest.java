package shop.util;/*
 * @project lab2
 * @author vladislav on 4/1/20
 */

import org.junit.Assert;
import org.junit.Test;
import shop.dao.ItemDAO;
import shop.entity.Item;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalsTest {


    @Test
    public void foo() {
        ItemDAO dao = new ItemDAO();
        dao.read();
        System.out.println(Optional.of(dao.getByName("adsd")).isPresent());
        Optional.of(dao.getByName("anyname")).ifPresent(System.out::println);
        Optional<Item> item = Optional.of(dao.getByName("Cucumber")).orElse(Optional.of(new Item()));
        System.out.println(item);
        item = Optional.of(dao.getByName("Potato")).get();
        System.out.println(item);
    }
}
