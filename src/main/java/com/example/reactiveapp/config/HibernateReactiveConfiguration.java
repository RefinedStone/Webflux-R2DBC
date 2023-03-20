package com.example.reactiveapp.config;

import com.example.reactiveapp.person.Person;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.reactive.mutiny.Mutiny;
import org.hibernate.reactive.provider.Settings;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;
import static org.springframework.boot.jdbc.DataSourceBuilder.DataSourceProperty.PASSWORD;

@Configuration
public class HibernateReactiveConfiguration {

    @Bean
    public ReactiveTransactionManager reactiveTransactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public Mutiny.SessionFactory sessionFactory(ConnectionFactory connectionFactory) {
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting(Settings.URL, "jdbc:mysql://localhost:3306/study")
                .applySetting(Settings.USER, "root")
                .applySetting(Settings.PASS, "1234")
                .applySetting(Settings.DRIVER, "com.mysql.cj.jdbc.Driver")
                .applySetting(Settings.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                .applySetting(Settings.HBM2DDL_AUTO, "create")
                .applySetting(Settings.SHOW_SQL, "true")
                .applySetting(Settings.FORMAT_SQL, "true")
                .applySetting(Settings.USE_SQL_COMMENTS, "true")
                .applySetting(Settings.REACTIVE_CONNECTION_FACTORY, connectionFactory)
                .build();

        return new MetadataSources(registry)
                .addAnnotatedClass(Person.class)
                .buildMetadata()
                .getSessionFactoryBuilder()
                .unwrap(Mutiny.SessionFactoryBuilder.class)
                .build();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(PROTOCOL, "mysql")
                .option(HOST, "localhost")
                .option(PORT, 3306)
                .option(DATABASE, "study")
                .option(USER, "root")
                .option(PASSWORD, "1234")
                .build());
    }

}
