package com.hazardousdev.gym_dairy.guice;

import com.github.rkmk.container.FoldingListContainerFactory;
import com.github.rkmk.mapper.CustomMapperFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.hazardousdev.gym_dairy.dao.IGymDairyDao;
import com.hazardousdev.gym_dairy.service.GymDairyServiceImpl;
import com.hazardousdev.gym_dairy.service.IGymDairyService;
import com.hazardousdev.gym_dairy.wicket.application.GymDairyApplication;
import org.h2.jdbcx.JdbcConnectionPool;
import org.postgresql.ds.PGPoolingDataSource;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author alcovp
 */
public class GymDairyModule extends AbstractModule {
    @Override
    protected void configure() {
        Names.bindProperties(binder(), loadProperties());

        bind(String.class).annotatedWith(Names.named("JDBC URL h2")).toInstance("jdbc:h2:mem:test");
        bind(String.class).annotatedWith(Names.named("JDBC username h2")).toInstance("username");
        bind(String.class).annotatedWith(Names.named("JDBC password h2")).toInstance("password");

        bind(DataSource.class).toProvider(H2DataSourceProvider.class).in(Scopes.SINGLETON);
//        bind(DataSource.class).toProvider(ExternalSQLDataSourceProvider.class).in(Scopes.SINGLETON);

        bind(DBI.class).toProvider(DBIProvider.class).in(Scopes.SINGLETON);
        bind(IGymDairyDao.class).toProvider(GymDiaryDaoProvider.class).in(Scopes.SINGLETON);
        bind(IGymDairyService.class).to(GymDairyServiceImpl.class).in(Scopes.SINGLETON);
    }

    static class ExternalSQLDataSourceProvider implements Provider<DataSource> {

        private final String url;
        private final int port;
        private final String name;
        private final String username;
        private final String password;

        @Inject
        public ExternalSQLDataSourceProvider(@Named("dburl") final String url,
                                             @Named("dbport") final String port,
                                             @Named("dbname") final String name,
                                             @Named("dbuser") final String username,
                                             @Named("dbpassword") final String password) {
            this.url = url;
            this.port = Integer.valueOf(port);
            this.name = name;
            this.username = username;
            this.password = password;
        }

        @Override
        public DataSource get() {
            PGPoolingDataSource source = new PGPoolingDataSource();
            source.setServerName(url);
            source.setPortNumber(port);
            source.setDatabaseName(name);
            source.setUser(username);
            source.setPassword(password);

            return source;
        }
    }

    static class H2DataSourceProvider implements Provider<DataSource> {

        private final String url;
        private final String username;
        private final String password;

        @Inject
        public H2DataSourceProvider(@Named("JDBC URL h2") final String url,
                                    @Named("JDBC username h2") final String username,
                                    @Named("JDBC password h2") final String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        @Override
        public DataSource get() {
            return JdbcConnectionPool.create(url, username, password);
        }
    }

    static class DBIProvider implements Provider<DBI> {

        private final DataSource dataSource;

        @Inject
        public DBIProvider(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public DBI get() {
            final DBI dbi = new DBI(dataSource);
            dbi.registerMapper(new CustomMapperFactory());
            dbi.registerContainerFactory(new FoldingListContainerFactory());
            return dbi;
        }
    }

    static class GymDiaryDaoProvider implements Provider<IGymDairyDao> {

        private final DBI dbi;

        @Inject
        public GymDiaryDaoProvider(DBI dbi) {
            this.dbi = dbi;
        }

        @Override
        public IGymDairyDao get() {
            return dbi.onDemand(IGymDairyDao.class);
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        InputStream input = null;

        try {
            final String filename = "config.properties";
            input = GymDairyApplication.class.getClassLoader().getResourceAsStream(filename);

            if (input == null) {
                System.out.println("Unable to find " + filename);
            } else {
                properties.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }
}
