/***
 * Adar Dorham 203537824 89-281-03
 * Omer Forma 304823230 89-281-03
 * Roi Peretz 203258041 89-281-04
 * Tomer Rahamim 203717475 89-281-05
 ***/


import javax.management.Query;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DBClient class - responsible for creating and maintaining the connection to the
 * database specify in the configuration file. Send the queries to the server
 * and receive the data.
 */
public class DBClient {

    //The driver
    public static final String dbClass = "com.mysql.jdbc.Driver";
    private Connection connect = null;
    private Statement statement = null;

    /**
     * DBClient c'tor - create new DBClient , extract the database information from
     * the conf.txt file and establish the connection with the server.
     *
     * @throws Exception
     */
    public DBClient() throws Exception {

        //Extract configuration from conf.txt file.
        InputStream in = getClass().getResourceAsStream("/conf.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        //Take the usrl for the server.
        String url = br.readLine();
        //Take the user name and password for the database.
        String userName = br.readLine();
        String password = br.readLine();

        //Register JDBC driver
        Class.forName(dbClass);

        // Open connection
        connect = DriverManager.getConnection(url, userName, password);
        statement = connect.createStatement();
    }

    /**
     * Parse the dml query and send it to the server.
     *
     * @param query - the client query
     * @return the result return from the database
     * @throws Exception
     */
    public String sendDMLQuery(String query) throws Exception {
        String result = "";
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData rsmd = resultSet.getMetaData();

        int columnsNumber = rsmd.getColumnCount();

        //Parse the result
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1)
                result += ",  ";
            result += rsmd.getColumnName(i);
        }
        result += "\n";
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    result += ",  ";
                String columnValue = resultSet.getString(i);
                result += columnValue;
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Send the ddl query to the server.
     *
     * @param query - the client query
     * @throws Exception
     */
    public String sendDDLQuery(String query) throws Exception {
        if (query.contains("SHOW") || query.contains("DESCRIBE")) {
            return sendDMLQuery(query);
        } else {
            statement.executeUpdate(query);
            return "Success";
        }
    }


    /**
     * Open a giving file with script and read his content. Separate the script text
     * to commands and return the commands as a list.
     *
     * @param file - the script.
     * @return the list of commands.
     */
    public List<String> readScriptFromFile(File file) {
        String content = new String();
        StringBuffer sb = new StringBuffer();
        List<String> commands = new ArrayList<String>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //read all lines
            while ((content = br.readLine()) != null) {
                if (!content.contains("#")) {
                    sb.append(content);
                }
            }
            br.close();
            //Separate each command with ';'
            String[] inst = sb.toString().split(";");

            //Ignore spaces and new line
            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    commands.add(inst[i]);
                }
            }
        } catch (Exception e) {
        }
        return commands;
    }

    public List<String> getColumnsNames(String tableName) throws Exception {
        String query = "SHOW columns FROM " + tableName;
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<String> fieldsList=new ArrayList<String>();

        while (resultSet.next()) {
            String fieldName = resultSet.getString(1);
            System.out.println(fieldName);
            fieldsList.add(fieldName);
        }

        return fieldsList;

    }

}