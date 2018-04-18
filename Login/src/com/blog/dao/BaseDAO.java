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
 *  JDBCͨ�õ�DAO���� 
 *  1. �õ����� 
 *  2. �ͷ���Դ 
 *  3. ͨ�õ���ɾ�Ĳ�ķ��� 
 *  @author hejin 
 */  
public class BaseDAO {

    //MySQL���Ӳ���  
    private static final String URL = "jdbc:mysql://localhost:3306/user?useSSL=true&characterEncoding=utf8";  
    private static final String USER = "root";  
    private static final String PASSWORD = "root";  
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";  
      
    //ע������  
    static {  
        try {
        		System.out.println("��̬��!");
            Class.forName(DRIVER_CLASS);  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * �õ����Ӷ��� 
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
     * �ر����Ӷ��� 
     */  
    public void closeAll(Connection conn, Statement stmt, ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
                rs = null;
                System.out.println("�رս������");
            }  
            if (stmt != null) {  
                stmt.close();  
                stmt = null;
                System.out.println("�ر���䣡");
            }  
            if (conn != null) {  
                conn.close();  
                conn = null;
                System.out.println("�ر����ݿ����ӣ�");
            }  
        } catch (SQLException se) {  
            se.printStackTrace();  
        }  
    }  
  
    /** 
     * �ر����Ӷ��� 
     */  
    public void closeAll(Connection conn, Statement stmt) {  
        this.closeAll(conn, stmt, null);  
    }  
      
    /** 
     * ͨ�õ���ɾ�ĵķ��� 
     * @return ����Ӱ������� 
     */  
    public int params(String sql, Object[] params) {  
        //�õ����Ӷ���  
        Connection conn = null;  
        PreparedStatement pstmt = null;  
        //Ӱ�������  
        int rows = 0;  
        try {  
            conn = getConnection();  
            pstmt = conn.prepareStatement(sql);  
             //�õ���������  
            ParameterMetaData pmd =  pstmt.getParameterMetaData();  
            //���ò���  
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
     * ͨ�õĲ�ѯ��������װ��List<T> 
     * ʹ���˷��ͷ��� 
     * ʹ�����������ǰ�᣺���ݿ����ֶ����ƺ�JavaBean���������Ʊ���һ�� 
     * ��������������� 
     * BeanUtils.setProperty(obj, columnName, value) 
     * ��ʵ�֣���Ȼ��Ҫʹ�÷�����������ƵĲ����� 
     */  
    public <T> List<T> find(String sql, Object[] params, Class<T> clazz) {  
        //�õ����Ӷ���  
        Connection conn = null;  
        PreparedStatement stmt = null;  
        ResultSet rs = null;  
        //��������  
        List<T> list = new ArrayList<T>();  
        try {  
            conn = getConnection();
            if(conn==null){
            	System.out.println("���ݿ����Ӳ��ɹ�");
            }
            stmt = conn.prepareStatement(sql);  
            //�õ������ĸ���  
            ParameterMetaData pmd = stmt.getParameterMetaData();  
            for (int i = 0; i < pmd.getParameterCount(); i++) {  
                //��������ֵ  
                stmt.setObject(i+1, params[i]);  
            }  
            //���еõ������  
            rs = stmt.executeQuery();  
            ResultSetMetaData rsmd = rs.getMetaData();  
            //���������  
            while (rs.next()) {  
                T obj = clazz.newInstance(); //ʵ��������  
                //�õ������������  
                for (int i = 0; i < rsmd.getColumnCount(); i++) {  
                    //�õ�����  
                    String colName = rsmd.getColumnName(i+1);  
                    //�кŴ�1��ʼ���õ�ÿ1�е�ֵ  
                    Object value = rs.getObject(colName);  
                    //�����ֺ�ֵ��ֵ��������  
                    BeanUtils.setProperty(obj, colName, value);  
                }  
                //��ӵ��б���  
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