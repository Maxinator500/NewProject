package entities;

import lombok.*;
import validation.FieldsValidator;

@AllArgsConstructor
@Getter
public class AnswerOption {
    private Long id;
    private String value;
}
