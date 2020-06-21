package club.do4u.pojo;

import java.util.List;

public class MessagePage {
private int currentPage;
private int size;
private int totalMsg;
private int totalPage;
private List<Message> msglist;
public MessagePage(int currentPage, int size, int totalMsg, int totalPage, List<Message> msglist) {
	super();
	this.currentPage = currentPage;
	this.size = size;
	this.totalMsg = totalMsg;
	this.totalPage = totalPage;
	this.msglist = msglist;
}
public MessagePage() {
	super();
}
public int getCurrentPage() {
	return currentPage;
}
public void setCurrentPage(int currentPage) {
	this.currentPage = currentPage;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
public int getTotalMsg() {
	return totalMsg;
}
public void setTotalMsg(int totalMsg) {
	this.totalMsg = totalMsg;
}
public int getTotalPage() {
	return totalPage;
}
public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}
public List<Message> getMsglist() {
	return msglist;
}
public void setMsglist(List<Message> msglist) {
	this.msglist = msglist;
}
@Override
public String toString() {
	return "page [currentPage=" + currentPage + ", size=" + size + ", totalMsg=" + totalMsg + ", totalPage=" + totalPage
			+ ", msglist=" + msglist + "]";
}

}
