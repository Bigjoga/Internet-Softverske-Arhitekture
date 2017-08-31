package controllers;

import java.lang.ProcessBuilder.Redirect;

import play.mvc.Controller;

public class Logout extends Controller{

	public static void show(String mode)
	{
		session.clear();
		if(session.isEmpty())
			System.out.println("sada je prazna sesija");
		redirect("http://localhost:9000/logovanje/show");
			
	}
}
