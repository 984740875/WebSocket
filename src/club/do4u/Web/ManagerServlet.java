package club.do4u.Web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import club.do4u.Dao.ManagerDao;
import club.do4u.Dao.DaoImpl.ManagerDaoImpl;
import club.do4u.pojo.Manager;

public class ManagerServlet extends BaseServlet {

	/**
	 * 
	 */
	ManagerDao managerDao=new ManagerDaoImpl();
	private static final long serialVersionUID = 1L;
	
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("username");
		String password=req.getParameter("password");
		Manager manager=managerDao.queryByName(name);
		if (manager.getPassword().equals(password)) {
//			用户名和密码都正确,请求转发
			req.getSession().setAttribute("name",name);
//			
			resp.sendRedirect("");
			
		}else {
//			
			req.getRequestDispatcher("");
		}
		
	}
	
	

	

}
