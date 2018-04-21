package Controller.dao.Impl;

import Controller.dao.Dao;
import Controller.dao.domain.Table;
import util.DBCPUtil;
import warper.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class daoImpl implements Dao {
    @Override
    public void addTable(Table tab) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;//此对象对数据库中的数据进行操作
        DataSource ds = new MyDataSource ();//从池中取出一个连接MyConnection

        try {
            conn = ds.getConnection();
            //conn.setTransactionIsolation (Connection.TRANSACTION_REPEATABLE_READ);//mysql默认级别
            ps = conn.prepareStatement("INSERT INTO information(Mon,Tue,Wed,Thu,Fri,Sat,Sun) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, tab.getMon ());
            ps.setString(2, tab.getTue ());
            ps.setString(3, tab.getWed ());
            ps.setString(4, tab.getThu ());
            ps.setString(5, tab.getFri ());
            ps.setString(6, tab.getSat ());
            ps.setString(7, tab.getSun ());
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("添加失败！");
        } finally {
            DBCPUtil.release (conn,ps,null);
        }
    }

    public void addTable() throws Exception {
    }

}
