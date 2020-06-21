package club.do4u.pojo;

public class Limit {
private Integer id;
private String content;
public Limit(Integer id, String content) {
	super();
	this.id = id;
	this.content = content;
}
public Limit() {
	super();
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "Limit [id=" + id + ", content=" + content + "]";
}

}
