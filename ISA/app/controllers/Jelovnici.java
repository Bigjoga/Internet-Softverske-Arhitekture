package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Jelovnik;
import models.Restoran;
import play.mvc.Controller;

public class Jelovnici extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		List<Jelovnik> jelovnici = Jelovnik.findAll();
		List<Jelovnik>  jelovnicizaprikaz= new ArrayList<>();
		List<Restoran> restorano= Restoran.findAll();
	    Restoran restoran = new Restoran();
		for(int  i =0; i<restorano.size();i++){
			if(restorano.get(i).nazivRestorana.equals(session.get("restoran"))){
				restoran=restorano.get(i);
			}
		}
		
		for(int i=0; i<jelovnici.size();i++){
			if(jelovnici.get(i).restoran.nazivRestorana.equals(session.get("restoran").toString())){
				jelovnicizaprikaz.add(jelovnici.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(jelovnicizaprikaz,restoran,mode,selectedIndex);
	}
	
	public static void showGosti(String mode, Long selectedIndex)
	{
		List<Jelovnik> jelovnici = Jelovnik.findAll();
		List<Jelovnik>  jelovnicizaprikaz= new ArrayList<>();
		List<Restoran> restorano= Restoran.findAll();
	    Restoran restoran = new Restoran();
		for(int  i =0; i<restorano.size();i++){
			if(restorano.get(i).nazivRestorana.equals(session.get("restoran"))){
				restoran=restorano.get(i);
			}
		}
		
		for(int i=0; i<jelovnici.size();i++){
			if(jelovnici.get(i).restoran.nazivRestorana.equals(session.get("restoran").toString())){
				jelovnicizaprikaz.add(jelovnici.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		
		render(jelovnicizaprikaz,restoran,mode,selectedIndex);
	}
	
	public static void izborJelovnika(Jelovnik jelovnik)
	{ 
		session.put("jelovnik", jelovnik.nazivJelovnika);
		redirect("http://localhost:9000/StavkeJelovnika/show");
	}
	
	public static void create(Jelovnik jelovnik, Long restoran)
	{
	
		Restoran rest = Restoran.findById(restoran);
		jelovnik.restoran=rest;
		jelovnik.save();
		show("add",jelovnik.id);
	}
	
	public static void edit(Jelovnik jelovnik, Long restoran)
	{
		Restoran rest = Restoran.findById(restoran);
		jelovnik.restoran=rest;
		jelovnik.save();
		show("edit",jelovnik.id);
	}
	
	public static void delete(Long id)
	{
		Jelovnik jelovnik = Jelovnik.findById(id);
		jelovnik.delete();
		show("edit",jelovnik.id-1);
	}
	
}

