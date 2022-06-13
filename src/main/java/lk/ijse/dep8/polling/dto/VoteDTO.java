package lk.ijse.dep8.polling.dto;

import jakarta.json.bind.annotation.JsonbTransient;
import lk.ijse.dep8.polling.util.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO implements Serializable {
    private Integer pollId;
    private String user;
    private VoteType voteType;

    @JsonbTransient
    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = VoteType.valueOf(voteType);
    }
}
