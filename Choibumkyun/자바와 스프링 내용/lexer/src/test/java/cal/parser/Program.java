package cal.parser;

public class Program implements Node {
    private Stmt[] stmts;

    public Program(Stmt[] stmts) {
        this.stmts = stmts;
    }

    public Stmt[] getState() {
        return stmts;
    }

    @Override
    public String DebugString() {
        StringBuilder sb = new StringBuilder();
        for (Stmt stmt : stmts) {
            sb.append(stmt.DebugString());
        }
        return sb.toString();
    }
}
