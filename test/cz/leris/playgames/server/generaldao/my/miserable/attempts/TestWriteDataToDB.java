package cz.leris.playgames.server.generaldao.my.miserable.attempts;

import cz.leris.playgames.server.generaldao.DB;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestWriteDataToDB extends DBwriter {

    private String[] DBNameToInsert = {"TRUNCATE AAregistration", "TRUNCATE AAdatetest"};
    private String[] DBNameToDelete = {"DROP TABLE IF EXISTS AAtest;"};


    @BeforeClass
    public static void setup(){
        DB.setUp();
    }

    @Before
    public void clear() throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String sql : DBNameToInsert) {
                    System.out.println("Clearing: " + sql);
                    statement.executeUpdate(sql);
                }
            }
        }
    }

    @Before
    public void delete() throws SQLException {
        try (Connection con = DB.getConnection()) {
            try (Statement statement = con.createStatement()) {
                for (String sql : DBNameToDelete) {
                    System.out.println("Deleting: " + sql);
                    statement.executeUpdate(sql);
                }
            }
        }
    }

    @AfterClass
    public static void tearDrop() {DB.closePool();}

    @Test
    public void insertTest() throws SQLException {
        executeSql_CreateOrInsert("INSERT into AAregistration VALUES (1003, 'Boris', 'Johnson', 66)",
                                        "INSERT into AAregistration VALUES (1004, 'Angela', 'Merkel', 73)",
                                        "INSERT into AAdatetest VALUES ('1996-10-24 10:53:12');");
    }

    @Test
    public void createTest() throws SQLException {
        executeSql_CreateOrInsert("CREATE table AAdatetest (date DATETIME)");
    }
}
