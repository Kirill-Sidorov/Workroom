package org.sidorov.workroom.resource;

import java.util.ResourceBundle;

public class AppManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("app");

    private AppManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
