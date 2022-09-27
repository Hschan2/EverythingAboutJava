package cal.parser;

public class Program implements Node {
    private Stmt[] state;

    public Program(Stmt[] state) {
        this.state = state;
    }

    public Stmt[] getState() {
        return state;
    }
}
