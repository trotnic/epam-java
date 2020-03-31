package shop.entity;/*
 * @project lab2
 * @author vladislav on 3/30/20
 */

import java.util.*;

public class Item {
    private String name;
    private Integer price;
    private Integer count;
    private List<Store> stores;

    public Item() {
        this.name = null;
        this.price = null;
        this.count = 0;
        this.stores = new ArrayList<Store>();
    }

    public Item(String name, int price) {
        this();
        this.name = name;
        this.price = price;
        this.count += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void addStore(Store store) {
        this.stores.add(store);
    }

    public void removeStore(Store store) {
        this.stores.remove(store);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("count=" + count)
                .add("stores=" + stores.size())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getName().equals(item.getName()) &&
                getPrice().equals(item.getPrice()) &&
                getCount().equals(item.getCount()) &&
                Objects.equals(getStores(), item.getStores());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getCount(), getStores());
    }

    public static class Builder {
        private Item entity;

        public Builder() {
            entity = new Item();
        }

        public Builder withName(String name) {
            entity.setName(name);
            return this;
        }

        public Builder withCount(Integer count) {
            entity.setCount(count);
            return this;
        }

        public Builder withPrice(Integer price) {
            entity.setPrice(price);
            return this;
        }

        public Item build() {
            return entity;
        }
    }
}
