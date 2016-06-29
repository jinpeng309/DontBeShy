package com.capslock.dontbeshy;

import com.capslock.dontbeshy.pipeline.TopicPipeline;
import com.capslock.dontbeshy.spider.DontBeShyPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * Created by capslock.
 */
@SpringBootApplication
public class SpiderApplication implements CommandLineRunner {
    @Autowired
    private TopicPipeline topicPipeline;

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(SpiderApplication.class);
        application.setWebEnvironment(false);
        application.run(args);
    }

    @Override
    public void run(final String... strings) throws Exception {
        Spider.create(new DontBeShyPageProcessor())
                .addUrl("https://www.douban.com/group/haixiuzu/discussion?start=0")
                .thread(5)
                .addPipeline(new ConsolePipeline())
                .addPipeline(topicPipeline)
                .run();
    }
}
