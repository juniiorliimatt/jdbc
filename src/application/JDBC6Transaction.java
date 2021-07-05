package application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC6Transaction{
  public static void main(String[] args){
    Connection connection = null;
    Statement st = null;
    var rows = 0;

    try{
      connection = DB.getConnection();
      connection.setAutoCommit(false);
      st = connection.createStatement();

      rows = st.executeUpdate("UPDATE seller SET BaseSalary = 100 WHERE DepartmentId = 1");
      System.out.println("rows 1: "+rows);

      if(rows<0){
        throw new DbException("Error fake");
      }

      rows = st.executeUpdate("UPDATE seller SET BaseSalary = 100 WHERE DepartmentId = 2");
      System.out.println("rows 2: "+rows);

      connection.commit();

    }catch(SQLException e){
      try{
        connection.rollback();
        throw new DbIntegrityException("Transaction rolled back! Caused by: "+e.getMessage());
      }catch(SQLException e1){
        throw new DbIntegrityException("Error trying to rollback! Caused by: "+e1.getMessage());
      }
    }finally{
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}
