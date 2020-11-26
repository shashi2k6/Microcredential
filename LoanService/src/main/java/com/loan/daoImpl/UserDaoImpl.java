/*
 * package com.loan.daoImpl;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import
 * java.sql.SQLIntegrityConstraintViolationException; import java.sql.Statement;
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.loan.bean.UserBean; import com.loan.bean.UserLoanDtls; import
 * com.loan.dao.UserDao;
 * 
 * @Service public class UserDaoImpl implements UserDao {
 * 
 * @Autowired public DataSource datasource;
 * 
 * public String createUser(UserBean user) { String message =
 * "User is created Successfully."; Connection conn = null; try { conn =
 * datasource.getConnection(); PreparedStatement pStmt = conn
 * .prepareStatement("insert into users (username,password,enabled) values (?,?,?)"
 * );
 * 
 * pStmt.setString(1, user.getUsername()); pStmt.setString(2,
 * user.getPassword()); pStmt.setString(3, user.getEnabled()); pStmt.execute();
 * 
 * PreparedStatement pStmt1 = conn
 * .prepareStatement("insert into authorities (username,authority) values (?,?)"
 * ); pStmt1.setString(1,user.getUsername()); pStmt1.setString(2,"ROLE_USER");
 * pStmt1.execute();
 * 
 * 
 * } catch (SQLIntegrityConstraintViolationException e) { message =
 * "User already exists."; } catch(SQLException e) { message =
 * "Error in creating the username"; } finally { if (conn != null) { try {
 * conn.close(); } catch (SQLException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } } } return message; }
 * 
 * public List<UserBean> getAllUsers() { String query =
 * "select username,enabled from users"; Connection conn = null; List<UserBean>
 * userList = new ArrayList<UserBean>(); try { conn =
 * datasource.getConnection(); Statement stmt = conn.createStatement();
 * ResultSet rs = stmt.executeQuery(query);
 * 
 * while (rs.next()) { UserBean user = new UserBean();
 * user.setUsername(rs.getString(1)); user.setEnabled(rs.getString(2));
 * userList.add(user); } } catch (SQLException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); } finally { if (conn != null) { try {
 * conn.close(); } catch (SQLException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } } } return userList; }
 * 
 * @Override public boolean applyLoan(UserLoanDtls userDetails) { // TODO
 * Auto-generated method stub return false; }
 * 
 * }
 */