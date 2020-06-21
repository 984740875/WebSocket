package club.do4u.pojo;

import java.util.List;

public class OnLineNumbers {
final private String name="servicer";
private List<String> onlineNumber;
public OnLineNumbers(List<String> onlineNumber) {
	super();
	this.onlineNumber = onlineNumber;
}
public OnLineNumbers() {
	super();
}
public List<String> getOnlineNumber() {
	return onlineNumber;
}
public void setOnlineNumber(List<String> onlineNumber) {
	this.onlineNumber = onlineNumber;
}
public String getName() {
	return name;
}
@Override
public String toString() {
	return "OnLineNumbers [name=" + name + ", onlineNumber=" + onlineNumber + "]";
}

}
