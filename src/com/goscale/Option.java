package com.goscale;

public class Option {

    private int id;
    private String statement;
    private String positiveAction;

    public Option(int id, String statement, String positiveAction) {
        this.id = id;
        this.statement = statement;
        this.positiveAction = positiveAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getPositiveAction() {
        return positiveAction;
    }

    public void setPositiveAction(String positiveAction) {
        this.positiveAction = positiveAction;
    }
}

