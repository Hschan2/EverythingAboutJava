package cal.ast;

import cal.parser.Exp;

public class IdExp implements Exp {
    private String id;

    public IdExp(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String DebugString() {
        return id;
    }
}
