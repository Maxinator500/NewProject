import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User implements Identifiable, Validatable {
    private List<Poll> polls;
    private List<Vote> votes;
    final private long id;
    private String email;
    private String fullName;
    final private long createdAt;

    public User(long createdAt, String email, String fullName) {
        polls = new ArrayList<>();
        votes = new ArrayList<>();
        this.id = FieldsValidator.nextId();
        this.createdAt = createdAt;
        this.email = email;
        this.fullName = fullName;
        isGoodInput();
    }

    public void isGoodInput() {
        FieldsValidator.isUserValid(email, fullName, createdAt, System.currentTimeMillis());
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public void addPoll(Poll poll) {
        polls.add(poll);
    }

    public void vote() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите опрос:");
        for (int i = 0; i < polls.size(); i++) {
            System.out.println((i + 1) + ". " + polls.get(i).getQuestion());
        }

        int pollIndex = scanner.nextInt() - 1;
        Poll selectedPoll = polls.get(pollIndex);

        System.out.println("Отдайте ваш голос:");
        for (int i = 0; i < selectedPoll.getOptions().size(); i++) {
            System.out.println((i + 1) + ". " + selectedPoll.getOptions().get(i).getText());
        }

        int optionIndex = scanner.nextInt() - 1;
        AnswerOption selectedOption = selectedPoll.getOptions().get(optionIndex);

        Vote existingVote = findVoteByUserAndPoll(this, selectedPoll);
        if (existingVote != null) {
            votes.remove(existingVote);
        }

        Vote newVote = new Vote(this, selectedPoll, selectedOption);
        votes.add(newVote);

        System.out.println("Ваш голос учтен!");
    }

    private Vote findVoteByUserAndPoll(User user, Poll poll) {
        for (Vote vote : votes) {
            if (vote.getUser().equals(user) && vote.getPoll().equals(poll)) {
                return vote;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createdAt=" + createdAt + '\'' +
                '}';
    }
}
