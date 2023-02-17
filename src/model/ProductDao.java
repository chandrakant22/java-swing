package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/imsdb";
	static String db_user = "root";
	static String db_pass = "abc123";

	static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, db_user, db_pass);
		return con;
	}

	public int insert(Product p) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("insert into product_tbl(pName, pQuantity, pRate) values(?,?,?)");
		ps.setString(1, p.getpName());
		ps.setString(2, p.getpQuantity());
		ps.setString(3, p.getpRate());

		int a = ps.executeUpdate();

		return a;
	}

	public int update(Product p) throws ClassNotFoundException, SQLException {

		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("update product_tbl set pName=?, pQuantity=?, pRate=? where pid=?");
		ps.setString(1, p.getpName());
		ps.setString(2, p.getpQuantity());
		ps.setString(3, p.getpRate());
		ps.setInt(4, p.getpId());

		int a = ps.executeUpdate();

		return a;
	}
	
	public Product getOneProduct(int pId) {
	  
		Product product=new Product();
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM product_tbl where pId="+pId);
			rs.absolute(1);
			 product.setpId((rs.getInt("pId")));
             product.setpName(rs.getString("pName"));
             product.setpQuantity(rs.getString("pQuantity"));
             product.setpRate(rs.getString("pRate"));
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return product;
	}
	

	public String[][] getProduct() {

		String[][] data = new String[4][4]; // [rows][columns]

		try {
			Connection con = getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM product_tbl LIMIT 0,4");
			// Looping to store result in returning array data //
			int i = 0;
			while (rs.next()) {
				for (int j = 0; j < 4; j++) {
					data[i][j] = rs.getString(j + 1);
				}
				i = i + 1;
			}
			// Below lines to check data before returning. //
			
			 for (int x=0;x<data.length;x++) { for (int y=0;(data[x]!= null &&
			 y <data[x].length);y++) { System.out.print(data[x][y] + " "); }
			 System.out.println(); }
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return data;
	}
	
	
	
	public ArrayList<Product> searchproduct(String key){
        ArrayList<Product> result = new ArrayList<Product>();
        String sql = "SELECT * FROM product_tbl WHERE pName LIKE ?";
        try{
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setpId((rs.getInt("pId")));
                product.setpName(rs.getString("pName"));
                product.setpQuantity(rs.getString("pQuantity"));
                product.setpRate(rs.getString("pRate"));
        
             
                result.add(product);
            }
        }catch(Exception e){
            e.printStackTrace();
        }   
        return result;
    }

	public void deleteStock(int pId) {
		 String sql = "DELETE FROM product_tbl WHERE pId=?";
	        try{
	            PreparedStatement ps = getConnection().prepareStatement(sql);
	            ps.setInt(1, pId);

	            ps.executeUpdate();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		
	}

}
