package dealership.service;

import dealership.dao.UserDao;
import dealership.dao.UserDaoPostgres;

import dealership.pojo.User;

public class UserServiceImpl implements UserService {
	
	private static UserDao uDao = new UserDaoPostgres();

	@Override
	public User loginUser(String username, String password) {
		
		User u = uDao.getUserByUsername(username);
		
		if (u != null && u.getPassword().equals(password)) {
			return u;
		}
		
		return null;
		
	}

	@Override
	public User registerUser(String username, String password) {
		
		
		
		return null;
	}

	@Override
	public void updatePassword(String username, String password) {
		
		uDao.createUser(new User(0, username, password));

	}

}
