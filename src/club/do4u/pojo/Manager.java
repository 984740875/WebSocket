package club.do4u.pojo;

public class Manager {
private Integer id;
private String name;
private String password;
private String ip;
private String date;
public Manager(Integer id, String name, String password, String ip, String date) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
	this.ip = ip;
	this.date = date;
}
public Manager() {
	super();
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
@Override
public String toString() {
	return "Manager [id=" + id + ", name=" + name + ", password=" + password + ", ip=" + ip + ", date=" + date + "]";
}

}
