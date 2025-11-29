import entities.*;
import validation.FieldsValidator;

public class Main {
    public static void main(String[] args) {
       User dummyUser = new User( "111111", "abab@mail.ru");
       User User1 = new User( "1112222", "abak@mail.ru");
       UserService userService = new UserService();

  //     userService.registrate(dummyUser);
  //     userService.registrate(User1);
//        userService.readUsersfromSCV();
//
 //     User fUSer = userService.authentificate("abab@mail.ru","111111");
//        userService.readUsersfromSCV();

     //  AuthenticationMenu authenticationMenu = new AuthenticationMenu();
     //  User currentUser = authenticationMenu.showMenu();
        User currentUser = dummyUser;

       PollService pollService = new PollService();
       VotingService voteSrvice = new VotingService();

       if (currentUser != null){
           MainMenu mainMenu = new MainMenu(currentUser,pollService,voteSrvice);
           mainMenu.showfinishedPolls();
            //mainMenu.show();
       }


    }
}
