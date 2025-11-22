public class Main {
    public static void main(String[] args) {
        User dummyUser = new User(System.currentTimeMillis(), "abab@mail.ru", "ababababababa");

        // Создаем опросы и варианты ответов
        Poll poll1 = new Poll(FieldsValidator.nextId(), FieldsValidator.nextId(), System.currentTimeMillis(),"Пепси или Кока-кола?");
        poll1.addOption(new AnswerOption("Pepsi"));
        poll1.addOption(new AnswerOption("Coke"));

        Poll poll2 = new Poll(FieldsValidator.nextId(), FieldsValidator.nextId(), System.currentTimeMillis(),"У пластиковой соломинки одно отверстие или два?");
        poll2.addOption(new AnswerOption("1"));
        poll2.addOption(new AnswerOption("2"));

        dummyUser.addPoll(poll1);
        dummyUser.addPoll(poll2);

        // Проголосвать 1 раз
        dummyUser.vote();
    }
}
