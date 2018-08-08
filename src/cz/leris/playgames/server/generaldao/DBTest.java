package cz.leris.playgames.server.generaldao;

//import cz.leris.playgames.server.DB;
//import cz.leris.playgames.server.utils.Config;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DBTest {

    private String[] setupSQLs;

    protected DBTest(String... setupSQLs) {
        this.setupSQLs = setupSQLs;
    }

    @BeforeClass
    public static void init() {
//        Config.load();
        String dbName = "testdb";
        String dbServer = "localhost";
        DB.setUp();
//        Config.loadServerConfigFromDb();
    }

    @AfterClass
    public static void tearDown() {
        DB.closePool();
    }

    @Before
    public void setup() throws SQLException {
        System.out.println("Clear DB");
        clean(setupSQLs);
        System.out.println("Cleared DB");
    }

    public void clean(/*language=SQL*/String... strs) throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String str : strs) {
                    System.out.println("Clearing: " + str);
                    statement.executeUpdate(str);
                }
            }
        }
    }

    public void prepareSql(/*language=SQL*/String... sqls) throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String sql : sqls) {
                    System.out.println("Calling: " + sql);
                    statement.executeUpdate(sql);
                }
            }
        }
    }

    public void assertSql(/*language=SQL*/String... sqls) throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String sql : sqls) {
                    try (ResultSet executeQuery = statement.executeQuery(sql)) {
                        System.out.println("Asserting " + sql);
                        assertTrue(executeQuery.next());
                    }
                }
            }
        }
    }

    public void assertSqlEmpty(/*language=SQL*/String... sqls) throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String sql : sqls) {
                    try (ResultSet executeQuery = statement.executeQuery(sql)) {
                        System.out.println("Asserting " + sql);
                        assertFalse(executeQuery.next());
                    }
                }
            }
        }
    }

    public void assertSqlCount(int expected, /*language=SQL*/String... sqls) throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String sql : sqls) {
                    try (ResultSet executeQuery = statement.executeQuery(sql)) {
                        System.out.println("Asserting count " + sql);
                        if (executeQuery.next()) {
                            int count = executeQuery.getInt(1);
                            assertEquals(expected, count);
                        } else {
                            fail();
                        }
                    }
                }
            }
        }
    }
}
