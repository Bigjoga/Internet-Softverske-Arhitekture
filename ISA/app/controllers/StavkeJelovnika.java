package controllers;

import java.util.List;

import models.Jelo;
import models.Jelovnik;
import models.StavkaJelovnika;
import play.mvc.Controller;

public class StavkeJelovnika extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		
		
		List<StavkaJelovnika> stavkeJelovnika = StavkaJelovnika.findAll();
		List<Jelo> jela = Jelo.findAll();
		List<Jelovnik> jelovnici = Jelovnik.findAll();
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(jela,jelovnici,stavkeJelovnika,mode,selectedIndex);
	}
}