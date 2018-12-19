package cn.footman.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author footman77
 * @create 2018-12-15 12:57
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    private static ThreadLocal<String> key = new ThreadLocal<String>();

    @Override
    protected Object determineCurrentLookupKey() {
        return this.key.get();
    }

    public static String getKey() {
        return key.get();
    }

    public static void setKey(String key_in) {
        key.set(key_in);
    }
}
