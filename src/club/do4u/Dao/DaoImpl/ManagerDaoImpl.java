package club.do4u.Dao.DaoImpl;

import club.do4u.Dao.ManagerDao;
import club.do4u.Dao.baseDao;
import club.do4u.pojo.Manager;

public class ManagerDaoImpl extends baseDao implements ManagerDao{

	@Override
	public Manager queryByName(String name) {
		String sql="select * from manager where name=?";
		return queryForOne(Manager.class, sql, name);
	}

}
