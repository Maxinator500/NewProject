package services;

import entities.User;
import lombok.ToString;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@ToString(of = {"users", "id"})
public class UserService {
    private static final String USERS_CSV = "users.csv";
    private Map<String, User> users = new HashMap<>();
    private Long id;

    public UserService() {
        loadUsersFromSCV();
    }

    public User authenticate(String login, String password){
        User user = users.get(login);
        if (user != null && user.checkPassword(password)){
            return user;
        }
        return null;
    }

    public void register(User user){
            if (isLoginExits(user.getEmail())){
                System.out.println("Пользователь уже существует");
                return;
            }
            users.put(user.getEmail(),user);
            saveUserToCSV(user);
    }

    public void readUsersFromSCV(){
        File file = new File(USERS_CSV);
        if (file.length() == 0) {
            System.out.println("Файл пустой");
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                System.out.println(parts[0] + " " +parts[1]);
            }

        } catch (Exception e) {
            throw new RuntimeException("Нет данных для чтения "+ e);
        }
    }

    public void loadUsersFromSCV(){
        users.clear();
        File file = new File(USERS_CSV);
        if (!file.exists()) {
            System.out.println("Файл не существует");
            return;
        }

        if (file.length() == 0) {
            System.out.println("Файл пустой");
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                User user = new User("temp", parts[1]);
                user.setPasswordHash(parts[2]);
                users.put(parts[1],user);

            }

        } catch (Exception e) {
            throw new RuntimeException("Нет данных для чтения "+ e);
        }
    }

    private void saveUserToCSV(User user){
        try (FileWriter writer = new FileWriter(USERS_CSV,true)){
            writer.write(user.getId() + ","+ user.getEmail()+ ","+ user.getPasswordHash()+"\n");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла"+ e);
        }
    }

    private boolean isLoginExits(String login){
        return users.containsKey(login);
    }


    public User findByHash(String hash) {
        for (User user : users.values()){
            if(hash.equals(user.getPasswordHash())){
                return user;
            }
        }
        return null;
    }


    public void checkKeys(){
        System.out.println(users.keySet());
    }
}
