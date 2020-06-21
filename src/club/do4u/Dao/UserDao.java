package club.do4u.Dao;

import java.util.List;

import club.do4u.pojo.User;

public interface UserDao {
	User queryByName(String name);
	boolean addUser(User user);
	boolean deleteUserById(Integer id);
	boolean updateNameById(String name,Integer id);
	boolean updatePicPathById(String path,Integer id);
	List<User> getUsers(Integer limit);
	int getUserCount();
	List<User> getUserList(int start, int s);
}
