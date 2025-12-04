package services;

import entities.AnswerOption;
import entities.Poll;
import entities.User;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class PollService {
    private List<Poll> polls = new ArrayList<>();
    private List<AnswerOption> options = new ArrayList<>();
    private Long nextPollID = 1L;
    private Long nextOptionId = 1L;
    private static final String POLLS_CSV = "polls.csv";

    public PollService() {
        createPollsDefault();
    }

    public List<Poll> getPolls() {
        return new ArrayList<>(polls);
    }

    public void createPollsDefault(){
        File file = new File(POLLS_CSV);
        if (file.length() == 0) {
            System.out.println("Файл пустой");
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
                String[] parts = line.split("/");

                String question = parts[0].trim();
                String[] optionsValue = parts[1].split(",");

                List<AnswerOption> options1 = new ArrayList<>();
                for (int i = 0; i < optionsValue.length; i++) {
                    options1.add(new AnswerOption(nextOptionId++,optionsValue[i]));
                }
                Poll poll1 = new Poll(nextPollID++,
                        LocalDateTime.now(),
                        question,
                        options1
                );

                polls.add(poll1);
                options.addAll(options1);

            }

        } catch (Exception e) {
            throw new RuntimeException("Нет данных для чтения "+ e);
        }

    }

    private void saveUserToCSV(User user){

    }

    public void createPollsAdditional(List<AnswerOption> options, String question){
        try (FileWriter writer = new FileWriter(POLLS_CSV,true)){
            StringBuilder optionValues = new StringBuilder();
            for (AnswerOption option : options){
                optionValues.append(option.getValue()).append(",");
            }
            optionValues.replace(optionValues.length() - 1,optionValues.length()," ");
            writer.write(question + "/"+ optionValues+"\n");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении файла"+ e);
        }

        Poll poll = new Poll(nextPollID++,
                LocalDateTime.now(),
                question,
                options
        );

        polls.add(poll);
        this.options.addAll(options);
    }


    public Poll findByHash(String hash){
        for (Poll poll : polls){
            if (hash.equals(poll.getPollHash())){
                return poll;
            }
        }
        return null;
    }

    public AnswerOption findAnswerOptionById(Long id){
        for (AnswerOption option : options){
            if (id.equals(option.getId())){
                return option;
            }
        }
        return null;
    }
}
