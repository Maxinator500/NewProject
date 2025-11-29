package entities;

import contracts.Validatable;

import java.util.Scanner;

public class AuthenticationMenu implements Validatable {
    private User currentUser;
    private UserService userService;
    Scanner scanner = new Scanner(System.in);

    public AuthenticationMenu() {
        this.userService = new UserService();
    }

    public User showMenu() {
        System.out.println("\n=== АУТЕНТИФИКАЦИЯ ===");
        System.out.println("1. Войти");
        System.out.println("2. Зарегистрироваться");
        System.out.println("3. Выйти");
        System.out.print("Выберите действие: ");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                currentUser = login();
                return currentUser;
            case "2":
                currentUser = registrate();
                return currentUser;
            case "3":
                System.out.println("Выход!");
                break;
            default:
                System.out.println("Неверный выбор");
                return null;
        }

        return null;
    }

    private User login(){
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            User user = userService.authentificate(login.trim(),password.trim());
            System.out.println("Вы вошли в систему!");
            return user;
        }catch (IllegalArgumentException e){
            System.err.println("Пользователь не найден");
        }
        return null;
    }

    private User registrate(){
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            User user = new User(password.trim(),login.trim());
            userService.registrate(user);
            System.out.println("Вы зарегистрировались!");
            return user;
        }catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void isGoodInput() {

    }
}
