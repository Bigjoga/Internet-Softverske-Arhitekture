package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Korisnik;
import models.Pice;
import models.Ponuda;
import models.Restoran;
import models.Rezervacija;
import play.mvc.Controller;

public class Ponude extends Controller{

	public static void show(String mode, Long selectedIndex) throws ParseException
	{
		if(session.isEmpty())
		{
			redirect("http://localhost:9000/logovanje/show");
		}
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String danasnjiDatum = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String[] parts1 = danasnjiDatum.split("-");
		int godina1 = Integer.parseInt(parts1[0]);
		int mesec1 = Integer.parseInt(parts1[1]);
		int dan1 = Integer.parseInt(parts1[2]);
		Date dateDanas = format.parse(dan1+"/"+mesec1+"/"+godina1);
		
		List<Ponuda> ponude = Ponuda.findAll();
		List<Ponuda> listaPonudaZaPrikaz = new ArrayList<>();

		
	//	-----------
		List<Restoran> restorann = Restoran.findAll();
		List<Restoran> restoran = new ArrayList<>();
		
		for(int i=0; i<restorann.size(); i++)
		{
				restoran.add(restorann.get(i));
		}
	//-----------	
		
		for(int i=0; i<ponude.size(); i++)
		{
			String[] parts2 = ponude.get(i).rokIsporuke.split("-");
			int godina2 = Integer.parseInt(parts2[0]);
			int mesec2 = Integer.parseInt(parts2[1]);
			int dan2 = Integer.parseInt(parts2[2]);
			Date dateIsporuke = format.parse(dan2+"/"+mesec2+"/"+godina2);
			
			if( (!dateDanas.after(dateIsporuke)) && ponude.get(i).saljePonudu.equals(session.get("ime")) ){
				listaPonudaZaPrikaz.add(ponude.get(i));
			}
		}
		
		if(mode == null || mode.equals(""))
			mode = "posalji";
		
		render(listaPonudaZaPrikaz, restoran, mode, selectedIndex);
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
	
	public static void prihvati(String mode, Ponuda ponuda, Long selectedIndex) throws ParseException
	{
		if( ponuda.prihvaceno.equals("da") ){
			mode="prihvati";
			novePonude(mode,ponuda.id);			
		}else{
			ponuda.prihvaceno="DA";
			ponuda.procitano="NOVI ODGOVOR";
			ponuda.primljeno="da";
			ponuda.save();
			mode="prihvati";
			novePonude(mode,ponuda.id);
		}
	}
	
	public static void odbij(String mode, Ponuda ponuda, Long selectedIndex) throws ParseException
	{		
		if( ponuda.prihvaceno.equals("da") || ponuda.prihvaceno.equals("ne") || ponuda.prihvaceno.equals("DA") || ponuda.prihvaceno.equals("NE") ){
			mode="odbij";
			novePonude(mode,ponuda.id);			
		}else{
			ponuda.prihvaceno="NE";
			ponuda.procitano="NOVI ODGOVOR";
			ponuda.primljeno="da";
			ponuda.save();
			mode="odbij";
			novePonude(mode,ponuda.id);
		}
	}
	
	public static void posalji(Ponuda ponuda, String stavkaPonude, Long restoran, String prihvaceno, String kolicina, String cena, String rokPonude, String rokIsporuke) throws ParseException
	{
		Restoran restoran2 = Restoran.findById(restoran);
		String saljePonudu = session.get("ime");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		String[] parts1 = rokIsporuke.split("-");
		int godina1 = Integer.parseInt(parts1[0]);
		int mesec1 = Integer.parseInt(parts1[1]);
		int dan1 = Integer.parseInt(parts1[2]);
		Date dateIsporuka = format.parse(dan1+"/"+mesec1+"/"+godina1);
		
		String[] parts2 = rokPonude.split("-");
		int godina2 = Integer.parseInt(parts2[0]);
		int mesec2 = Integer.parseInt(parts2[1]);
		int dan2 = Integer.parseInt(parts2[2]);
		Date dateRok = format.parse(dan2+"/"+mesec2+"/"+godina2);
		
		if(dateIsporuka.after(dateRok)){
			Ponuda pon=new Ponuda(stavkaPonude, restoran2, "neodgovoreno", "da", kolicina, cena, rokPonude, saljePonudu, rokIsporuke);
			pon.restoran=restoran2;
			pon.procitano=" ";
			pon.save();
			System.out.println("Kreirana i poslata nova ponuda");
			show("add",pon.id);
		} else {
			System.out.println("***Ponuda NIJE kreirana: rok isporuke ne sme da istice pre roka odgovora na ponudu!");
			show("add",ponuda.id);		
		}
	}

	public static void edit(Ponuda ponuda,String stavkaPonude, Long restoran, String prihvaceno, String kolicina, String cena, String rokPonude, String rokIsporuke) throws ParseException
	{
		Restoran restoran2 = Restoran.findById(restoran);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String danasnjiDatum = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String[] parts1 = danasnjiDatum.split("-");
		int godina1 = Integer.parseInt(parts1[0]);
		int mesec1 = Integer.parseInt(parts1[1]);
		int dan1 = Integer.parseInt(parts1[2]);
		Date dateDanas = format.parse(dan1+"/"+mesec1+"/"+godina1);
		String[] parts2 = rokPonude.split("-");
		int godina2 = Integer.parseInt(parts2[0]);
		int mesec2 = Integer.parseInt(parts2[1]);
		int dan2 = Integer.parseInt(parts2[2]);
		Date dateRok = format.parse(dan2+"/"+mesec2+"/"+godina2);
		
		if( (!dateDanas.before(dateRok)) || ponuda.prihvaceno.equals("da") || ponuda.prihvaceno.equals("ne") || ponuda.prihvaceno.equals("DA") || ponuda.prihvaceno.equals("NE") ){
			show("add",ponuda.id);
		}else{
			ponuda.restoran=restoran2;
			ponuda.stavkaPonude=stavkaPonude;
			ponuda.cena = cena;
			ponuda.kolicina = kolicina;
			ponuda.rokPonude = rokPonude;
			ponuda.rokIsporuke = rokIsporuke;
			ponuda.save();
			show("procitano",ponuda.id);
		}
	}
	
	public static void procitano() throws ParseException {
		
		/////// PROCITANO OBAVESTENJE NE MOZE OVDE DA STOJI!!!
		List<Ponuda> ponudeRename = Ponuda.findAll();
		for(Ponuda pR : ponudeRename)
		{
			
			if(pR.saljePonudu.equals(session.get("ime"))){
				if(pR.prihvaceno.equals("DA"))
				{
					pR.prihvaceno="da";
					pR.procitano=" ";
					pR.save();
				}
				if(pR.prihvaceno.equals("NE"))
				{
					pR.prihvaceno="ne";
					pR.procitano=" ";
					pR.save();
				}
			}
			
		}
		/////// PROCITANO OBAVESTENJE
		show("posalji", null);
	}
	
	public static void primljeno() throws ParseException {
		
		/////// PROCITANO OBAVESTENJE
		List<Ponuda> ponudePrimi = Ponuda.findAll();
		for(Ponuda pP : ponudePrimi)
		{
			
			if(pP.restoran.nazivRestorana.equals(session.get("restoran"))){
				pP.primljeno="da";
				pP.save();
			}
			
		}
		/////// PROCITANO OBAVESTENJE
		novePonude("prihvati", null);
	}
	
}
