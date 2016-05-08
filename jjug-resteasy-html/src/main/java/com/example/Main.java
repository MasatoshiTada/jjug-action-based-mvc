package com.example;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

/**
 * emagさんのブログを参考に作りました。
 * @see http://morec.at/blog/2015/12/21/wildfly-swarm-tour
 * @see https://github.com/emag/wildfly-swarm-tour/blob/master/complete/lifelog-postgresql/src/main/java/wildflyswarmtour/lifelog/App.java
 * @see https://github.com/emag/wildfly-swarm-tour/blob/master/complete/lifelog-postgresql/src/main/java/wildflyswarmtour/lifelog/LifeLogContainer.java
 * @see https://github.com/emag/wildfly-swarm-tour/blob/master/complete/lifelog-postgresql/src/main/java/wildflyswarmtour/lifelog/LifeLogDeployment.java
 */
public class Main {

    public static void main(String[] args) throws Exception {
        newContainer().start().deploy(deployment());
    }

    private static Container newContainer() throws Exception {
        Container container = new Container();
        container.fraction(new DatasourcesFraction()
                .jdbcDriver("h2", (d) -> {
                    d.driverClassName("org.h2.Driver");
                    d.xaDatasourceClass("org.h2.jdbcx.JdbcDataSource");
                    d.driverModuleName("com.h2database.h2");
                })
                .dataSource("jjugDS", (ds) -> {
                    ds.driverName("h2");
                    ds.connectionUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
                    ds.userName("sa");
                    ds.password("sa");
                })
        );
        container.fraction(new JPAFraction()
                .inhibitDefaultDatasource()
                .defaultDatasource("jboss/datasources/jjugDS")
        );
        return container;
    }

    private static JAXRSArchive deployment() {
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackages(true, Main.class.getPackage());
        deployment.addAsWebInfResource(
                new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()),
                "classes/META-INF/persistence.xml");
        return deployment;
    }
}
