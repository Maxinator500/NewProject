package entities;

import contracts.Identifiable;
import contracts.Validatable;
import validation.FieldsValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poll implements Identifiable, Validatable {
    final private long id;
    private final long pollId;
    private LocalDateTime createdAt;
    private String question;
    private List<AnswerOption> options;
    private boolean active;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String pollHash;

    enum StatusEnum {
        CREATED, FINISHED, CANCELLED
    }

    public String getPollHash() {
        return pollHash;
    }

    public Poll(long pollId, LocalDateTime createdAt, String question, List<AnswerOption> options) {
        this.pollId = pollId;
        this.id = FieldsValidator.nextId();
        this.createdAt = createdAt;
        this.question = question;
        this.options = options;
        this.active = true;
        this.pollHash = hashPassword(question);
        isGoodInput();
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }


    //Надо ли валидаторы?
    public void isGoodInput() {
        //validation.FieldsValidator.isPollValid();
    }

    public void cancelPoll() {
        active = false;
    }

    public void addOption(AnswerOption option) {
        options.add(option);
    }

    public String getQuestion() {
        return question;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

    public long getId() {
        return id;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private String hashPassword(String question){
        return String.valueOf(question.hashCode() * 31);
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