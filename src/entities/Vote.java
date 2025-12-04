package entities;

import validation.FieldsValidator;
import java.time.LocalDateTime;
import lombok.*;

@RequiredArgsConstructor
@Getter
public class Vote {
    final private User user;
    final private Poll poll;
    private LocalDateTime voteDate = LocalDateTime.now();;
    private Long id = FieldsValidator.nextId();

    @Setter
    private VoteStatus status;

    @Setter
    private AnswerOption option;
}
