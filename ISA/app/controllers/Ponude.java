package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Ponuda;
import models.Restoran;
import play.mvc.Controller;

public class Ponude extends Controller{

	public static void show(String mode, Long selectedIndex)
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Ponuda> ponude = Ponuda.findAll();
		List<Ponuda> listaPonudaZaPrikaz = new ArrayList<>();
//		List<Restoran> restoran = Restoran.findAll();
		
		for(int i=0; i<ponude.size(); i++)
		{
		//	if(ponude.get(i).restoran.equals(session.get("restoran")))
		//	{
				listaPonudaZaPrikaz.add(ponude.get(i));
		//	}
		}
		
		if(mode == null || mode.equals(""))
			mode = "edit";
		
		render(listaPonudaZaPrikaz, mode, selectedIndex);
	}
	
	public static void novePonude(String mode, Long selectedIndex) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String danasnjiDatum = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String[] parts1 = danasnjiDatum.split("-");
		int godina1 = Integer.parseInt(parts1[0]);
		int mesec1 = Integer.parseInt(parts1[1]);
		int dan1 = Integer.parseInt(parts1[2]);
		Date dateDanas = format.parse(dan1+"/"+mesec1+"/"+godina1);

		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		List<Ponuda> ponude = Ponuda.findAll();
		List<Ponuda> listaPonudaZaPrikaz = new ArrayList<>();
		
		for(int i=0; i<ponude.size(); i++)
		{
			if(ponude.get(i).restoran.nazivRestorana.equals(session.get("restoran"))) 
			{
				String datumRokaPonude = ponude.get(i).rokPonude;

				String[] parts2 = datumRokaPonude.split("-");
				int godina2 = Integer.parseInt(parts2[0]);
				int mesec2 = Integer.parseInt(parts2[1]);
				int dan2 = Integer.parseInt(parts2[2]);
				Date dateRok = format.parse(dan2+"/"+mesec2+"/"+godina2);
				
				if( dateDanas.before(dateRok) ){
					listaPonudaZaPrikaz.add(ponude.get(i));
				}
				
			}
		}
		
		render(listaPonudaZaPrikaz, mode, selectedIndex);
	}
	
	public static void prihvati(String mode, Ponuda ponuda, Long selectedIndex,String stavkaPonude, String restoran, String prihvaceno) throws ParseException
	{
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran2 = new Restoran();
		for(int i=0 ;i< restorani.size();i++){
			if(restorani.get(i).nazivRestorana.equals(restoran))
				restoran2=restorani.get(i);
		}
		
		ponuda.restoran=restoran2;
		ponuda.stavkaPonude=stavkaPonude;
		ponuda.prihvaceno="da";
		
		ponuda.save();
		mode="novePonude";
		novePonude("novePonude",ponuda.id);
	}
	
	public static void odbij(String mode, Ponuda ponuda, Long selectedIndex,String stavkaPonude, String restoran, String prihvaceno) throws ParseException
	{
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran2 = new Restoran();
		for(int i=0 ;i< restorani.size();i++){
			if(restorani.get(i).nazivRestorana.equals(restoran))
				restoran2=restorani.get(i);
		}
		
		ponuda.restoran=restoran2;
		ponuda.stavkaPonude=stavkaPonude;
		
		if(ponuda.prihvaceno.equals("da")){
			ponuda.prihvaceno="da";

			ponuda.save();
			mode="novePonude";
			novePonude("novePonude",ponuda.id);			
		}else{
			ponuda.prihvaceno="ne";

			ponuda.save();
			mode="novePonude";
			novePonude("novePonude",ponuda.id);
		}
	}
	
	public static void posalji(String stavkaPonude, String restoran, String prihvaceno, String kolicina, String cena, String rokPonude)
	{
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran2 = new Restoran();
		for(int i=0 ;i< restorani.size();i++){
			if(restorani.get(i).nazivRestorana.equals(restoran))
				restoran2=restorani.get(i);
		}
		
		Ponuda pon=new Ponuda(stavkaPonude, restoran2, "neodgovoreno", kolicina, cena, rokPonude);
		pon.save();
		show("add",pon.id);
	}
	
	public static void edit(Ponuda ponuda,String stavkaPonude, String restoran, String prihvaceno)
	{
		List<Restoran> restorani = Restoran.findAll();
		Restoran restoran2 = new Restoran();
		for(int i=0 ;i< restorani.size();i++){
			if(restorani.get(i).nazivRestorana.equals(restoran))
				restoran2=restorani.get(i);
		}
		
		if(ponuda.prihvaceno.equals("da") || ponuda.prihvaceno.equals("ne")){
			show("add",ponuda.id);
		}else{
			ponuda.restoran=restoran2;
			ponuda.stavkaPonude=stavkaPonude;
			ponuda.save();
			show("add",ponuda.id);
		}
	}
}
