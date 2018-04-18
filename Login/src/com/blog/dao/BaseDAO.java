package com.blog.dao;



import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ParameterMetaData;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.commons.beanutils.BeanUtils;  
  
/** 
 *  JDBC通用的DAO基类 
 *  1. 得到连接 
 *  2. 释放资源 
 *  3. 通用的增删改查的方法 
 *  @author hejin 
 */  
public class BaseDAO {

    //MySQL连接参数  
    private static final String URL = "jdbc:mysql://localhost:3306/user?useSSL=true&characterEncoding=utf8";  
    private static final String USER = "root";  
    private static final String PASSWORD = "root";  
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";  
      
    //注册驱动  
    static {  
        try {
        		System.out.println("静态块!");
            Class.forName(DRIVER_CLASS);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 得到连接对象 
     * @return 
     */  
    public Connection getConnection() {  
        Connection conn = null;  
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
        }  
        return conn;  
    }  
      
    /** 
     * 关闭连接对象 
     */  
    public void closeAll(Connection conn, Statement stmt, ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
                rs = null;
                System.out.println("关闭结果集！");
            }  
            if (stmt != null) {  
                stmt.close();  
                stmt = null;
                System.out.println("关闭语句！");
            }  
            if (conn != null) {  
                conn.close();  
                conn = null;
                System.out.println("关闭数据库连接！");
            }  
        } catch (SQLException se) {  
            se.printStackTrace();  
        }  
    }  
  
    /** 
     * 关闭连接对象 
     */  
    public void closeAll(Connection conn, Statement stmt) {  
        this.closeAll(conn, stmt, null);  
    }  
      
    /** 
     * 通用的增删改的方法 
     * @return 返回影响的行数 
     */  
    public int params(String sql, Object[] params) {  
        //得到连接对象  
        Connection conn = null;  
        PreparedStatement pstmt = null;  
        //影响的行数  
        int rows = 0;  
        try {  
            conn = getConnection();  
            pstmt = conn.prepareStatement(sql);  
             //得到参数个数  
            ParameterMetaData pmd =  pstmt.getParameterMetaData();  
            //设置参数  
            for (int i = 0; i < pmd.getParameterCount(); i++) {  
                pstmt.setObject(i+1, params[i]);  
            }  
            rows  = pstmt.executeUpdate();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            closeAll(conn, pstmt);  
        }  
        return rows;  
    }  
      
    /** 
     * 通用的查询方法，封装成List<T> 
     * 使用了泛型方法 
     * 使用这个方法的前提：数据库表的字段名称和JavaBean的属性名称保持一致 
     * 在这个方法调用了 
     * BeanUtils.setProperty(obj, columnName, value) 
     * 来实现，不然就要使用反射来完成类似的操作了 
     */  
    public <T> List<T> find(String sql, Object[] params, Class<T> clazz) {  
        //得到连接对象  
        Connection conn = null;  
        PreparedStatement stmt = null;  
        ResultSet rs = null;  
        //创建集合  
        List<T> list = new ArrayList<T>();  
        try {  
            conn = getConnection();
            if(conn==null){
            	System.out.println("数据库连接不成功");
            }
            stmt = conn.prepareStatement(sql);  
            //得到参数的个数  
            ParameterMetaData pmd = stmt.getParameterMetaData();  
            for (int i = 0; i < pmd.getParameterCount(); i++) {  
                //给参数赋值  
                stmt.setObject(i+1, params[i]);  
            }  
            //运行得到结果集  
            rs = stmt.executeQuery();  
            ResultSetMetaData rsmd = rs.getMetaData();  
            //遍历结果集  
            while (rs.next()) {  
                T obj = clazz.newInstance(); //实例化对象  
                //得到结果集的列数  
                for (int i = 0; i < rsmd.getColumnCount(); i++) {  
                    //得到列名  
                    String colName = rsmd.getColumnName(i+1);  
                    //列号从1开始，得到每1列的值  
                    Object value = rs.getObject(colName);  
                    //把名字和值赋值到对象中  
                    BeanUtils.setProperty(obj, colName, value);  
                }  
                //添加到列表中  
                list.add(obj);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            closeAll(conn, stmt, rs);  
        }  
        return list;  
    }

	public int executeUpdate(String sql, Object[] params) {
		int rows=0;
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = getConnection();
			stat = con.prepareStatement(sql);
			ParameterMetaData parameterMetaData = stat.getParameterMetaData();
			for(int i = 0;i < parameterMetaData.getParameterCount();i++) {
				stat.setObject(i+1, params[i]);
			}
			rows= stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(con, stat, null);
		}
		return rows;
	}  

}