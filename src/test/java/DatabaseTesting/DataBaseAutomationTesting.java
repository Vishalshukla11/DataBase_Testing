package DatabaseTesting;

import java.sql.*;
import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataBaseAutomationTesting {
    private static String url = "jdbc:mysql://127.0.0.1:3306/companydb";
    private static String username = "root";
    private static String password = "Pankaj";
    private static Connection conn;

    @BeforeClass
    public static void setup() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Test
    public void testInsertEmployee() throws SQLException {
        String query = "INSERT INTO Employee (EmployeeName, DepartmentID, ProjectID) VALUES (?, ?, ?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, "Lakhan");
            psmt.setInt(2, 2);
            psmt.setInt(3, 3);

            int rowaffected = psmt.executeUpdate();
            Assert.assertEquals(1, rowaffected);

        }

    }

    @Test
    public void testReadEmployeeData() throws SQLException {
        String query = "select * from employee";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                int employeeid = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                int Departmentid = rs.getInt("DepartmentID");
                int projectID = rs.getInt("ProjectId");

                System.out.println(
                        "EmployeeID: " + employeeid + " ,EmployeeName: " + employeeName + " ,DepartmentID: "
                                + Departmentid + " ,projectID: " + projectID);

                Assert.assertNotNull(employeeName);

            }

        }

    }

    @Test
    public void testupdateEmployee() throws SQLException {
        String query = "Update employee set employeeName=? ,Departmentid =? ,projectid=? where employeeid=?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, "vivek");
            psmt.setInt(2, 3);
            psmt.setInt(3, 2);
            psmt.setInt(4, 8);

            int rowaffected = psmt.executeUpdate();
            Assert.assertEquals(1, rowaffected);

        }
    }

    // @Deprecated
     @Test
    public void TestDeleteEmployee() throws SQLException {
        String query = "Delete from employee where employeeID=?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, 5);
            int rowaffected = psmt.executeUpdate();
            Assert.assertEquals(1, rowaffected);
        }
    }

    // projects

    @Test
    public void testInsertProject() throws SQLException {
        String query = "insert into project(projectName)values(?)";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, "project beta");
            int rowaffected = psmt.executeUpdate();
            Assert.assertEquals(1, rowaffected);

        }
    }

    @Test
    public void testReadProject() throws SQLException {
        String query = "Select * from project where projectid=?";
        try (PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, 1);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                Assert.assertEquals(1, rs.getInt("projectId"));
            } else {
                Assert.fail("Project not found");
            }

        }
    }
//Department 
    @Test
    public void testInsertDepartment()throws SQLException
    {String query="Insert into Department(DepartmentName)values(?)";
    try(PreparedStatement psmt=conn.prepareStatement(query))
    {
        psmt.setString(1, "HR");
        int rowaffected=psmt.executeUpdate();
        Assert.assertEquals(1, rowaffected);
    }

    }

    @Test
    public void testReadDepartment() throws SQLException
    {
        String query="Select * from department where DepartmentID=?";
        try(PreparedStatement psmt=conn.prepareStatement(query))
        {
            psmt.setInt(1  , 1);
            ResultSet rs=psmt.executeQuery();
            if (rs.next()) {
            Assert.assertEquals("1", rs.getString("DepartmentID"));
                
            }
            else{
                Assert.fail("Department not found");
            }

        }
    }
}
