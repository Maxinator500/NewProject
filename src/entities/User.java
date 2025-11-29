package entities;

import contracts.Identifiable;
import contracts.Validatable;
import validation.FieldsValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User implements Identifiable, Validatable {
    private List<Poll> polls;
    private List<Vote> votes;
    final private Long id;
    private String email;
    private String passwordHash;

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User(String password, String email) {
        polls = new ArrayList<>();
        votes = new ArrayList<>();
        this.id = FieldsValidator.nextId();
        this.email = email;
        this.passwordHash = hashPassword(password);
        isGoodInput();
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String hashPassword(String password){
        FieldsValidator.isPasswordValid(password);
        return String.valueOf(password.hashCode() * 31);
    }

    public boolean checkPassword(String password){
        return this.passwordHash.equals(hashPassword(password));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
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
        return "entities.User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public void isGoodInput() {

    }
}
