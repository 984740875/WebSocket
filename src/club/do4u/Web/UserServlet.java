package club.do4u.Web;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import club.do4u.Dao.UserDao;
import club.do4u.Dao.DaoImpl.UserDaoImpl;
import club.do4u.pojo.User;
import club.do4u.utils.WebUtils;
import  static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet{
	UserDao userDao=new UserDaoImpl();
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
 * 注册操作
 * @param req
 * @param resp
 * @throws ServletException
 * @throws IOException
 */
protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	获取谷歌验证码，来自：KAPTCHA_SESSION_KEY
	String token=(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
//	删除session中的验证码
	req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
//	获取表单的验证码
	String checkcode=(String) req.getParameter("checkcode");
//	判断验证码是否正确
	if(token!=null&&token.equalsIgnoreCase(checkcode)) {
//		验证码正确
//		把用户信息写入数据库
		User user=new User();
		WebUtils.copyMapToBean(req.getParameterMap(), user);
		userDao.addUser(user);
//		设置session和cookie并且重定向到聊天窗口界面
		setSessionAndCookie(req,resp,user.getName());
		
	}else {
//		验证码不正确
//		携带用户名和密码请求转发回注册界面
//		req.setAttribute("username",req.getParameter("name"));
//		req.setAttribute("password",req.getParameter("password"));
//		System.out.println("请求转发");
//		req.setAttribute("error","验证码不正确");
		resp.sendRedirect("publicPage/Register.html?error=errorcode");
	}
	

}

	/**
	 * 登陆或注册成功则添加session身份，向浏览器添加cookie，重定向到聊天界面
	 * @param req
	 * @param resp
	 * @param name
	 */
	private void setSessionAndCookie(HttpServletRequest req, HttpServletResponse resp,String name) {
//		向session中添加用户身份
		req.getSession().setAttribute("username",name);
//		新建一个cookie给聊天页面获取用户的名称
		Cookie cookie=new Cookie("username", name);
//		/page下的所有页面都可以获取这个cookie
		cookie.setPath(req.getContextPath()+"/page");
		resp.addCookie(cookie);
//		重定向到聊天页面
		try {
			resp.sendRedirect("http://localhost:8080/WebSockt/page/index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	/**
	 * 登录操作
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		User user=userDao.queryByName(username);
		if (user!=null&&user.getPassword().equals(password)) {
//			登录成功
			setSessionAndCookie(req,resp,username);
		}else
		{
//			账号或者密码错误
			resp.sendRedirect("publicPage/Login.html?error=account");
			
		}
	}
	
	protected void isEnableName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
//		System.out.println(username);
		if(userDao.queryByName(username)==null) {
//			该用户不存在，用户名可用
			Map<String, String> map=new HashMap<String, String>();
			map.put("enable", "true");
			String json_str=JSON.toJSONString(map);
//			System.out.println(json_str);
			resp.getWriter().write(json_str);
		}else {
			Map<String, String> map=new HashMap<String, String>();
			map.put("enable", "false");
			String json_str=JSON.toJSONString(map);
//			System.out.println(json_str);
			resp.getWriter().write(json_str);
		}
		
	}
}
