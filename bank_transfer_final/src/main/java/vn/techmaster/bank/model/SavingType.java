package vn.techmaster.bank.model;

public enum SavingType {
    EACHTERM("EACH TERM"),

    ENDOFTERM("END OF TERM");

    public final String label;

    private SavingType(String label) {
        this.label = label;
    }
}
