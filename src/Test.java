import java.util.Scanner;


public class Test {

	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static float pouzeCisla(Scanner sc) 
	{
		float cislo = 0;
		try
		{
			cislo = sc.nextFloat();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cislo ");
			sc.nextLine();
			cislo = pouzeCisla(sc);
		}
		return cislo;
	}

	public static void main(String[] args) {	
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Databaze mojeDatabaze=new Databaze();
		float prumer;
		int volba;
		char a;
		int ID;
		boolean run=true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vlozeni noveho Stroja");
			System.out.println("2 .. nastaveni prumeru Stroja");
			System.out.println("3 .. vypis informace o Strojovi");
			System.out.println("4 .. odstraneni Stroja ");
			System.out.println("5 .. vypis Stroju ");
			System.out.println("6 .. setrideni databaze dle ID ");
			System.out.println("7 .. nalezeni nejhorsiho prumeru ");
			System.out.println("8 .. ukonceni aplikace");
			
			volba=pouzeCelaCisla(sc);
			switch(volba)
			{
				case 1:
					System.out.println("Zadejte jedinecne ID a jmeno Stroja");
					ID=Test.pouzeCelaCisla(sc);
					a=sc.next().charAt(0);
					if (!mojeDatabaze.setStroj(ID,a))
						System.out.println("Stroja nebylo mozno do databaze zadat");
					break;
				case 2:
					System.out.println("Zadejte ID a prumer Stroja");
					ID=pouzeCelaCisla(sc);
					prumer = pouzeCisla(sc);
					if (!mojeDatabaze.setPrumer(ID,prumer))
						System.out.println("Prumer nezadan");
					
					break;
				case 3:
					System.out.println("Zadejte ID Stroja");
					ID=pouzeCelaCisla(sc);
					Stroj info=mojeDatabaze.getStroj(ID);
					if (info!=null)
						System.out.println("Jmeno: " + " prumer: " + info.getStudijniPrumer());
					else
						System.out.println("Vybrana polozka neexistuje");
					break;
				case 4:
					System.out.println("Zadejte ID Stroja k odstraneni");
					ID=pouzeCelaCisla(sc);
					if (mojeDatabaze.vymazStroja(ID))
						System.out.println(ID + " odstranen ");
					else
						System.out.println(ID + " neni v databazi ");
					break;
				case 5:
					mojeDatabaze.vypisDatabaze();
					break;
				case 6:
					mojeDatabaze.setridDatabazi();
					break;
				case 7:
					System.out.println("nejhorsi prumer je " + mojeDatabaze.zjistiNejhorsiPrumer());
					break;
				case 8:
					run=false;
					break;
			}
			
		}
	}

}
