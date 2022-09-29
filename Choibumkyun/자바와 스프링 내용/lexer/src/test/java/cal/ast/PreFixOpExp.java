package cal.ast;

import cal.parser.Exp;

public class PreFixOpExp implements Exp {
    private String operator;
    private Exp exp;

    public PreFixOpExp(String operator, Exp exp) {
        this.operator = operator;
        this.exp = exp;
    }

    public String getOperator() {
        return operator;
    }

    public Exp getExp() {
        return exp;
    }

    @Override
    public String DebugString() {
        return String.format("(%s%s).", operator, exp.DebugString());
    }
}
