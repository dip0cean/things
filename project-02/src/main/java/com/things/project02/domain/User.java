package com.things.project02.domain;

import com.things.project02.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_ID")
    private String userId;

    @Column(nullable = false, name = "USER_PW")
    private String userPw;

    @Column(nullable = false, name = "USER_NICK")
    private String userNick;

    @Column(nullable = false, name = "USER_INTRO")
    private String userIntro;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public User(String userId, String userPw, String userNick, String userIntro) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNick = userNick;
        this.userIntro = userIntro;
    }

    @Builder
    public User(UserDto.UserRes userRes) {
        this.userId = userRes.getUserId();
        this.userPw = userRes.getUserPw();
        this.userNick = userRes.getUserNick();
        this.userIntro = userRes.getUserIntro();
    }

    public void update(User entity) {
        this.userId = entity.getUserId();
        this.userPw = entity.getUserPw();
        this.userNick = entity.getUserNick();
        this.userIntro = entity.getUserIntro();
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
