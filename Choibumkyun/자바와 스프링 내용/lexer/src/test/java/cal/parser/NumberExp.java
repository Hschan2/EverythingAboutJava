package cal.parser;

public class NumberExp implements Exp {
    private String value;

    public NumberExp(String value) {
        this.value = value;
    }

    public double valueAsDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public String DebugString() {
        return value;
    }
}
