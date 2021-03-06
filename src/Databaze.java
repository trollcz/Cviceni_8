import java.util.*;
import java.io.*;



public class Databaze {
	Databaze()
	{
		prvkyDatabaze=new LinkedList<Stroj>();
	}
		
	public boolean setStroj(int ID, char a)
	{
        if (a=='c'){
            if (!cstroj()){
                return	prvkyDatabaze.add(new Stroj(ID,a));
            }
        }else{
            return	prvkyDatabaze.add(new Stroj(ID,a));
        }
        return false;
	}
	
    public boolean cstroj(){
	    for (Stroj str:prvkyDatabaze){
	        if (str.getStroj()=='c'){
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
				odebraniprace();
				return true;
			}
		}

		System.out.println("Stroj nenalezen");
		return false;
	}
    public int max_kapacita(){
	    int maxkapacita=0;
        for (Stroj str:prvkyDatabaze) {
            maxkapacita+=str.getKapacita();
        }
        return maxkapacita;
    }
    public int max_kapacita_sroubky(){
        int maxkapacita=0;
        for (Stroj str:prvkyDatabaze) {
            if (str.getStroj()=='b')
            maxkapacita+=str.getKapacita();
        }
        return maxkapacita;
    }

    public boolean setPracepodlozky(int pracepodlozky) {
        if(pracepodlozky<=max_kapacita()) {
            this.pracepodlozky = pracepodlozky;
            odebraniprace();
            return true;
        }
        return false;
    }

    public boolean setPracesroubky(int pracesroubky) {
        if(pracesroubky<=max_kapacita_sroubky()) {
            this.pracesroubky = pracesroubky;
            odebraniprace();
            return true;
        }
        return false;
    }
    public boolean setporucha(int ID){
        for (Stroj stroj:prvkyDatabaze){
            if(stroj.getID()==ID){
                if(stroj.getStroj()!='a'){
                    stroj.setPorucha();
                    odebraniprace();
                    stroj.setAkt_kapacita(0);
                    return true;
                }else{
                    System.out.println("Stroj A se nemuze porouchat");
                }
            }
        }
        return false;
    }
    public boolean setoprava(int ID){
        for (Stroj stroj:prvkyDatabaze){
            if(stroj.getID()==ID){
                stroj.setPorucha();
                return true;
            }
        }
        return false;
    }

    public void setridDatabazi(boolean ses){
        if (ses)
		Collections.sort(prvkyDatabaze);
        else
        Collections.reverse(prvkyDatabaze);
	}
	public int spotrebatov(){
        int a=0,b=0,c=0,spota=0,spotb=0,spotc=0;
        for(Stroj stroj:prvkyDatabaze){
            if (stroj.getStroj()=='a') {
                a += stroj.getAkt_kapacita();
                spota=stroj.getSpotreba();
            }else{
                if (stroj.getStroj() == 'b') {
                    b += stroj.getAkt_kapacita();
                    spotb=stroj.getSpotreba();
                }else{
                    c += stroj.getAkt_kapacita();
                    spotc=stroj.getSpotreba();
                }
            }
        }
        return a*spota+b*spotb+c*spotc;
    }

	public void vypisDatabaze()
	{
		for (Stroj mujStroj:prvkyDatabaze)
			System.out.println("ID: " + mujStroj.getID() + " stroj: " + mujStroj.getStroj() + " Pouzivany: "+ mujStroj.getAkt_kapacita()+ " max " + mujStroj.getKapacita() + " Porouchan: " + mujStroj.isPorucha());
	}
   /* public void prideleniprace(){
        //zde bude logika pri pridelovani prace
        int i=0;
        int polozky=0;
        boolean ses=true;
        //plni se nez se doplnej
        setridDatabazi(ses);
        for (Stroj str:prvkyDatabaze ){
            while (str.getAkt_kapacita()<str.getKapacita() && polozky<pracepodlozky && !str.isPorucha()){
                i++;
                polozky++;
                str.setAkt_kapacita(i);
            }
            i=0;
            if(polozky==pracepodlozky)break;
        }
    }
*/
    public void odebraniprace(){
        //zde bude logika pri odebirani prace
        boolean ses=false;
        //plni se nez se doplnej
        setridDatabazi(ses);
        for (Stroj str:prvkyDatabaze ){
            str.setAkt_kapacita(0);
        }
        int i=0;
        int polozky=0;
        ses=true;
        //plni se nez se doplnej
        setridDatabazi(ses);
        for (Stroj str:prvkyDatabaze){
            while (str.getAkt_kapacita()<str.getKapacita() && polozky<pracesroubky && !str.isPorucha() && str.getStroj()=='b'){
                i++;
                polozky++;
                str.setAkt_kapacita(i);
            }
            i=0;
            if(polozky==pracesroubky)break;
        }
        polozky=0;
        i=0;
        if (max_kapacita()==pracepodlozky) pracepodlozky-=pracesroubky;
        for (Stroj str:prvkyDatabaze ){
            while (str.getAkt_kapacita()<str.getKapacita() && polozky<pracepodlozky && !str.isPorucha()){
                i++;
                polozky++;
                str.setAkt_kapacita(i);
            }
            i=0;
            if(polozky==pracepodlozky)break;
        }
    }
    public boolean loadDat(String jmenoDB) throws NumberFormatException{
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(jmenoDB);
            br = new BufferedReader(fr);
            while (fr!=null){
                String row = br.readLine();
                if(row == null){break;}
                String[]words = row.split(" ");
                prvkyDatabaze.add(new Stroj(Integer.parseInt(words[0]),words[1].charAt(0)));
                pracepodlozky=Integer.parseInt(words[2]);
                pracesroubky=Integer.parseInt(words[3]);
                if(Integer.parseInt(words[4])==1)
                prvkyDatabaze.getLast().setPorucha();
            }
            odebraniprace();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return false;

    }

    public boolean saveDat(String jmenoDB){
        int porucha=0;
        try {
            FileWriter fw = new FileWriter(jmenoDB);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Stroj str:prvkyDatabaze){
                if(str.isPorucha())
                    porucha=1;
                else
                    porucha=0;

                bw.write(str.getID() + " " + str.getStroj() + " "  + pracepodlozky + " " + pracesroubky  + " " + porucha);
                bw.newLine();
            }
            bw.close();
            fw.close();


        }catch (IOException e){
        }
        return false;
    }

    private LinkedList<Stroj>  prvkyDatabaze;
    private int pracesroubky;
    private int pracepodlozky;
    }