package com.cice.testMaven.dao;

public class UsuarioRegistro {

	private String nombre;
	private String apellido;
	private String mail;
	private String pass;
	public UsuarioRegistro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsuarioRegistro(String nombre, String apellido, String mail, String pass) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.pass = pass;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + "]";
	}
	
	
	
	
	
}
