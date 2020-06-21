package club.do4u.Dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import club.do4u.utils.JDBCUtils;
/**
 * 该基本类提供了增删查改的基本操作，具体Dao类继承该类并写具体的查询语句调用该类中的方法即可返回需要的 结果
 * @author 王鑫
 *
 */
public abstract class baseDao {
	private QueryRunner queryRunner = new QueryRunner();

	/**
	 * 该方法用来执行insert/update/delete语句
	 * 
	 * @param sql  SQL语句
	 * @param args 占位符参数值
	 * @return 如果返回-1则表示执行失败
	 */
	public int update(String sql, Object... args) {
		Connection connection = JDBCUtils.getConnection();
		try {
			return queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection);
		}
		return -1;
	}

	/**
	 * 该方法查询返回一个javaBean对象
	 * 
	 * @param type 返回的对象类型
	 * @param sql  sql语句
	 * @param args sql占位符对应参数
	 * @return
	 */
	public <T> T queryForOne(Class<T> type, String sql, Object... args) {
		Connection conn = JDBCUtils.getConnection();
		try {
//			返回一个type类型对象
//			BeanHandler将结果集中的第一行数据封装到一个对应的JavaBean实例中
//			ps:装载查询结果的集合种类查阅：https://blog.csdn.net/weixin_38465623/article/details/80794976
			return queryRunner.query(conn, sql, new BeanHandler<>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		return null;
	}

	/**
	 * 该方法查询返回List对象
	 * 
	 * @param type 返回的对象类型
	 * @param sql  sql语句
	 * @param args sql占位符对应参数
	 * @return
	 */
	public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
		Connection conn = JDBCUtils.getConnection();
		try {
//BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里
			return queryRunner.query(conn, sql, new BeanListHandler<>(type), args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn);
		}
		return null;
	}
	
	/**
	 * 执行返回一行一列的SQL语句
	 * @param sql SQL语句
	 * @param args SQL占位符对应参数
	 * @return 
	 */
	public Object queryForSingleVale(String sql,Object...args) {
		Connection conn = JDBCUtils.getConnection();
		try {
//ScalarHandler的作用是将数据库中某一个字段的数据封装成一个Object对象
			return queryRunner.query(conn,sql,new ScalarHandler(),args);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn);
		}
		return null;
	}

}
