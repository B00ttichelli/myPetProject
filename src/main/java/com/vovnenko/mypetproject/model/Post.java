package com.vovnenko.mypetproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank(message = "Post name cant be empty")
    @UniqueElements
    private String postName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;

    private Instant createDate;
    @ManyToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private SubForum subForum;

    @NotBlank(message = "Cant be blank")
    @Lob
    private String postBody;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
}
