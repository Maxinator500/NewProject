package entities;

import validation.FieldsValidator;

public class AnswerOption {
    private String value;
    private Long id;

    public AnswerOption(Long id, String value) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }
}
