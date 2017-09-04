package controllers;

import java.util.ArrayList;
import java.util.List;

import org.h2.engine.SysProperties;

import models.Jelo;
import models.Jelovnik;
import models.Porudzbina;
import models.Restoran;
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
		List<StavkaJelovnika> stavkeZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<stavkeJelovnika.size();i++){
			if(stavkeJelovnika.get(i).jelovnik.nazivJelovnika.equals(session.get("jelovnik"))){
				stavkeZaPrikaz.add(stavkeJelovnika.get(i));
				System.out.println("usao je u sesiju");
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeZaPrikaz,mode,selectedIndex);
	}
	
	public static void showMenadzer(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		
		List<StavkaJelovnika> stavkeJelovnika = StavkaJelovnika.findAll();
		List<StavkaJelovnika> stavkeZaPrikaz = new ArrayList<>();
		List<Jelovnik> listaJelovnika	= Jelovnik.findAll();
		 
		Jelovnik jelovnik = new Jelovnik();
		
		
		for(int i=0 ; i<listaJelovnika.size();i++){
			if(listaJelovnika.get(i).nazivJelovnika.equals(session.get("jelovnik"))){
				jelovnik=listaJelovnika.get(i);
			}
			
		}
		
		System.out.println("selektovan je jelovnik:" + jelovnik.nazivJelovnika);
		
		for(int i=0; i<stavkeJelovnika.size();i++){
			if(stavkeJelovnika.get(i).jelovnik.nazivJelovnika.equals(session.get("jelovnik"))){
				stavkeZaPrikaz.add(stavkeJelovnika.get(i));
				System.out.println("dosao sam dovde");
			}
		}
		
		for(int i=0;i<stavkeZaPrikaz.size();i++){
			System.out.println(stavkeZaPrikaz.get(i).jelo.nazivJela);
		}
		
		System.out.println("jelovnik je : " + jelovnik.nazivJelovnika);
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		render(stavkeZaPrikaz,jelovnik,mode,selectedIndex);
	}
	
	
	public static void naruci(StavkaJelovnika stavkaJelovnika)
	{ 
		session.put("stavkaJelovnika", stavkaJelovnika.cena);
		System.out.println("STAVKA JELOVNIKA IMA CENU ---> " + session.get("stavkaJelovnika"));
		
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran = new Restoran();
		for(int i = 0; i<restorani.size(); i++)
		{
			if(restorani.get(i).nazivRestorana.equals(session.get("restoran")))
			{
				restoran = restorani.get(i);
			}
		}
		
		Porudzbina porudzbina = new Porudzbina(stavkaJelovnika, restoran);
		porudzbina.save();
		redirect("http://localhost:9000/Jelovnici/showGosti");
	}
	
	public static void create(StavkaJelovnika stavkaJelovnika, String opis, String jelo, Double cena)
	{
		Jelo dodajJelo= new Jelo(jelo, opis);
		dodajJelo.save();
		List<Jelovnik> jelovnici=Jelovnik.findAll();
		Jelovnik jel= new Jelovnik();
		
		for(int i=0; i<jelovnici.size();i++){
			if(jelovnici.get(i).nazivJelovnika.equals(session.get("jelovnik"))){
				jel=jelovnici.get(i);
			}
		}
		
		StavkaJelovnika stav= new StavkaJelovnika(cena, jel, dodajJelo);
		stav.save();
		showMenadzer("add", stav.id);
	
	}
	
	public static void edit(StavkaJelovnika stavkaJelovnika, String opis, String jelo, Double cena)
	{
		stavkaJelovnika.jelo.opisJela=opis;
		stavkaJelovnika.jelo.nazivJela=jelo;
		stavkaJelovnika.jelo.save();
		
		stavkaJelovnika.cena=cena;
		
		stavkaJelovnika.save();
		showMenadzer("edit", stavkaJelovnika.id);

	}
	
	public static void delete(Long id)
	{
		StavkaJelovnika stavkaJelovnika = StavkaJelovnika.findById(id);
		stavkaJelovnika.delete();
		showMenadzer("edit",stavkaJelovnika.id-1);
	}
	
}






