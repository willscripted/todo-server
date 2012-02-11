package todo.services;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;

/**
 * Test the implementation of UserService.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"hibernateTest.xml", ""})
public class UserServiceImplTest extends DBTestCase {

    public UserServiceImplTest(String name) {
        super(name);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:file:mem:todoTest" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
        // System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

    public void setUp() {
    }

    @Test
    public void testUsernameExists() throws Exception {

    }

    @Test
    public void testGetUserByEmail() throws Exception {

    }

    @Test
    public void testGetUserByUsername() throws Exception {

    }

    @Test
    public void testCreateUser() throws Exception {

    }

    @Test
    public void testGetUsernames() throws Exception {

    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testRemoveUserByUsername() throws Exception {

    }

    @Test
    public void testChangePassword() throws Exception {

    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream
                                                 ("dataset.xml"));
    }
}
