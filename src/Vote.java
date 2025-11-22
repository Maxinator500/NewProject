public class Vote {
    private User user;
    private Poll poll;
    private AnswerOption option;

    public Vote(User user, Poll poll, AnswerOption option) {
        this.user = user;
        this.poll = poll;
        this.option = option;
    }

    public User getUser() {
        return user;
    }

    public Poll getPoll() {
        return poll;
    }

    public AnswerOption getOption() {
        return option;
    }
}
