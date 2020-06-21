package club.do4u.Dao.DaoImpl;

import java.util.List;

import club.do4u.Dao.MessageDao;
import club.do4u.Dao.baseDao;
import club.do4u.pojo.Message;

public class MessageDaoImpl extends baseDao implements MessageDao {

	/* 请求参数：发送者，当前页，每页容纳的记录条数
	 * @see club.do4u.Dao.MessageDao#queryBySender(java.lang.String, int, int)
	 */
	@Override
	public List<Message> queryBySender(String sender,int start,int size) {
			String sql="select sender,content,DATE_FORMAT(date,'%Y-%m-%d %T') as date from chatlog where sender=? limit ?,?";
			return queryForList(Message.class, sql,sender,start,size);
	}

	@Override
	public List<Message> queryByTime(String beginDate, String endDate) {
			if(beginDate.length()>10) {
				String ymd=beginDate.substring(0,10);
				beginDate=ymd+" 00:00:00";
			}
			if (endDate.length()>10) {
				String ymd=beginDate.substring(0,10);
				endDate=ymd+" 00:00:00";
			}
			String sql="select sender,content,DATE_FORMAT(date,'%Y-%m-%d %T') as date from chatlog where date between ? and ?";
			return queryForList(Message.class, sql,beginDate,endDate);
				

	}

	@Override
	public boolean addMessage(Message message) {
		String sql="insert into chatlog set id=?,sender=?,content=?,date=?";
		if(update(sql,message.getId(),message.getSender(),message.getContent(),message.getDate())!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteMessageById(Integer id) {
		String sql="delete from chatlog where id=?";
		if(update(sql,id)!=-1) {
			return true;
		}
		return false;
	}

	@Override
	public List<Message> getTenMessage() {
		String sql="select sender,content,DATE_FORMAT(date,'%Y-%m-%d %T') as date from chatlog order by id desc limit 10";
		return queryForList(Message.class, sql);
	}

	@Override
	public int queryCountBySender(String sender) {
		String sql="select count(*) from chatlog where sender=?";
		Object object=queryForSingleVale(sql,sender);
		int count=Integer.parseInt(object.toString());
		return (count);
	}

	@Override
	public List<Message> queryByContent(String content, int start, int size) {
		String sql="select sender,content,DATE_FORMAT(date,'%Y-%m-%d %T') as date from chatlog where content like ? limit ?,?";
		String param='%'+content+'%';
		return queryForList(Message.class, sql,param,start,size);
	}

	@Override
	public int queryCountByContent(String content) {
		String sql="select count(*) from chatlog where content like ?";
		String param='%'+content+'%';
		Object object=queryForSingleVale(sql,param);
		int count=Integer.parseInt(object.toString());
		return count;
		
	}


	

}
