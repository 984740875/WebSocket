package club.do4u.pojo;

public class Message {
private Integer id;
private String sender;
private String content;
private String date;


public Message(Integer id, String sender, String content, String date) {
	super();
	this.id = id;
	this.sender = sender;
	this.content = content;
	this.date = date;
}

public Message() {
	super();
}

public String getSender() {
	return sender;
}
public void setSender(String sender) {
	this.sender = sender;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

@Override
public String toString() {
	return "Message [id=" + id + ", sender=" + sender + ", content=" + content + ", date=" + date + "]";
}



}
