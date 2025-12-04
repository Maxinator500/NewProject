package entities;

import contracts.Identifiable;
import contracts.Validatable;
import validation.FieldsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.*;

@ToString(of = {"id", "email"})
public class User implements Identifiable, Validatable {
    private List<Poll> polls = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();

    @Getter
    final private long id = FieldsValidator.nextId();;

    @Setter
    @Getter
    private String email;

    @Getter
    @Setter
    private String passwordHash;

    public User(String passwordHash, String email) {
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public String hashPassword(String password){
        FieldsValidator.isPasswordValid(password);
        passwordHash = String.valueOf(password.hashCode() * 31);
        return passwordHash;
    }

    public boolean checkPassword(String password){
        return this.passwordHash.equals(hashPassword(password));
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
    public void isGoodInput() {

    }
}
