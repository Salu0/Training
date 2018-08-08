package cz.leris.playgames.server.generaldao.my.miserable.attempts;

import cz.leris.playgames.server.generaldao.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBwriter {

    public void executeSql_CreateOrInsert(String... sqls) throws SQLException {
        try (Connection conn = DB.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                for (String sql : sqls) {
                    System.out.println("Executing: " + sql);
                    stmt.executeUpdate(sql);
                }
            }
        }
    }

    public void executeSql_Select(String... sqls) throws SQLException {
        try (Connection conn = DB.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                for (String sql : sqls) {
                    System.out.println("Executing: " + sql);
                    stmt.executeQuery(sql);
                }
            }
        }
    }
}