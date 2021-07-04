package application;

import db.DB;

public class JDBC1TestConnection{
  public static void main(String[] args){
    var conn = DB.getConnection();

    DB.closeConnection();
  }
}
