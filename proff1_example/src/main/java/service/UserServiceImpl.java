package service;

import java.util.List;
import dao.UserDao;
import data.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao = null;

		public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

		@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
	
}
