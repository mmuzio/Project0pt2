package dealership.service;

import dealership.pojo.User;

public interface UserService {
	
	public User loginUser(String username, String password);
	
	public User registerUser(String username, String password);
	
	public void updatePassword(String username, String password);

}
