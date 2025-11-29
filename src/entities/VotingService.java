package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VotingService {
    private static final String VOTES = "votes.csv";
    private List<Vote> allVotes = new ArrayList<>();

    private PollService pollService = new PollService();

    public VotingService(){
        loadVotesFromCSV();
    }

    public Vote createOrUpdateVote(User user, Poll poll, AnswerOption option){
        Vote excistVote = getUserVote(user,poll);
        if( excistVote != null){
            updateVoteInCsv(excistVote);
            return excistVote;

        }else {
            Vote newVote = new Vote(user,poll,option);
            allVotes.add(newVote);
            saveVoteToCSV(newVote);
            return newVote;
        }
    }


    private Vote getUserVote(User user, Poll poll){
        for (Vote vote : allVotes){
            if(vote.getUser().equals(user) && vote.getPoll().equals(poll)){
                return vote;
            }
        }
        return null;
    }

    private void saveVoteToCSV(Vote vote){
        try(FileWriter writer = new FileWriter(VOTES,true)){
            writer.write(vote.getId() + ","
                           + vote.getUser().getPasswordHash() + ","
                           + vote.getPoll().getPollHash()+ ","
                           + vote.getOption().getId());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать голос "+e.getMessage());
        }
    }

    private void loadVotesFromCSV(){
        File file = new File(VOTES);
        if(!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(VOTES))){
            String line;

            while ((line = reader.readLine()) != null){
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");

                String userHash = parts[1].trim();
                String pollHash = parts[2].trim();;
                Long optionId = Long.valueOf(parts[3]);

                AnswerOption option = pollService.findAnswerOptionById(optionId);
                UserService userService = new UserService();
                User user =  userService.findByhash(userHash);
                Poll poll = pollService.findByHash(pollHash);

                Vote vote = new Vote(user,poll,option);
                vote.setStatus(Vote.StatusEnum.FINISHED);
                allVotes.add(vote);

            }
        }catch (Exception e){
            System.err.println("Не удалось прочитать файл " + e.getMessage());
        }

    }

    private void updateVoteInCsv(Vote upVote) {
        File file = new File(VOTES);
        List<String> lines = new ArrayList<>();

        if (!file.exists()) return;
        try(BufferedReader reader = new BufferedReader(new FileReader(VOTES))) {
            String line;

            while ((line = reader.readLine()) != null){
                if (line.isEmpty()) continue;

                String[] parts = line.split(line);
                Long voteId = Long.valueOf(parts[0]);

                if(voteId.equals(upVote.getId())){
                    line = voteId + ","
                            + upVote.getUser().getId() + ","
                            + upVote.getPoll().getId() + ","
                            + upVote.getOption();

                    lines.add(line);
                }
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(VOTES)){
            for (String line : lines){
                writer.write(line);
            }
        }catch (IOException e){
            System.err.println("Не удалось обновить инфорацию!");
        }

    }

    public List<Vote> getAllVotes(){
        return new ArrayList<>(allVotes);
    }

    public List<Vote> getUserVotes(String hash){
        ArrayList<Vote> usersVotes = new ArrayList<>();
        for(Vote vote : allVotes)
            if (hash.equals(vote.getUser().getPasswordHash())){
                usersVotes.add(vote);
            }
        return usersVotes;
    }

    public List<Poll> getUserPolls(String hash){
        ArrayList<Poll> usersPolls = new ArrayList<>();
        for(Vote vote : allVotes)
            if (hash.equals(vote.getUser().getPasswordHash())){
                usersPolls.add(vote.getPoll());
            }
        return usersPolls;
    }

}
