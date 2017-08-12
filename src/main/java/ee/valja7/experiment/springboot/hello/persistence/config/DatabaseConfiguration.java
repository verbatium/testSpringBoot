package ee.valja7.experiment.springboot.hello.persistence.config;

import liquibase.integration.spring.SpringLiquibase;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {
    private static final String LIQUIBASE_CHANGELOG_FILE = "classpath:liquibase/master.xml";
    private static final String LIQUIBASE_CONTEXT = "dev";

    @Bean
    @Primary
    public DataSource ucpDataSource() throws SQLException {

        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
        UcpDataSourceProperties ucpProps = ucpDataSourceProperties();
        poolDataSource.setURL(ucpProps.getUrl());
        poolDataSource.setUser(ucpProps.getUser());
        poolDataSource.setPassword(ucpProps.getPassword());
        poolDataSource.setConnectionFactoryClassName(ucpProps.getConnectionFactoryClassName());
        poolDataSource.setConnectionWaitTimeout(ucpProps.getConnectionWaitTimeout());
        poolDataSource.setInactiveConnectionTimeout(ucpProps.getInactiveConnectionTimeout());
        poolDataSource.setInitialPoolSize(ucpProps.getInitialPoolSize());
        poolDataSource.setMinPoolSize(ucpProps.getMinPoolSize());
        poolDataSource.setMaxPoolSize(ucpProps.getMaxPoolSize());
        poolDataSource.setConnectionProperty("v$session.program", "DBCON");
        poolDataSource.setValidateConnectionOnBorrow(ucpProps.getValidateConnectionOnBorrow());
        return poolDataSource;
    }

    @Bean
    @Primary
    public UcpDataSourceProperties ucpDataSourceProperties() {
        return new UcpDataSourceProperties();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) throws SQLException {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(LIQUIBASE_CHANGELOG_FILE);
        liquibase.setContexts(LIQUIBASE_CONTEXT);

        return liquibase;
    }
}
