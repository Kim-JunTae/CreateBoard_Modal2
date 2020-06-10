package com.company.Model2_Board.user;

public class UserDO {
	//필드(프로퍼티, 중간저장소)
	private String id, password, name, role;

	//필드 하나당 getter, setter 메소드
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}

	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getRole() {return role;}
	public void setRole(String role) {this.role = role;}
	
	//toString() 오버라이딩 => 처리되는지 테스트하기 위해서 추가
	@Override
	public String toString() {
		return "UserDO [id="+id+", password="+password+", "
				+ "name="+name+", role="+role+"]";
	}
}