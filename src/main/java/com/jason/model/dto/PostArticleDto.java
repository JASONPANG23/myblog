package com.jason.model.dto;

import com.jason.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostArticleDto {
    private Integer id ;
    @NotNull
    private String title;
    @NotNull
    private String content;
    private String description;
    private String firstPicture;
    private boolean published ;
    private boolean comment;
    @NotNull
    private Integer categoryId;
    private String tags ;
}
