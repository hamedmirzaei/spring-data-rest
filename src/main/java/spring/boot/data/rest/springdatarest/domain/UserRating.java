package spring.boot.data.rest.springdatarest.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class UserRating implements Serializable {

    @EmbeddedId
    private UserRatingPk pk;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    protected UserRating() {
    }

    public UserRating(UserRatingPk pk, Integer score, String comment) {
        this.pk = pk;
        this.score = score;
        this.comment = comment;
    }

    public UserRatingPk getPk() {
        return pk;
    }

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }
}
