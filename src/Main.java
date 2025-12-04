import entities.*;
import services.PollService;
import services.UserService;
import services.VotingService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User dummyUser = new User("111111", "abab@mail.ru");
        User User1 = new User("1112222", "abak@mail.ru");
        UserService userService = new UserService();


        //AuthenticationMenu authenticationMenu = new AuthenticationMenu();
        //dummyUser = authenticationMenu.showMenu();

        User currentUser = dummyUser;

        PollService pollService = new PollService();
        VotingService voteService = new VotingService();

        if (currentUser != null) {
            MainMenu mainMenu = new MainMenu(currentUser, pollService, voteService);
            mainMenu.show();
        }
    }
}
