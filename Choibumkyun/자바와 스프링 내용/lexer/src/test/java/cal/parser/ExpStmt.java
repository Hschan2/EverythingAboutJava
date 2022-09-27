package cal.parser;

public class ExpStmt implements Stmt {
    private Exp exp;

    public ExpStmt(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }
}
