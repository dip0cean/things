package com.things.project02.dto;

import com.things.project02.domain.User;
import lombok.*;

import java.time.format.DateTimeFormatter;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class UserReq {

        private Long   id;
        private String userId;
        private String userPw;
        private String userNick;
        private String userIntro;

        @Builder
        public UserReq(String userId, String userPw, String userNick, String userIntro) {
            this.userId = userId;
            this.userPw = userPw;
            this.userNick = userNick;
            this.userIntro = userIntro;
        }

        @Builder
        public UserReq(Long id, String userPw) {
            this.id = id;
            this.userPw = userPw;
        }

        @Builder
        public UserReq(UserRes userRes) {
            this.id = userRes.getId();
            this.userId = userRes.getUserId();
            this.userPw = userRes.getUserPw();
            this.userNick = userRes.getUserNick();
            this.userIntro = userRes.getUserIntro();
        }

        public User toEntity() {
            return User.builder()
                    .userId(this.userId)
                    .userPw(this.userPw)
                    .userNick(this.userNick)
                    .userIntro(this.userIntro)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserRes {
        private Long   id;
        private String userId;
        private String userPw;
        private String userNick;
        private String userIntro;
        private String createdDate;
        private String updatedDate;

        public UserRes(User entity) {
            this.id = entity.getId();
            this.userId = entity.getUserId();
            this.userPw = entity.getUserPw();
            this.userNick = entity.getUserNick();
            this.userIntro = entity.getUserIntro();
            this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            this.updatedDate = entity.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
    }
}
