package lenn.db.mysql.libs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by lenn on 16/6/1.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public static String target;

    @Override
    protected Object determineCurrentLookupKey() {
        return target;
    }
}
