package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration(classes = {RootConfig.class})
public class DataSourceTest {

    @Inject
    private DataSource dataSource;
    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection() {

        try (Connection conn =  dataSource.getConnection()) {
            System.out.println(conn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void testMyBatis() {

        try (SqlSession session = sqlSessionFactory.openSession()) {
            System.out.println(session);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


}
