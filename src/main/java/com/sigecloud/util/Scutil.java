package com.sigecloud.util;

import com.google.gson.Gson;
import com.sigecloud.pojo.Widget;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Scutil {

    public static String DASHBOARD = "dashboard";
    public static String APP = "app";
    public static String DASHBOARD_CLASSNAME = "Dashboard";
    public static String DASHBOARD_CLASSNAME_INSTANCE = "dashboard";



    public static Widget loadJsonFile(String jsonFile){
        Gson gson = new Gson();
        Widget widget = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("test.json"));

            widget = gson.fromJson(br, Widget.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return widget;
    }
}
