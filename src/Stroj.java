import java.util.Comparator;

public class Stroj implements Comparable{
	
	@Override
	public int compareTo(Object dalsi){
		Stroj stud = (Stroj) dalsi;
		
		return Integer.compare(this.ID, stud.ID);
	}
	
		
	public static Comparator<Stroj> StrojComparator = null;
	
	
	public Stroj(int ID, char a)
    {
		this.ID=ID;
		switch (a){
            case 'b':
                this.spotreba=3;
                this.kapacita=50;
                this.sku_pracepodlozky=0;
                this.sku_pracesroubky=0;
                stroj=a;
                break;
            case 'c':
                if (!iscstroj){
                    this.spotreba=2;
                    this.kapacita=150;
                    this.sku_pracepodlozky=0;
                    iscstroj=true;
                    stroj=a;
                }else{
                    System.out.println("Stroj C muze byt jen 1");
                }
                break;
            default:
                this.spotreba=4;
                this.kapacita=70;
                this.sku_pracepodlozky=0;
                stroj=a;
                break;
        }
	}

    public void setPracepodlozky(int pracepodlozky) {
        this.pracepodlozky = pracepodlozky;
    }

    public void setPracesroubky(int pracesroubky) {
        this.pracesroubky = pracesroubky;
    }

    /*public String getJmeno()
            {
                return jmeno;
            }
            */
	public int getID()
	{
		return ID;
	}
	
	public float getStudijniPrumer() {
		return studijniPrumer;
	}

	public boolean setStudijniPrumer(float studijniPrumer) {
		if (studijniPrumer<1||studijniPrumer>5)
		{
			System.out.println("Chybny prumer");
			return false;
		}
		this.studijniPrumer = studijniPrumer;
		return true;
	}
	
	private int ID;
	private String jmeno;
	private float studijniPrumer;
	private int spotreba;
	private int kapacita;
	private char stroj;
	public boolean iscstroj=false;
    private int pracesroubky;
    private int pracepodlozky;
    private int sku_pracesroubky;
    private int sku_pracepodlozky;
	
}