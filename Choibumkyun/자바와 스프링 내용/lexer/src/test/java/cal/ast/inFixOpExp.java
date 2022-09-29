package cal.ast;

import cal.parser.Exp;

public class inFixOpExp implements Exp {
    private String operator;
    private Exp left;
    private Exp right;

    public inFixOpExp(String operator, Exp left, Exp right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public Exp getLeft() {
        return left;
    }

    public Exp getRight() {
        return right;
    }

    @Override
    public String DebugString() {
        return String.format("(%s %s %s)", left.DebugString(), operator, right.DebugString());
    }
}
