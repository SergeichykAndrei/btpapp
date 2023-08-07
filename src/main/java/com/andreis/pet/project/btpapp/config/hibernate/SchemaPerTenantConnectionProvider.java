package com.andreis.pet.project.btpapp.config.hibernate;

import com.andreis.pet.project.btpapp.util.TenantUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchemaPerTenantConnectionProvider implements MultiTenantConnectionProvider {

    @Value("${multitenant.defaultTenant}")
    private String defaultTenant;

    private final DataSource dataSource;

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            if (Objects.equals(tenantIdentifier, defaultTenant)) {
                log.info("SchemaPerTenantConnectionProvider: getConnection!!!!: " + defaultTenant);
                connection.setSchema(defaultTenant);
            } else {
                log.info("SchemaPerTenantConnectionProvider: getConnection!!!!: " + tenantIdentifier);
                connection.setSchema(TenantUtil.createSchemaName(tenantIdentifier));
            }
        } catch (SQLException e) {
            throw new HibernateException("SchemaPerTenantConnectionProvider: Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            log.info("SchemaPerTenantConnectionProvider: releaseConnection: " + defaultTenant);
            connection.setSchema(defaultTenant);
        } catch (SQLException e) {
            throw new HibernateException("SchemaPerTenantConnectionProvider: Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
