package club.do4u.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	private static DruidDataSource dataSource;
	static {
		Properties properties = new Properties();
//		获取四个基本信息并输出流
		InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
//		加载数据
			properties.load(inputStream);
//		创建 数据库链接池
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	从连接池中获取链接对象
	public static Connection getConnection() {
//		这个connection类是java.sql.connection,不是com.mysql.jdbc的connection
		Connection connection = null;
		try {
			connection = (Connection) dataSource.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
//关闭链接对象
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
