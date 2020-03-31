package shop.entity;/*
 * @project lab2
 * @author vladislav on 3/30/20
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Store {
    private String name;
    private List<Item> items;
    private List<String> feedbackList;

    private Store() {
        this.items = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
    }

    public Store(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public List<String> getFeedbackList() {
        return feedbackList;
    }

    public void addFeedback(String feedback) {
        this.feedbackList.add(feedback);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Store.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("items=" + items.size())
                .add("feedbackList=" + feedbackList)
                .toString();
    }

    public static class Builder {
        private Store entity;

        public Builder() {
            entity = new Store();
        }

        public Builder withName(String name) {
            entity.setName(name);
            return this;
        }

        public Store build() {
            return entity;
        }
    }
}
