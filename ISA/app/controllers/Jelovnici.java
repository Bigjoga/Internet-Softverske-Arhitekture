package controllers;

import java.util.List;

import models.Jelovnik;
import models.Restoran;
import play.mvc.Controller;

public class Jelovnici extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		List<Jelovnik> jelovnici = Jelovnik.findAll();
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(jelovnici,mode,selectedIndex);
	}

}
