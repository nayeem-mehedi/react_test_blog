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
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ARTICLES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article implements Serializable {
    private static final long serialVersionUID = 5198648483792147899L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    @Column(name = "COVERIMAGE")
    private String coverImage;
    @ManyToOne
    @JoinColumn(name = "AUTHOR", nullable = false)
    private User user;
    private String body;
    private Integer approval_state;
    private Integer delete_flag;
    private Date created;
    private Date updated;
    @OneToMany(mappedBy = "comment_article", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comment> comments;
    @Transient
    private Integer commentsNumber;
    @OneToMany(mappedBy = "reaction_article", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Reaction> reactions;
    @Transient
    private Integer likesNumber;

    public User getUser() {
        User user = new User();
        user.setId(this.user.getId());
        user.setUsername(this.user.getUsername());
        return user;
    }

    public Integer getCommentsNumber() {
        return this.comments.size();
    }

    public Integer getLikesNumber() {
        return this.reactions.size();
    }
}