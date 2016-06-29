package com.capslock.dontbeshy.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by capslock.
 */
@Data
public class Topic {
    private long topicId;
    private List<String> images;
}
