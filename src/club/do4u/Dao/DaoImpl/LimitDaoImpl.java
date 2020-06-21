package club.do4u.Dao.DaoImpl;

import java.util.List;

import club.do4u.Dao.LimitDao;
import club.do4u.Dao.baseDao;
import club.do4u.pojo.Limit;

public class LimitDaoImpl extends baseDao implements LimitDao {

	@Override
	public boolean addLimit(String litim) {
		String sql="insert into limit(id,content) values(null,?)";
		if (update(sql, litim)!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteLimitById(Integer id) {
		String sql="delete from limit where id=?";
		if(update(sql, id)!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Limit> getAllLimit() {
		String sql="select * from limit";
		return queryForList(Limit.class, sql);
	}

}
