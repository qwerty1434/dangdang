package com.dangdang.member.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    private UUID id;

    @NotNull
    private String email;

    @Setter
    @NotNull
    private String password;

    @Setter
    @NotNull
    private String nickname;

    @NotNull
    private String background;

    @NotNull
    @CreationTimestamp
    private LocalDate joinDate;

    @NotNull
    @Builder.Default
    @ColumnDefault("false")
    private boolean isDelete=false;

    @NotNull
    @Builder.Default
    @ColumnDefault("false")
    private boolean isAdmin=false;

    @NotNull
    @Builder.Default
//    @ColumnDefault("DD")
    private String provider = "DD";

    @Setter
    private String refreshToken;

    private String publicKey;

    private String kakaoSid;

    @Setter
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User(String email, String password, String nickname, String publicKey) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.publicKey = publicKey;
        this.provider = "DD";
    }
}
