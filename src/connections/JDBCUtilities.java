package connections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

public final class JDBCUtilities {
    public static void printSQLException(final SQLException ex) {
        for (final Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(((SQLException) e).getSQLState()) == false) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

    public static boolean ignoreSQLException(final String sqlState) {
        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }
        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32"))
            return true;
        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55"))
            return true;
        return false;
    }

    public static void loadFile(final ScriptRunner sr, final String fileName) {
        try {
            sr.runScript(new BufferedReader(new FileReader(fileName)));
        } catch (final FileNotFoundException e) {
            System.err.println("file not found !");
            e.printStackTrace();
        }
    }

    public static String dropIfExist(final String tableName) {
        return "BEGIN EXECUTE IMMEDIATE 'DROP TABLE " + tableName
                + " CASCADE CONSTRAINTS'; EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF; END;";
    }

    public static void selectAll(final Connection connection, final String tableName) throws SQLException {
        final String query = "select * from " + tableName;
        // automatically close Statement and ResultSet objects, regardless of whether an
        // SQLException has been thrown.
        try (Statement statement = connection.createStatement()) {
            statement.setQueryTimeout(30); // set timeout to 30 sec.
            try (ResultSet rs = statement.executeQuery(query)) {
                // dump and print result
                System.out.println(dumpResultSet(rs));
            }
        }
        connection.commit();
    }

    public static String dumpResultSet(final ResultSet rs) throws SQLException {
        String result = "\n";
        final ResultSetMetaData rsmd = rs.getMetaData();
        final int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    result += ",  \n";
                final String columnValue = rs.getString(i);
                result += columnValue + " " + rsmd.getColumnName(i);
            }
            result += "\n";
        }
        return result;
    }
}
