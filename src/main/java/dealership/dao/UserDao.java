package dealership.dao;

import dealership.pojo.User;

public interface UserDao {

	public void createUser(User u);
	
	public void updateUser(User u);
	
	// public void deleteUser(User u);
	
	// public User getUserById(Integer id);
	
	public User getUserByUsername(String username);
	
	// public List<User> getAllUsers();
	
}
