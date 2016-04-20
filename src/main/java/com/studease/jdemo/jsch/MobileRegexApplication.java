package com.studease.jdemo.jsch;

import com.jcraft.jsch.Session;
import java.sql.SQLException;
import redis.clients.jedis.Jedis;

/**
 * Author: liushaoping
 * Date: 2016/2/17.
 */
public class MobileRegexApplication {

    private static final String KEY = "ms2_content_mobile_regex_key";
    private static final String REGEX_STR = "\\d(\\s|\\d|\\p{P}|\\||O|o|〇|一|二|三|四|五|六|七|八|九|零|壹|贰|叁|肆|伍|陆|柒|捌|玖|①|②|③|④|⑤|⑥|⑦|⑧|⑨|~|\\$|`|\\^|=|<|>|～|\\+|￥|×){10,}(?!米)";

    public static void main(String[] args) throws SQLException {
        SshConfig sshConfig = null; // TODO add 有人jumper-config
        PortForwardConfig portForwardConfig = new PortForwardConfig(6384, "10.0.0.43", 6384);
        Session session = JSchUtil.openSshTunnelSession(sshConfig, portForwardConfig);
        if (session == null) {
            System.exit(2);
        }
        // connectAndExecuteDev();
        connectAndExecuteOnline();
        System.out.println("completed successfully !");
        session.disconnect();
    }

    private static void connectAndExecuteDev() {
        Jedis jedis = new Jedis("192.168.1.204", 6379, 30000);
        jedis.auth("97624DD3A48FA681");
        String s = jedis.get(KEY);
        System.out.println(s);
        jedis.set(KEY, REGEX_STR);
        s = jedis.get(KEY);
        System.out.println(s);
    }

    private static void connectAndExecuteOnline() {
        Jedis jedis = new Jedis("localhost", 6384, 300000);
        jedis.auth("countcountcount");
        String s = jedis.get(KEY);
        System.out.println(s);
        jedis.set(KEY, REGEX_STR);
        s = jedis.get(KEY);
        System.out.println(s);
    }

}
