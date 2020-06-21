package club.do4u.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import club.do4u.Dao.MessageDao;
import club.do4u.Dao.DaoImpl.MessageDaoImpl;
import club.do4u.pojo.Message;

public class testmessage {
public static void main(String[] args) {
	MessageDao messageDao=new MessageDaoImpl();
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date=simpleDateFormat.format(new Date());
	Message message1=new Message(null,"liuwei","testForMessage1",date);
	Message message2=new Message(null,"guang","testForMessage2",date);
	Message message3=new Message(null,"lin","testForMessage3",date);
	messageDao.addMessage(message1);
	messageDao.addMessage(message2);
	messageDao.addMessage(message3);
//	List<Message> messages=messageDao.getTenMessage();
//	for(Message item:messages) {
//		String json_str=JSON.toJSONString(item);
//		System.out.println(json_str);}

}
}
