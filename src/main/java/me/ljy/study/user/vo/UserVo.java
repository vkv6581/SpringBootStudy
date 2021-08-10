package me.ljy.study.user.vo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER", uniqueConstraints = {@UniqueConstraint(
        name="USER_UNIQUE",
        columnNames = {"userId", "email"}
)})
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class UserVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String email;

    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regDt;

    public UserVo() {

    }
}
