package com.tusia.practise.service;

import android.content.Context;

import com.tusia.practise.PractiseApp;
import com.tusia.practise.model.DaoSession;
import com.tusia.practise.model.Item;
import com.tusia.practise.model.ItemDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ItemService {

    ItemDao itemDao;

    public ItemService(DaoSession daoSession) {
        itemDao = daoSession.getItemDao();
    }

    public List<Item> getItems() {
        if (itemDao == null) {
            return new ArrayList<>();
        }
        System.out.println("Items size: " + countItems() );
        return itemDao.loadAll();
    }

    public void saveItem(Item item) {
        itemDao.save(item);
        System.out.println("Items size: " + countItems() );
    }

    public void deleteFirst(){
        List<Item> items = getItems();
        if (items.size() > 0) {
            itemDao.delete(items.get(0));
        }
        System.out.println("Items size: " + countItems() );
    }

    public void deleteItem(Item item) {
        itemDao.delete(item);
        System.out.println("Items size: " + countItems() );
    }

    public long countItems(){
        return itemDao.count();
    }

   }
