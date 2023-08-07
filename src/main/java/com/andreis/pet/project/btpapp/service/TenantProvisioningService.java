package com.andreis.pet.project.btpapp.service;

import com.andreis.pet.project.btpapp.util.TenantUtil;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantProvisioningService {
    public static final String LIQUIBASE_PATH = "db/changelog/db.changelog-master.yaml";
    private static final Pattern TENANT_PATTERN = Pattern.compile("[-\\w]+");

    private final DataSource dataSource;


    public void subscribeTenant(final String tenantId) throws SQLException, LiquibaseException {
        String defaultSchemaName;
        try {
            Validate.isTrue(isValidTenantId(tenantId), String.format("Invalid tenant id: \"%s\"", tenantId));
            String schemaName = TenantUtil.createSchemaName(tenantId);

            Connection connection = dataSource.getConnection();
//            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("CREATE SCHEMA IF NOT EXISTS \"%s\"", schemaName));
                log.info("SCHEMA with name: {} was created", schemaName);
                connection.close();


//                defaultSchemaName = database.getDefaultSchemaName();
//                database.setDefaultSchemaName(schemaName);
//
//                log.info("SCHEMA NAME!!!!! " + schemaName);
//                statement.execute(String.format("SET search_path TO %s", schemaName));
//
//
//                connection = dataSource.getConnection();
//                connection.setSchema(schemaName);
//                Database database2 = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
//
//                Liquibase liquibase = new Liquibase(LIQUIBASE_PATH, new ClassLoaderResourceAccessor(), database2);
//
//                liquibase.update(new Contexts(), new LabelExpression());
//                database.setDefaultSchemaName(defaultSchemaName);
            }

            runLiquibaseScript(dataSource.getConnection(), schemaName);

        } catch (Exception e) {
            log.error("Tenant subscription failed for {}.", tenantId, e);
            throw e;
        }
    }

    private void runLiquibaseScript(Connection connection, String schemaName) {
        try {
            connection.setSchema(schemaName);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase(LIQUIBASE_PATH, new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
            log.info("Initial script for schema: {} was performed successfully", schemaName);

            connection.commit();
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void unsubscribeTenant(final String tenantId) throws SQLException {
        try {
            Validate.isTrue(isValidTenantId(tenantId), String.format("Invalid tenant id: \"%s\"", tenantId));
            String schemaName = TenantUtil.createSchemaName(tenantId);
            Connection connection = dataSource.getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.execute(String.format("DROP SCHEMA IF EXISTS \"%s\" CASCADE", schemaName));
            }
            log.info("SCHEMA with name: {} was deleted successfully", schemaName);
        } catch (Exception e) {
            log.error("Tenant unsubscription failed for {}.", tenantId, e);
            throw e;
        }
    }

    private boolean isValidTenantId(String tenantId) {
        return Objects.nonNull(tenantId) && TENANT_PATTERN.matcher(tenantId).matches();
    }
}
