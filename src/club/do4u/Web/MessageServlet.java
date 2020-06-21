package club.do4u.Web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import club.do4u.Dao.MessageDao;
import club.do4u.Dao.DaoImpl.MessageDaoImpl;
import club.do4u.pojo.Message;
import club.do4u.pojo.MessagePage;


public class MessageServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	MessageDao messageDao=new MessageDaoImpl();
	
	
	/**
	 * 该方法根据发送者名字，返回page对象的json格式数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void querryBySender(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sender=req.getParameter("sender");
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
		int totalMsg=messageDao.queryCountBySender(sender);
//		返回页的message数据
		List<Message> messages=messageDao.queryBySender(sender,start,s);
//		计算总页数
		int totalPage=totalMsg/s;
		if(totalMsg%s>0) {
			totalPage++;
		}
//		
		MessagePage myPage=new MessagePage();
		myPage.setCurrentPage(cp);
		myPage.setSize(s);
		myPage.setTotalMsg(totalMsg);
		myPage.setTotalPage(totalPage);
		myPage.setMsglist(messages);
		String json=JSON.toJSONString(myPage);
		resp.getWriter().write(json);
		
	}
	
	/**
	 * 该方法根据信息的内容，返回page对象的json格式数据
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void querryByContent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content=req.getParameter("content");
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
		int totalMsg=messageDao.queryCountByContent(content);
//		返回页的message数据
		List<Message> messages=messageDao.queryByContent(content, start, s);
//		计算总页数
		int totalPage=totalMsg/s;
		if(totalMsg%s>0) {
			totalPage++;
		}
//		
		MessagePage myPage=new MessagePage();
		myPage.setCurrentPage(cp);
		myPage.setSize(s);
		myPage.setTotalMsg(totalMsg);
		myPage.setTotalPage(totalPage);
		myPage.setMsglist(messages);
		String json=JSON.toJSONString(myPage);
		resp.getWriter().write(json);
	}
}
