package com.jason.utils;

import com.jason.exception.CustomizeErrorCode;
import com.jason.exception.CustomizeException;
import com.jason.model.entity.Tag;
import org.springframework.util.StringUtils;

import java.util.List;

public class TagsConverter {

    public static Tag[] parse(String str){
        if(StringUtils.isEmpty(str)) return null ;
        String[] tagStrArr = str.split(";");
        if(tagStrArr.length == 0){
            return null ;
        }
        Tag[] tags = new Tag[tagStrArr.length];

        int index = 0 ;
        for (String s : tagStrArr) {
            Tag tag = new Tag();
            tag.setName(s);
            tags[index ++ ] =tag ;
        }

        return tags ;
    }

    public static String parse(Tag[] tags){
        if(tags == null ||  tags.length == 0)
            return "" ;
        StringBuilder stringBuilder = new StringBuilder() ;

        for (Tag tag : tags) {
            stringBuilder.append(tag.getName());
            stringBuilder.append(";");
        }

        return stringBuilder.toString();
    }

    public static String parse(List<Tag> tags){
        if(tags == null ||  tags.size() == 0)
            return "" ;
        StringBuilder stringBuilder = new StringBuilder() ;

        for (Tag tag : tags) {
            stringBuilder.append(tag.getName());
            stringBuilder.append(";");
        }
        return stringBuilder.toString();
    }
}
