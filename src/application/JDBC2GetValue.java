package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC2GetValue{
  public static void main(String[] args){

    //Cria uma conexão com o banco de dados(configurado em outro arquivo)
    Connection conn = null;

    //Monta o comando sql
    Statement st = null;

    //Monta um objeto contendo o resultado da consulta montada no statement
    ResultSet rs = null;

    try{
      conn = DB.getConnection();

      st = conn.createStatement();
      rs = st.executeQuery("SELECT id, name FROM DEPARTMENT");
      while(rs.next()){
        System.out.println(rs.getInt("id")+"-"+rs.getString("name"));
      }

      rs = st.executeQuery("SELECT id, name, email, birthDate, baseSalary, departmentId FROM SELLER");
      while(rs.next()){
        System.out.println(
            rs.getInt("id")+", "+
                rs.getString("name")+", "+
                rs.getDate("birthDate")+", "+
                rs.getDouble("baseSalary")+", "+
                rs.getInt("departmentId"));
      }
    }catch(SQLException e){
      throw new DbException(e.getMessage());
    }finally{
      DB.closeResultSet(rs);
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}
