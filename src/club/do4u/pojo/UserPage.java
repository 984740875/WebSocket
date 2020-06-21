package club.do4u.pojo;

import java.util.List;

public class UserPage {
private int currentPage;
private int size;
private int totalUser;
private int totalPage;
private List<User> userlist;

public UserPage() {
	super();
}

public UserPage(int currentPage, int size, int totalUser, int totalPage, List<User> userlist) {
	super();
	this.currentPage = currentPage;
	this.size = size;
	this.totalUser = totalUser;
	this.totalPage = totalPage;
	this.userlist = userlist;
}

public List<User> getUserlist() {
	return userlist;
}

public void setUserlist(List<User> userlist) {
	this.userlist = userlist;
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

public int getTotalPage() {
	return totalPage;
}
public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}

public int getTotalUser() {
	return totalUser;
}

public void setTotalUser(int totalUser) {
	this.totalUser = totalUser;
}

@Override
public String toString() {
	return "UserPage [currentPage=" + currentPage + ", size=" + size + ", totalUser=" + totalUser + ", totalPage="
			+ totalPage + ", userlist=" + userlist + "]";
}



}
