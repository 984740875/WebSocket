package club.do4u.pojo;

public class User {
private Integer id;
private String name;
private String password;
private String picPath;
public User(Integer id, String name, String password, String picPath) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
	this.picPath = picPath;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public User() {
	super();
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
public String getPicPath() {
	if (picPath==null) {
		return "/img/profilePic.jpg";
	}
	return picPath;
}
public void setPicPath(String picPath) {
	this.picPath = picPath;
}

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", password=" + password + ", picPath=" + picPath + "]";
}


}
