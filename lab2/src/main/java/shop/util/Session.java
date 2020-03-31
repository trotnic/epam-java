package shop.util;/*
 * @project lab2
 * @author vladislav on 3/30/20
 */

import shop.dao.ItemDAO;
import shop.dao.StoreDAO;
import shop.entity.Store;

public class Session {

    private ItemDAO itemDAO;
    private StoreDAO storeDAO;

    public Session() {
        setup();
    }

    private void setup() {
        itemDAO = new ItemDAO();
        storeDAO = new StoreDAO();
    }

    public void beginSession() {
        itemDAO.read();
        storeDAO.read();
    }

    public void saveSession() {
        itemDAO.save();
        storeDAO.save();
    }

    public Store[] getStores() {
        return (Store[]) storeDAO.getCache().toArray();
    }

    public void showStores() {
        storeDAO.getCache().forEach(System.out::println);
    }

    public void showItems() {
        itemDAO.getCache().forEach(System.out::println);
    }
}
