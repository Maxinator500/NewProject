package entities;

import contracts.Identifiable;
import contracts.Validatable;
import validation.FieldsValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.*;

@RequiredArgsConstructor
@Getter
public class Poll implements Identifiable, Validatable {
    final private long pollId;
    final private LocalDateTime createdAt;
    final private String question;
    final private List<AnswerOption> options;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long id = FieldsValidator.nextId();
    private boolean active = true;
    private String pollHash;

    public void isGoodInput() {
        //validation.FieldsValidator.isPollValid();
        return;
    }

    public void cancelPoll() {
        active = false;
    }

    public void addOption(AnswerOption option) {
        options.add(option);
    }

    private void hashPassword(){
        pollHash = String.valueOf(question.hashCode() * 31);
        return;
    }

    public Map<Integer, Long> showOptions() {
        System.out.println("===" + question + "===");
        Map<Integer, Long> optionsMap = new HashMap<>();
        int numb = 1,temp = 0;
        for (AnswerOption option : options) {
            temp = numb++;

            optionsMap.put(temp, option.getId());
            System.out.println(temp + ": " + option.getValue());
        }

        return optionsMap;
    }
}