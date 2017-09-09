package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Ponuda;
import models.Restoran;

import org.joda.time.format.DateTimeFormat;

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
	
	public static void novePonude(String mode, Long selectedIndex)
	{
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
/*		LocalDate danasnjiDatum = LocalDate.now();
		org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		String formattedDate = formatter.print(d);
	*/	
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
/*				danasnjiDatum.format(dtf);
				String[] parts = (danasnjiDatum.toString("yyyy-MM-dd")).split("-");
				System.out.println(parts);
*/				if(1<2){
//					iznad u ifu ide poredjenje dva datuma, a iznad ifa je parsiranje da bih mogao porediti datume 
//					
					listaPonudaZaPrikaz.add(ponude.get(i));
				}
			}
		}
		
		render(listaPonudaZaPrikaz, mode, selectedIndex);
	}
	
	public static void prihvati(String mode, Ponuda ponuda, Long selectedIndex,String stavkaPonude, String restoran, String prihvaceno)
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
	
	public static void odbij(String mode, Ponuda ponuda, Long selectedIndex,String stavkaPonude, String restoran, String prihvaceno)
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
