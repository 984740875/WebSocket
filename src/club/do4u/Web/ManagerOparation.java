package club.do4u.Web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import club.do4u.Dao.UserDao;
import club.do4u.Dao.DaoImpl.UserDaoImpl;
import club.do4u.pojo.User;
import club.do4u.pojo.UserPage;

public class ManagerOparation extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserDao userDao=new UserDaoImpl();
	protected void deleteUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		if (userDao.deleteUserById(Integer.parseInt(id))) {
			Map<String,Boolean> map=new HashMap<String, Boolean>();
			map.put("success", true);
			String json=JSON.toJSONString(map);
			resp.getWriter().write(json);
		}else
		{
			Map<String,Boolean> map=new HashMap<String, Boolean>();
			map.put("success", false);
			String json=JSON.toJSONString(map);
			resp.getWriter().write(json);
		}
	}
	
	/**
	 *两个参数，当前页数和每页的记录数
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPage=req.getParameter("currentPage");
		String size=req.getParameter("size");
//		如果请求里面参数没有，那么就给个默认值
		if (currentPage==null) {
			currentPage="1";
		}
		if (size==null) {
			size="5";
		}
		int cp=Integer.parseInt(currentPage);
//		每有size个记录
		int s=Integer.parseInt(size);
//		记录其实索引
		int start=(cp-1)*s;
//		总的记录数
		int totalUser=userDao.getUserCount();
//		返回页的message数据
		List<User> users=userDao.getUserList(start,s);
//		计算总页数
		int totalPage=totalUser/s;
		if(totalUser%s>0) {
			totalPage++;
		}
		UserPage userPage=new UserPage();
		userPage.setCurrentPage(cp);
		userPage.setSize(s);
		userPage.setTotalPage(totalPage);
		userPage.setTotalUser(totalUser);
		userPage.setUserlist(users);
		String json=JSON.toJSONString(userPage);
		resp.getWriter().write(json);
	}
	
	

}
