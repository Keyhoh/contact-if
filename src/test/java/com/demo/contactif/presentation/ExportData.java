package com.demo.contactif.presentation;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class ExportData {
    public static void main(String[] args) throws Exception {
        Class driverClass = Class.forName("org.hibernate.dialect.MySQL8Dialect");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test_db?serverTimezone=UTC",
                "test_user",
                "test_user"
        );
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));
    }
}
