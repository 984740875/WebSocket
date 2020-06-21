package club.do4u.Dao;

import java.util.List;

import club.do4u.pojo.Limit;

public interface LimitDao {
boolean addLimit(String litim);
boolean deleteLimitById(Integer id);
List<Limit> getAllLimit();
}
