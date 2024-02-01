package sub1;

public class UserDTO {
	
	private String uid;
	private String pass;
	private String name;
	private int age; //속성 
	
	//생성자도 만들 수 있음.

	
	@Override
	public String toString() {
		return "UserDTO [uid=" + uid + ", name=" + name + ", age=" + age + ", pass=" + pass +  "]"; 
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String pass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

}
