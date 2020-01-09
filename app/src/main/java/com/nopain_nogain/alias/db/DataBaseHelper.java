package com.nopain_nogain.alias.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "alias_test.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
