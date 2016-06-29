package com.capslock.dontbeshy.mapper;

import com.capslock.dontbeshy.domain.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by capslock.
 */
@Mapper
public interface TopicMapper {

    @Insert("insert into topic values(#{topicId})")
    void add(final Topic topic);
}
