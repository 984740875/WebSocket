package club.do4u.Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSON;

import club.do4u.Dao.MessageDao;
import club.do4u.Dao.DaoImpl.MessageDaoImpl;
import club.do4u.pojo.Message;
import club.do4u.pojo.OnLineNumbers;


/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/WebSocketTest")
public class WebSocketTest {

//	记录在线用户的用户名Map，应该把它设计为线程安全的。
	private static Map<String,String> onLineNumbers = new HashMap<String, String>();
//	该ArraySet是concurrent中线程安全的Set，用来存放每个client对应的WebSocket对象。
	private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<>();
//	与某个客户端的连接会话，需要通过它给客户端发送数据
	private Session session;
//	操作数据库
	private static MessageDao msgDao=new MessageDaoImpl();

	@OnOpen // 打开连接执行
	public void onOpw(Session session) {
		this.session = session;
		webSocketSet.add(this);
//		sendHistoryMsg();
		System.out.println(session.getId()+"用户打开了连接");
		System.out.println(Thread.currentThread());
	}



	@OnMessage 
	public void onMessage(String message, Session session) {
		System.out.println(message);
		if (isjson(message)) {
//		检查客户端发送的信息符合规范
//		1.将json字符串转换成对象
			Message clinetMsg=JSON.parseObject(message,Message.class);
			if(clinetMsg.getContent().equals("join")) {
//				2.新用户加入，处理它,加入到在线列表
					addOnlineNumber(session.getId(),clinetMsg.getSender());
//				3.将线用户列表的json字符串发送给所有客户端
					sendOnlineMsg();

				}else {
//					1.信息直接转发
					sendToAllUser(message);
//					2将记录转存到数据库
					saveMsg(clinetMsg);
				}
		}else {
//			不符合规范
			sendToAllUser(message+"---该信息不符合Message-json约定规范");
		}
		
		
		
		
	}

	@OnClose // 关闭连接执行
	public void onClose(Session session) {
		webSocketSet.remove(this);
		System.out.println(session.getId()+"已下线");
//		1.从在线列表中删除该用户信息
		subOnlineNumber(session.getId());
//		2.将在线用户列表的json字符串发送给所有客户端
		sendOnlineMsg();
	}

	@OnError // 连接错误的时候执行
	public void onError(Throwable error, Session session) {
		System.out.println("错误的时候执行");
		error.printStackTrace();
	}

	/*
	 * websocket session发送文本消息有两个方法：getAsyncRemote()和 getBasicRemote()
	 * getAsyncRemote()和getBasicRemote()是异步与同步的区别， 大部分情况下，推荐使用getAsyncRemote()。
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getAsyncRemote().sendText(message);
	}

	/**
	 * 返回在线用户列表onLineNumbers
	 * @return
	 */
	public static synchronized List<String> getOnlineNumber() {
		Map<String,String> onlineMap=WebSocketTest.onLineNumbers;
		List<String> onLineNumbers=new ArrayList<String>();
		for(Map.Entry<String, String> entry:onlineMap.entrySet()) {
			onLineNumbers.add(entry.getValue());
		}
//		返回在线用户列表
		return onLineNumbers;
	}

	/**
	 * 该方法先判断在线yong
	 * @param sessionId
	 * @param name
	 */
	public static synchronized void addOnlineNumber(String sessionId,String name) {
		if (!WebSocketTest.onLineNumbers.containsKey(sessionId)) {
			WebSocketTest.onLineNumbers.put(sessionId, name);
		}
		
	}

	/**
	 * 通过sessionid 删除在线列表中离线的用户
	 * @param sessionId
	 */
	public static synchronized void subOnlineNumber(String sessionId) {
		try {
			WebSocketTest.onLineNumbers.remove(sessionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

 	/**
 	 * 把信息群发给每个在线用户
 	 * @param message
 	 */
 	public static void sendToAllUser(String message) {
		for (WebSocketTest user : webSocketSet) {
			try {
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			遍历，把消息发给每个客户端对象m;
				user.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
 	}
 	
 	
 	/**
 	 * 该方法将在线用户信息通过json字符串发送给所有用户
 	 */
 	public static void sendOnlineMsg() {
//		发送新的在线用户信息给clinet
		OnLineNumbers onlineMsg=new OnLineNumbers();
		onlineMsg.setOnlineNumber(getOnlineNumber());
//	4.将对象转成json字符串，传给所有的用户
		String numberJson=JSON.toJSONString(onlineMsg);
		sendToAllUser(numberJson);
 	}
 	
 	/**
 	 * 将消息记录存入数据库
 	 * @param object
 	 */
 	public static void saveMsg(Message message) {
 		System.out.println("存入数据库代做...");
// 		WebSocketTest.msgDao.addMessage(message);
 	}
 	
 	/**
 	 * 该方法用来检查客户端发送的信息是否符合Message json规范
 	 * @param str
 	 * @return
 	 */
 	public static boolean isjson(String str) {
 		try {
 			Message message=JSON.parseObject(str,Message.class);
 			return true;
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
// 			e.printStackTrace();
 			return false;
 		}
 	}
 	
	/**
	 * 把十条历史消息发给送给刚登陆的当前用户
	 */
	private void sendHistoryMsg() {
//		获取信息
		List<Message> messages=WebSocketTest.msgDao.getTenMessage();
		for(Message item:messages) {
			String json=JSON.toJSONString(item);
			session.getAsyncRemote().sendText(json);
		}
		
		
	}
}
