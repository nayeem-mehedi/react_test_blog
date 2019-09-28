package com.nayeem.example.demo.slumber.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "body")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private Date created;
    private Date updated;
    @Column(insertable = false,updatable = false)
    private Long article_id;
    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User comment_user;
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID",nullable = false)
    @JsonIgnore
    private Article comment_article;

    public User getComment_user() {
        User user = new User();
        user.setId(comment_user.getId());
        user.setName(this.comment_user.getUsername());
        return user;
    }
}