package com.studease.jdemo.jsch;

import com.jcraft.jsch.Session;
import java.sql.*;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class JSchDemo {

    public static void connectAndExecuteSql() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logisticsqq", "Pr0webUser", "Rromhs1fwvO9WP");
            st = conn.createStatement();
            String sql = "SELECT COUNT(1) FROM message";
            rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println("there is %s records in table message : " + rs.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            st.close();
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        SshConfig sshConfig = new SshConfig("42.62.59.34", "user", "yHbPIL0OkPtSQgchE4UD", 2201);
        PortForwardConfig portForwardConfig = new PortForwardConfig(3306, "10.0.0.45", 3306);
        Session session = JSchUtil.openSshTunnelSession(sshConfig, portForwardConfig);
        if (session == null) {
            System.exit(2);
        }
        connectAndExecuteSql();
        session.disconnect();
    }
}
