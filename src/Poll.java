import java.util.ArrayList;
import java.util.List;

public class Poll implements Identifiable, Validatable {
    final private long id, creatorId, pollId, createdAt;
    private String question;
    private List<AnswerOption> options;

    enum StatusEnum {
        CREATED, FINISHED, CANCELLED
    }

    private Poll.StatusEnum status = Poll.StatusEnum.CREATED;

    public Poll(long pollId, long creatorId, long createdAt, String question) {
        this.id = FieldsValidator.nextId();
        this.creatorId = creatorId;
        this.pollId = pollId;
        this.createdAt = createdAt;
        this.question = question;
        this.options = new ArrayList<>();
        isGoodInput();
    }

    //Надо ли валидаторы?
    public void isGoodInput() {
        //FieldsValidator.isPollValid();
    }

    public void cancelPoll() {
        this.status = Poll.StatusEnum.CANCELLED;
    }

    public boolean isFinished() {
        return status.ordinal() == 1;
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

    public long getCreatorId() {
        return creatorId;
    }

    public long getPollId() {
        return pollId;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}