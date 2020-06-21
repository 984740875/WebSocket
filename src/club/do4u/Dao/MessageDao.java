package club.do4u.Dao;

import java.util.List;

import club.do4u.pojo.Message;

public interface MessageDao {
	List<Message> queryBySender(String sender,int start,int size);
	List<Message> queryByContent(String content,int start,int size);
	int queryCountBySender(String sender);
	int queryCountByContent(String content);
	List<Message> queryByTime(String beginDate,String endDate );
	List<Message> getTenMessage();
	boolean addMessage(Message message);
	boolean deleteMessageById(Integer id);
}
