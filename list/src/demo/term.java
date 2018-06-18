package demo;

//一元多项式的项
public class term {
    //系数
    int coef;
    //指数
    int expn;

    public term() {
    }

    public term(int coef, int expn) {
        this.coef = coef;
        this.expn = expn;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public int getExpn() {
        return expn;
    }

    public void setExpn(int expn) {
        this.expn = expn;
    }
}
