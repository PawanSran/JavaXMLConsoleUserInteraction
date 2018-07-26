package com.goscale;

import java.util.List;
import java.util.Map;

public class Question {

    private Integer id;
    private String statement;
    private String type;
    private String positiveAction;
    private String negativeAction;

    Map<Integer ,Option> options;

    public Question(Integer id, String statement, String type, String positiveAction, String negativeAction) {
        this.id = id;
        this.statement = statement;
        this.type = type;
        this.positiveAction = positiveAction;
        this.negativeAction = negativeAction;
        this.options = null;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPositiveAction() {
        return positiveAction;
    }

    public void setPositiveAction(String positiveAction) {
        this.positiveAction = positiveAction;
    }

    public String getNegativeAction() {
        return negativeAction;
    }

    public void setNegativeAction(String negativeAction) {
        this.negativeAction = negativeAction;
    }

    public Map<Integer ,Option> getOptions() {
        return options;
    }

    public void setOptions(Map<Integer ,Option> options) {
        this.options = options;
    }
}
