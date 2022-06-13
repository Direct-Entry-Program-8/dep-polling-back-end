package lk.ijse.dep8.polling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class VotePK implements Serializable {
    @Column(name = "poll_id")
    private int pollId;
    private String user;
}
