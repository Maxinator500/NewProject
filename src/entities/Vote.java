package entities;

import validation.FieldsValidator;
import java.time.LocalDateTime;

public class Vote {
    private User user;
    private Poll poll;
    private AnswerOption option;
    private LocalDateTime voteDate;
    private Long id;
    private StatusEnum status;

    enum StatusEnum {
        CREATED, FINISHED, CANCELLED
    }

    public Vote(User user, Poll poll, AnswerOption option) {
        this.user = user;
        this.poll = poll;
        this.option = option;
        this.voteDate = LocalDateTime.now();
        this.id = FieldsValidator.nextId();
    }

    public User getUser() {
        return user;
    }

    public StatusEnum getStatus() {
        return status;
    }


    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Poll getPoll() {
        return poll;
    }

    public AnswerOption getOption() {
        return option;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getVoteDate() {
        return voteDate;
    }
}
