package shop.dao;/*
 * @project lab2
 * @author vladislav on 3/31/20
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shop.entity.Store;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class StoreDAO implements DAO<Store> {
    static Logger logger = LogManager.getLogger();

    private final String path = "data/store.txt";
    private List<Store> cache;

    public StoreDAO() {
        this.cache = new ArrayList<>();
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public void read() {
        logger.info("Execute read:store");
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(path()));
            while((line = br.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, "_");
                while(stringTokenizer.hasMoreElements()) {
                    String name = stringTokenizer.nextElement().toString();
                    this.cache.add(new Store.Builder()
                                    .withName(name)
                                    .build());
                }
            }
            logger.info("Read " + cache.toString().getBytes().length + "bytes");
            br.close();
        } catch (IOException e) {
            logger.info("Exception: " + e);
        }
    }

    @Override
    public void save() {
        logger.info("Execute save:stores");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path()));
            StringJoiner result = new StringJoiner("_");
            getCache().forEach(e -> {
                result.add(e.getName()).add("\n");
            });
            bw.write(result.toString());
            logger.info("Write " + cache.toString().getBytes().length + "bytes");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Store older, Store newer) {
        logger.info("Execute update:stores");
        Integer idx = cache.indexOf(older);
        cache.set(idx, newer);
    }

    @Override
    public void delete(Store obj) {
        cache.remove(obj);
    }

    @Override
    public List<Store> getCache() {
        return this.cache;
    }
}
