package club.do4u.Dao.DaoImpl;
import java.util.List;

import club.do4u.Dao.UserDao;
import club.do4u.Dao.baseDao;
import club.do4u.pojo.User;

public class UserDaoImpl extends baseDao implements UserDao {

	@Override
	public User queryByName(String name) {
		String sql="select * from user where name=?";
		return queryForOne(User.class, sql, name);
	}

	@Override
	public boolean addUser(User user) {
		String sql="insert into user(id,name,password,imgPath) values (?,?,?,?)";
		if(update(sql,user.getId(),user.getName(),user.getPassword(),user.getPicPath())!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUserById(Integer id) {
		String sql="delete from user where id=?";
		if(update(sql, id)!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateNameById(String name,Integer id) {
		String sql="update user set name=?,where id=?";
		if(update(sql,name,id)!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePicPathById(String path,Integer id) {
		String sql="update user set imgPath=? where id=?";
		if(update(sql,id)!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> getUsers(Integer  limit) {
		
		String sql="select * from user order by id desc limit ?";
		return queryForList(User.class, sql, limit);
	}

	@Override
	public int getUserCount() {
		String sql="select count(*) from user";
		Object object=queryForSingleVale(sql);
		int count=Integer.parseInt(object.toString());
		return count;
	}

	@Override
	public List<User> getUserList(int start, int s) {
		// TODO Auto-generated method stub
		return null;
	}



}
