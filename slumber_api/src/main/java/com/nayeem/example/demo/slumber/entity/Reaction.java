package com.nayeem.example.demo.slumber.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "REACTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int type;
    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User reaction_user;
    private Long article_id;
    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID",nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    //@Column(insertable = false,updatable = false)
    private Article reaction_article;

    public User getLike_user() {
        User user = new User();
        user.setName(this.reaction_user.getUsername());
        return user;
    }

//    public Article getLike_article() {
//        Article article = new Article();
//        article.setId(like_article.getId());
//        return article;
//    }
}
