import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rejex {
	
	public Rejex() {
		
	}
	
	public Polynomial rez(String text) {
		String monomialFormat = "([+-]?[\\d\\.]*[a-zA-Z]?\\^?\\d*)";
    	String monomialPartsFormat = "([+-]?[\\d\\.]*)([a-zA-Z]?)\\^?(\\d*)";
        Pattern p1 = Pattern.compile(monomialFormat);
        Matcher m1 = p1.matcher(text);
        Polynomial a = new Polynomial();
        while (!m1.hitEnd()) {
            m1.find();
            Pattern p2 = Pattern.compile(monomialPartsFormat);
            Matcher m2 = p2.matcher(m1.group());
            double coefficient = 0;
            double exponent = 0;
            if (m2.find()) {
                coefficient = Double.valueOf(m2.group(1));
                exponent = Double.valueOf(m2.group(3));
                }
                Monomial b = new Monomial(coefficient, exponent);
                a.polinom.add(b);
            }
        return a;
	}
}