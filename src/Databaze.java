import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class Databaze {
	Databaze()
	{
		prvkyDatabaze=new ArrayList<Stroj>();
	}
		
	public boolean setStroj(int ID, char a)
	{
		return	prvkyDatabaze.add(new Stroj(ID,a));
		
	}
	
	public Stroj getStroj(int ID) 
	{
		return prvkyDatabaze.get(ID);
	}
	
	public boolean setPrumer(int ID, float prumer)
	{
		for (Stroj stud:prvkyDatabaze)
		{
			if(stud.getID() == ID)
			{
				stud.setStudijniPrumer(prumer);
				return true;
			}	
		}
		return false;

	}
	
	public boolean vymazStroja(int ID)
	{
		Iterator<Stroj> iter= prvkyDatabaze.iterator();
		while(iter.hasNext()){
			Stroj stud = iter.next();
			if(stud.getID() == ID){
				iter.remove();
				return true;
			}
		}
		System.out.println("Stroj nenalezen");
		return false;
	

		
	}
	
	
	public void setridDatabazi(){
		
		Collections.sort(prvkyDatabaze);
		System.out.println("Databaze setridena");
	
	}
	public float zjistiNejhorsiPrumer()
	{

		float nejhorsi = 0;
		
		for (Stroj stud:prvkyDatabaze)
		{
			if(stud.getStudijniPrumer() > nejhorsi)
				nejhorsi = stud.getStudijniPrumer();
		}
		
		return nejhorsi;

	}
	public void vypisDatabaze()
	{
		for (Stroj mujStroj:prvkyDatabaze)
			System.out.println("ID: " + mujStroj.getID() + " studijni prumer: " + mujStroj.getStudijniPrumer());
	}
	private List<Stroj>  prvkyDatabaze;
}