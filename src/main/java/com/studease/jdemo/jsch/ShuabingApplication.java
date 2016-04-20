package com.studease.jdemo.jsch;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class ShuabingApplication {

    public static void connectAndExecuteSql() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3306/message", "messageread", "mrbobo.3434345");
            st = conn.createStatement();
            // String sql = "select domain_id, user_id, departure_place_id, destination_place_id, create_time, content from t_message where create_time BETWEEN '2016-04-12 18:00:00' and '2016-04-13 18:00:00' order by domain_id, user_id, create_time";
            String sql = "select domain_id, user_id, departure_place_id, destination_place_id, create_time, content from t_message where create_time BETWEEN '2016-04-12 18:00:00' and '2016-04-13 18:00:00' order by domain_id, user_id, departure_place_id, destination_place_id, create_time";
            rs = st.executeQuery(sql);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String> lines = new ArrayList<>();
            String line = null;
            String tab = "\t";
            String newLine = "\r\n";
            int i = 0;
            while (rs.next()) {
                if (i % 1000 == 0) {
                    if (!lines.isEmpty()) {
                        FileUtils.writeLines(new File("D:\\shuabing-2016-14-13.txt"), lines, true);
                        lines = new ArrayList<>();
                    }
                }
                line = new StringBuilder().append(rs.getInt("domain_id")).append(tab)
                        .append(rs.getInt("user_id")).append(tab)
                        .append(rs.getInt("departure_place_id")).append(tab)
                        .append(rs.getString("destination_place_id")).append(tab)
                        .append(dateFormat.format(new Date(rs.getTimestamp("create_time").getTime()))).append(tab)
                        .append(rs.getString("content")).toString();
                lines.add(line);
                i++;
            }
            if (!lines.isEmpty()) {
                FileUtils.writeLines(new File("D:\\shuabing-2016-14-13.txt"), lines, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            st.close();
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        /*SshConfig sshConfig = new SshConfig("42.62.59.34", "user", "yHbPIL0OkPtSQgchE4UD", 2201);
        PortForwardConfig portForwardConfig = new PortForwardConfig(3306, "10.0.0.45", 3306);
        Session session = JSchUtil.openSshTunnelSession(sshConfig, portForwardConfig);
        if (session == null) {
            System.exit(2);
        }*/
        connectAndExecuteSql();
        System.out.println("completed successfully !");
        /*session.disconnect();*/
    }
}
