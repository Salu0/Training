package cz.leris.playgames.server.generaldao.my.miserable.attempts;

import com.google.gson.internal.bind.SqlDateTypeAdapter;
import cz.leris.playgames.server.generaldao.DB;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestReadDataFromDB extends TestWriteDataToDB {

    @Test
    public void selectFromTable() throws SQLException {

        insertTest();
        executeSql_Select("SELECT * FROM AAregistration;",
                                "SELECT id FROM AAregistration;");
    }

    @Test
    public void writeData() throws SQLException {
        insertTest();
        String[] sqls = {"SELECT * FROM AAregistration;", "SELECT * FROM AAregistration;"};
        try (Connection conn = DB.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                for (String sql : sqls) {
                    try (ResultSet rs = stmt.executeQuery(sql)) {
                        while (rs.next())
                            System.out.println("Selecting all columns from AAregistration: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)  + " " + rs.getInt(4));
                    }
                }
            }
        }
    }
}
