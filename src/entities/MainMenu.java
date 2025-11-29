package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private PollService pollService;
    private VotingService votingService;

    public MainMenu(User currentUser, PollService pollService, VotingService votingService) {
        this.currentUser = currentUser;
        this.pollService = pollService;
        this.votingService = votingService;
    }

    public void show(){
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("Добро пожаловать, " + currentUser.getEmail() + "!");
        System.out.println("1. Пройти новый опрос");
        System.out.println("2. Изменить свой ответ ");
        System.out.println("3. Посмотреть пройденные опросы");
        System.out.println("4. Посмотреть статистику");
        System.out.println("5. Выйти");
        System.out.print("Выберите действие: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                showActivePolls();
                break;
            case "2":
                showfinishedPolls();
                break;
            case "3":
                showCompletedPolls();
                break;
            case "4":
                showStatistics();
                break;
            case "5":
                System.out.println("Выход из системы...");
                return;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    private void showActivePolls(){
        List<Poll> allPolls = pollService.getPolls();

        if (allPolls.isEmpty()) return;

        System.out.println("Выберите номер опроса: ");

        Map<Integer, Poll> pollMap= new HashMap<>();

        int number = 0;
        for (Poll poll: allPolls){
            if (poll.isActive()){
                number++;
                System.out.println(number + ": " +poll.getQuestion());
                pollMap.put(number,poll);
            }
        }

        if (number == 0) return;

        int pointBack = number+1;
        System.out.println(pointBack + ": Назад");

        try {
            int choice = scanner.nextInt();
            if (choice == pointBack) {
                return;
            } else if (pollMap.containsKey(choice)) {
                showPoll(pollMap.get(choice));
            }else{
                System.out.println("Неверный выбор");
                return;
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода"+ e.getMessage());
        }

    }

    public void showfinishedPolls() {
        List<Vote> usersVotes = votingService.getUserVotes(currentUser.getPasswordHash());

        if (usersVotes.isEmpty()) {
            System.out.println("\nВы еще не прошли ни одного опроса.");
            return;
        }

        System.out.println("\n=== ПРОЙДЕННЫЕ ОПРОСЫ ===");
        int number = 0;
        System.out.println("Выберите номер опроса: ");
        Map<Integer, Poll> pollMap = new HashMap<>();
        for (Vote vote : usersVotes) {
            Poll poll = pollService.findByHash(vote.getPoll().getPollHash());

            if (vote.getStatus() == Vote.StatusEnum.FINISHED){
                number++;
                System.out.println(number + ": " + poll.getQuestion());
                pollMap.put(number, poll);
            }
        }

        if (number == 0) {
            System.out.println("Нет законченных опросов!");
            return;
        }

        int pointBack = number+1;
        System.out.println(pointBack + ": Назад");

        try {
            int choice = scanner.nextInt();
            if (choice == pointBack) {
                return;
            } else if (pollMap.containsKey(choice)) {
                showPoll(pollMap.get(choice));
            }else{
                System.out.println("Неверный выбор");
                return;
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода");
        }

    }

    private void showPoll(Poll poll){
        Map<Integer,Long> pollOptions = poll.showOptions();
        System.out.print("Выберите вариант: ");

        int choice = scanner.nextInt();
        Long currentOptionID = pollOptions.get(choice);
        if(currentOptionID == null){
            System.err.println("Неправильный выбор");
            return;
        }
        AnswerOption option = pollService.findAnswerOptionById(currentOptionID);

        System.out.println("Ваш выбор: "+ option.getValue());
        if (option != null) {
            Vote vote = votingService.createOrUpdateVote(currentUser, poll, option);
            vote.setStatus(Vote.StatusEnum.FINISHED);
            System.out.println("Ваш ответ сохранен!");
        } else{
            System.out.println("Неверный выбор!");
            }
        }

    private void showCompletedPolls() {
        List<Vote> usersVotes = votingService.getUserVotes(currentUser.getPasswordHash());

        if (usersVotes.isEmpty()) {
            System.out.println("\nВы еще не прошли ни одного опроса.");
            return;
        }

        System.out.println("\n=== ПРОЙДЕННЫЕ ОПРОСЫ ===");
        for (Vote vote : usersVotes) {
            Poll poll = pollService.findByHash(vote.getPoll().getPollHash());
            System.out.println("Опрос: " + poll.getQuestion());
            System.out.println("Ваш ответ: " + vote.getOption().getValue());
            System.out.println("Дата: " + vote.getVoteDate());
            System.out.println("--------------");
        }
    }

    private void showStatistics(){

    }

}

