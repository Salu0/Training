package cz.leris.playgames.server.generaldao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
//import cz.leris.playgames.server.utils.Config;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Rozhraní k databázi.
 */
public final class DB {
    private static final int IDLE_TIMEOUT_MS = 60 * 1000;
    private static HikariPool connectionPool;

    private DB() {}

    public static void setUp() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql://%s/%s?user=%s&password=%s&dontTrackOpenResources=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false", "localhost", "testdb", "kasa", "FGgCngH5290"));

        config.setUsername("kasa");
        config.setPassword("FGgCngH5290");

        config.setMaximumPoolSize(125);
        config.setMinimumIdle(25);
        config.setPoolName("HIKARI-CP");
        config.setIdleTimeout(IDLE_TIMEOUT_MS);

        connectionPool = new HikariPool(config);
    }


    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
	
	public static cz.leris.playgames.server.generaldao.DbConnection getDbConnection() throws SQLException {
		return new cz.leris.playgames.server.generaldao.DbConnection(getConnection());
	}

    public static void closePool() {
        try {
            connectionPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
