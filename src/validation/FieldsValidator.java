package validation;

import exceptions.InvalidInputException;

public class FieldsValidator {
    //Утилиты
    private static long counter = 0;

    public static long nextId() {
        return counter++;
    }

    private static boolean notBlank(String str) {
        return (str.trim().length() != 0);
    }
    //Конец утилит

    //Валидации для сущности entities.User
    public static void isUserValid(String mailStr, String fullName, long now) {
        if (!isMailValid(mailStr)) throw new InvalidInputException("Неверная почта");
        if (!isFullNameValid(fullName)) throw new InvalidInputException("Неверное имя, кол-во символов меньше 6");
    }

    public static boolean isMailValid(String mailStr) {
        return (notBlank(mailStr) && mailStr.contains("@"));
    }

    public static boolean isFullNameValid(String fullName) {
        return fullName.length() >= 6;
    }

    public static void isPasswordValid(String fullName) {
        if(fullName.length() < 4){
            throw new IllegalArgumentException("Задано некорректное значение");
        }
    }

    /* Фигня из домашки от 26 нояб.
    public static boolean idsExist(long userId, long slotId, Slot[] slots, Order[] orders) {
        boolean userExist = false;
        boolean slotExist = false;

        for (Slot s : slots) {
            for (Order o : orders) {
                if (o.getUserId() == userId) userExist = true;
                if (o.getSlotId() == slotId) slotExist = true;
            }
        }
        return userExist && slotExist;
    }
    */
}
