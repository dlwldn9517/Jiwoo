package com.gdu.app01.javal04;

public class Soldier {
	
	// field
	private String name;
	private Gun gun;
	
	// constructor
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	// info() 메소드
	public void info() {
		System.out.println("이름 : " + name);
		gun.info();
	}

}
