package entities;

import java.time.LocalDateTime;
import java.util.*;

public class PollService {
    private List<Poll> polls = new ArrayList<>();
    private List<AnswerOption> options = new ArrayList<>();
    private Long nextPollID = 1L;
    private Long nextOptionId = 1L;

    public PollService() {
        createPollsDefault();
    }

    public List<Poll> getPolls() {
        return new ArrayList<>(polls);
    }

    private void createPollsDefault(){

        List<AnswerOption> options1 = Arrays.asList(
                new AnswerOption(nextOptionId++,"Pepsi"),
                new AnswerOption(nextOptionId++,"Coke")
        );

        Poll poll1 = new Poll(nextPollID++,
                LocalDateTime.now(),
                "Пепси или Кока-кола?",
                options1
                );

        polls.add(poll1);
        options.addAll(options1);

        List<AnswerOption> options2 = Arrays.asList(
                new AnswerOption(nextOptionId++,"1"),
                new AnswerOption(nextOptionId++,"2")
        );

        Poll poll2 = new Poll(nextPollID++,
                LocalDateTime.now(),
                "У пластиковой соломинки одно отверстие или два?",
                options2
        );

        polls.add(poll2);
        options.addAll(options2);

    }

    private void createPollsAdditional(List<AnswerOption> options, String question){
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
