package com.tusia.practise;

import android.app.Application;

import com.tusia.practise.model.DaoMaster;
import com.tusia.practise.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class PractiseApp extends Application {
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        //database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "practise-db");
        Database db = helper.getWritableDb();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
