package com.ly;

import org.apache.derby.impl.jdbc.EmbedConnection;

import java.sql.*;

public class Demo
{
    private static String dbURL = "jdbc:derby:E:\\dream\\demo;create=true";
    private static EmbedConnection conn = null;

    public static void main(String[] args) throws SQLException {
        conn = (EmbedConnection) DriverManager.getConnection(dbURL);
        Statement st = conn.createStatement();
        selectTable(st);
     //   DriverManager.getConnection("jdbc:derby:E:\\dream\\demo;shutdown=true");
    }

    private static void selectTable(Statement st) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from cars");
        System.out.println(conn.getClass().getName());
        ResultSet  rs = null;
        try{
            rs = ps.executeQuery();
            while(rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                System.out.println("ID："+id +", "+ "姓名："+name +", "+ "价格："+price);
            }
        }finally {
            ps.close();
            rs.close();
            conn.close();
        }
    }

    private static void createInsertTable(Statement st) throws SQLException {
        st.executeUpdate("CREATE TABLE CARS(ID INT PRIMARY KEY,"
                + "NAME VARCHAR(30), PRICE INT)");
        st.executeUpdate("INSERT INTO CARS VALUES(1, 'Audi', 52642)");
        st.executeUpdate("INSERT INTO CARS VALUES(2, 'Mercedes', 57127)");
        st.executeUpdate("INSERT INTO CARS VALUES(3, 'Skoda', 9000)");
        st.executeUpdate("INSERT INTO CARS VALUES(4, 'Volvo', 29000)");
        st.executeUpdate("INSERT INTO CARS VALUES(5, 'Bentley', 350000)");
        st.executeUpdate("INSERT INTO CARS VALUES(6, 'Citroen', 21000)");
        st.executeUpdate("INSERT INTO CARS VALUES(7, 'Hummer', 41400)");
        st.executeUpdate("INSERT INTO CARS VALUES(8, 'Volkswagen', 21600)");
    }

}