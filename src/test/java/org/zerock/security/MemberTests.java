package org.zerock.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
public class MemberTests {

    private static final Logger log = LoggerFactory.getLogger(MemberTests.class);

    @Autowired
    private PasswordEncoder pwencoder;

    @Autowired
    private DataSource ds;

    @Test
    public void testInsertMember() {

        String sql = "INSERT INTO tbl_member(userid, userpw, username) VALUES (?,?,?);";

        for (int i = 0; i < 100; i++) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {

                conn = ds.getConnection();
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(2, pwencoder.encode("pw" + i));

                if (i < 80) {
                    pstmt.setString(1, "user" + i);
                    pstmt.setString(3, "일반사용자" + i);
                } else if (i < 90) {
                    pstmt.setString(1, "manager" + i);
                    pstmt.setString(3, "운영자" + i);
                } else {
                    pstmt.setString(1, "admin" + i);
                    pstmt.setString(3, "관리자" + i);
                }

                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (pstmt != null) {
                    try { pstmt.close(); } catch (Exception e) {}
                }
                if (conn != null) {
                    try { conn.close(); } catch (Exception e) {}
                }
            }

        }

    }

    @Test
    public void testInsertAuth() {

        String sql = "INSERT INTO tbl_member_auth(userid, auth) VALUES (?,?);";

        for (int i = 0; i < 100; i++) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {

                conn = ds.getConnection();
                pstmt = conn.prepareStatement(sql);

                if (i < 80) {
                    pstmt.setString(1, "user" + i);
                    pstmt.setString(2, "ROLE_USER");
                } else if (i < 90) {
                    pstmt.setString(1, "manager" + i);
                    pstmt.setString(2, "ROLE_MEMBER");
                } else {
                    pstmt.setString(1, "admin" + i);
                    pstmt.setString(2, "ROLE_ADMIN");
                }

                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (pstmt != null) {
                    try { pstmt.close(); } catch (Exception e) {}
                }
                if (conn != null) {
                    try { conn.close(); } catch (Exception e) {}
                }
            }

        }

    }

}
