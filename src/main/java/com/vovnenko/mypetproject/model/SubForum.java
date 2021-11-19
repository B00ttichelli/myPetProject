package com.vovnenko.mypetproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "subforum")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubForum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Forum name can't be blank")
    @Column(name = "forum_name")
    private String subForumName;

   @NotBlank
   @Column(name = "description")
    private String description;

   @OneToMany(fetch = FetchType.LAZY)
   private List<Post> posts;

   private Instant createDate;

   @ManyToOne(fetch = FetchType.LAZY)
   private User user;
}