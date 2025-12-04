package entities;

import contracts.Validatable;
import services.UserService;

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
                currentUser = register();
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
            User user = userService.authenticate(login.trim(), password.trim());
            if (user != null) {
                System.out.println("Вы вошли в систему!");
                return user;
            }
        }catch (IllegalArgumentException e){
            System.err.println("Недопустимый логин или пароль");
        }

        System.out.println("Пользователь не найден");
        return null;
    }

    private User register(){
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            User user = new User(currentUser.hashPassword(password), login.trim());
            userService.register(user);
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
