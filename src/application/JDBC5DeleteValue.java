package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC5DeleteValue{
  public static void main(String[] args){
    Connection connection;
    PreparedStatement ps = null;
    PreparedStatement ps2 = null;

    try{
      connection = DB.getConnection();
      ps = connection.prepareStatement("DELETE FROM department WHERE Id = ?");
      ps.setInt(1,6);
      var rowsAffected = ps.executeUpdate();
      System.out.println("Done! Rows affected: "+rowsAffected);

      ps2 = connection.prepareStatement("delete from seller where id = ?");
      ps2.setInt(1,11);
      rowsAffected = ps2.executeUpdate();
      System.out.println("Done! Rows affected: "+rowsAffected);
    }catch(SQLException e){
      throw new DbIntegrityException(e.getMessage());
    }finally{
      DB.closeStatement(ps);
      DB.closeStatement(ps2);
      DB.closeConnection();
    }
  }
}
