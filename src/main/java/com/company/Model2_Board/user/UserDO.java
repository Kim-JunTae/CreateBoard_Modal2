package com.company.Model2_Board.user;

public class UserDO {
	//�ʵ�(������Ƽ, �߰������)
	private String id, password, name, role;

	//�ʵ� �ϳ��� getter, setter �޼ҵ�
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	
	//toString() �������̵� => ó���Ǵ��� �׽�Ʈ�ϱ� ���ؼ� �߰�
	@Override
	public String toString() {
		return "UserDO [id="+id+", password="+password+", "
				+ "name="+name+", role="+role+"]";
	}
}