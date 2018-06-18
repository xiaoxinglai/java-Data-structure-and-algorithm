package demo;

public class PolynomialTest {
    public static void main(String[] args) {
        Polynomial polynomial=new Polynomial();

        term[] terms=new term[5];
        terms[0]=new term(1,1);
        terms[1]=new term(1,1);
        terms[2]=new term(5,3);
        terms[3]=new term(2,3);
        terms[4]=new term(2,4);
        polynomial.CreatPolyn(terms);
        polynomial.printPolyn();


        Polynomial polynomialB=new Polynomial();

        term[] termsB=new term[5];
        termsB[0]=new term(1,1);
        termsB[1]=new term(3,8);
        termsB[2]=new term(5,3);
        termsB[3]=new term(2,3);
        termsB[4]=new term(2,4);
        polynomialB.CreatPolyn(termsB);
        polynomialB.printPolyn();

        System.out.println("相加之后：");
        polynomial.addPolyn(polynomialB);
        polynomialB.printPolyn();

    }
}
