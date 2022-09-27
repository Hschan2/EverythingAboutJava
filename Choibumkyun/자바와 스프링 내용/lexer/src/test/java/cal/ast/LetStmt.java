package cal.ast;

import cal.parser.Exp;
import cal.parser.Stmt;

public class LetStmt implements Stmt {
    private IdExp idExp;
    private Exp exp;

    public LetStmt(IdExp idExp, Exp exp) {
        this.idExp = idExp;
        this.exp = exp;
    }

    public IdExp getIdExp() {
        return idExp;
    }

    public Exp getExp() {
        return exp;
    }
}
