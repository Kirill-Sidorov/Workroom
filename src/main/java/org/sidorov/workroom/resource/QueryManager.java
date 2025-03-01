package org.sidorov.workroom.resource;

import java.util.ResourceBundle;

public class QueryManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("query");

    private QueryManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
