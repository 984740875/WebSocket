package club.do4u.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class pageFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		String username=(String) req.getSession().getAttribute("username");
		if(username==null) {
//			session没有用户信息，判断该用户没有登录，跳转到登录界面
			req.setAttribute("error", "请先登录！");
			req.getRequestDispatcher("../publicPage/Login.html").forward(request, response);
			return;
		}else {
//			让程序继续往下访问目标资源
			chain.doFilter(request, response);
		}
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
