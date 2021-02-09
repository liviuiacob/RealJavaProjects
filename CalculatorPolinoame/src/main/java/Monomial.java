public class Monomial{
    public double coeficient;
    public double exponent;

    public Monomial () {}
    public Monomial(double coeficient, double exponent)
    {
        this.coeficient=coeficient;
        this.exponent=exponent;
    }
    public double getCoeficient()
    {
        return coeficient;
    }
    public double getExponent()
    {
        return exponent;
    }
    public void setExponent(double exponent)
    {
        this.exponent=exponent;
    }
    public void setCoeficient(double coeficient)
    {
        this.coeficient=coeficient;
    }

    public Monomial aduna(Monomial a, Monomial b)
    {
    	Monomial x= new Monomial();
    	x.setCoeficient(a.coeficient + b.coeficient);
    	x.setExponent(a.exponent);
    	return x;
    }
    public Monomial scade (Monomial a, Monomial b){
    	Monomial x= new Monomial();
    	x.setCoeficient(a.coeficient - b.coeficient);
    	x.setExponent(a.exponent);
    	return x;
    }
    public Monomial inmulteste (Monomial a, Monomial b)
    {
    	Monomial x= new Monomial();
    	x.setCoeficient(a.coeficient * b.coeficient);
    	x.setExponent(a.exponent + b.exponent);
    	return x;
    }
    /*
    public Monomial imparte (Monomial a, Monomial b)
    {
        Monomial rez=new Monomial(a.getCoeficient()/b.getCoeficient(), a.getExponent()+b.getExponent());
        return rez;
    }
    */
    public String toString() {
    	if (coeficient >= 0)
    		return "+" + coeficient + "x^" + (int)exponent + " ";
    	else
    		return coeficient + "x^" + (int)exponent + " ";
    		
    }
}