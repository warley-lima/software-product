package com.Connection.Configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

import javax.inject.Inject;
import javax.sql.DataSource;

public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    Log logger = LogFactory.getLog(getClass());

    private static final long serialVersionUID = 14535345L;
    private DataSource defaultDataSource;
    private DataSourceLookup dataSourceLookup;

    @Inject
    public MultiTenantConnectionProviderImpl(DataSource defaultDataSource, DataSourceLookup dataSourceLookup){
        if(defaultDataSource == null){
            throw new IllegalArgumentException("Parameter dataSource must not be null");
        } else if(dataSourceLookup == null){
            throw new IllegalArgumentException("Parameter dataSource must not be null");
        }
        else {
            this.defaultDataSource = defaultDataSource;
            this.dataSourceLookup = dataSourceLookup;
        }
    }

    /**
     * Select datasources in situations where not tenantId is used (e.g. startup processing).
     */
    @Override
    protected DataSource selectAnyDataSource() {
        logger.trace("Select any dataSource: " + defaultDataSource);
        return defaultDataSource;
    }

    /**
     * Obtains a DataSource based on tenantId
     */
    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        DataSource ds = dataSourceLookup.getDataSource(tenantIdentifier);
        logger.trace("Select dataSource from "+ tenantIdentifier+ ": " + ds);
        return ds;
    }
}
