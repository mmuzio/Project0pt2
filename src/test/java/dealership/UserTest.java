package dealership;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dealership.dao.UserDao;
import dealership.dao.UserDaoPostgres;
import dealership.pojo.User;

public class UserTest {
	
	User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		UserDao user = new UserDaoPostgres();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateUser() {
		
		assertEquals("Username should be username", "username", user.getUsername());
		
		/*
		User.createUser("username", "password");
		
		assertEquals("Username should be username", "username", User.getUserName());
		
		assertEquals("Password should be password", "password", auth.getPassWord());
		
		assertEquals("Password should be password in userPwdMap", "password", User.getUserPwdMap().get("username"));
		
		assertEquals("Usertype should be admin", "admin", User.getUserType());
		
		assertEquals("Usertype should be admin in userTypeMap", "admin", User.getUserTypeMap().get("username"));	
		*/
	}

}
