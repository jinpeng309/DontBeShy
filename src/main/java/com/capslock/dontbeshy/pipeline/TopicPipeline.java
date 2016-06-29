package com.capslock.dontbeshy.pipeline;

import com.capslock.dontbeshy.domain.Topic;
import com.capslock.dontbeshy.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by capslock.
 */
@Component
public class TopicPipeline implements PageModelPipeline<Topic>, Pipeline {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public void process(final Topic topic, final Task task) {
        topicMapper.add(topic);
    }

    @Override
    public void process(final ResultItems resultItems, final Task task) {
    }
}
