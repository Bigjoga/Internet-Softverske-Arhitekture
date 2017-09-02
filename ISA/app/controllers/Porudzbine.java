package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Jelovnik;
import models.Restoran;
import play.mvc.Controller;

public class Porudzbine extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		/*
		List<Jelovnik> jelovnici = Jelovnik.findAll();
		List<Jelovnik>  jelovnicizaprikaz= new ArrayList<>();
		
		for(int i=0; i<jelovnici.size();i++){
			if(jelovnici.get(i).restoran.nazivRestorana.equals(session.get("restoran").toString())){
				jelovnicizaprikaz.add(jelovnici.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(jelovnicizaprikaz,mode,selectedIndex);
		*/
	}

}
