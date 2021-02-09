import java.util.ArrayList;
import java.util.List;
public class Polynomial{
    List <Monomial> polinom= new ArrayList<Monomial>();

    public Polynomial () { }
    
//______________________adunare_______________________
    
    public Polynomial sum(Polynomial b) {
    	Polynomial rez= new Polynomial();
        Monomial aux=new Monomial(0, 0);
        Monomial x=new Monomial(0, 0);
        Monomial y=new Monomial(0, 0);
        
        int n = this.polinom.size();
        int m = b.polinom.size();

        int i=0;
        int j=0;
        while(i < n && j < m)
        {
            x=this.polinom.get(i);
            y=b.polinom.get(j);
            if (x.getExponent() > y.getExponent()) {
            	rez.polinom.add(x);
            	i++;
            }
            else {
                if (x.getExponent() < y.getExponent()) {
                	rez.polinom.add(y);
                	j++;
                }
                else {
                	aux=aux.aduna(x,  y);
                	rez.polinom.add(aux);
                	i++;
                	j++;
                }
            }
        }
        
        while(i < n) {
        	x=this.polinom.get(i);
            rez.polinom.add(x);
            i++;
        }
        while (j < m)
        {
        	y=b.polinom.get(j);
        	rez.polinom.add(y);
        	j++;
        }
        
        return rez;
        
	}
    
 //__________________________scadere____________________________________
    
    public Polynomial dif(Polynomial b) {
    	Polynomial rez= new Polynomial();
        Monomial aux=new Monomial(0, 0);
        Monomial x=new Monomial(0, 0);
        Monomial y=new Monomial(0, 0);
        
        int n = this.polinom.size();
        int m = b.polinom.size();

        int i=0;
        int j=0;
        while(i < n && j < m)
        {
            x=this.polinom.get(i);
            y=b.polinom.get(j);
            if (x.getExponent() > y.getExponent()) {
            	rez.polinom.add(x);
            	i++;
            }
            else {
                if (x.getExponent() < y.getExponent()) {
                	y.coeficient=-y.getCoeficient();
                	rez.polinom.add(y);
                	j++;
                }
                else {
                	aux=aux.scade(x,  y);
                	rez.polinom.add(aux);
                	i++;
                	j++;
                }
            }
        }
        
        while(i < n) {
        	x=this.polinom.get(i);
            rez.polinom.add(x);
            i++;
        }
        while (j < m)
        {
        	y=b.polinom.get(j);
        	y.coeficient=-y.getCoeficient();
        	rez.polinom.add(y);
        	j++;
        }
        
        return rez;
        
	}
    
//________________inmultire___________________________________
    public Polynomial prod(Polynomial b) {
        Monomial aux=new Monomial(0, 0);
        Polynomial p2 =new Polynomial();
        /*
        Monomial x=new Monomial(0, 0);
        Monomial y=new Monomial(0, 0);
        
        int n = this.polinom.size();
        int m = b.polinom.size();
        
        for (int i=0; i<n; i++)
        {
        	x=this.polinom.get(i);
        	Polynomial p1 =new Polynomial();
        	for (int j=0; j<m; j++)
        	{
        		y=b.polinom.get(j);
        		aux=aux.inmulteste(x,y);
        		p1.polinom.add(aux);
        	}
        	p2=p2.sum(p1);
        }
        */
        for (Monomial i:this.polinom)
        {
        	Polynomial p1 =new Polynomial();
        	for (Monomial j:b.polinom)
        	{
        		aux=aux.inmulteste(i,j);
        		p1.polinom.add(aux);
        	}
        	p2=p2.sum(p1);
        }
        return p2;
        
	}
    
	public String toString() {
		String s = "";
		for(int i = 0; i < polinom.size(); i++)
			s += polinom.get(i).toString();
		return s;
	}
}