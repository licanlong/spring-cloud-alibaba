package com.licanlong;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import lombok.Data;

import java.util.List;

/**
 * @author licl
 * @date 2019/10/21
 */
public class FeignTest {

    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
    }

    @Data
    static class Contributor {
        String login;
        int contributions;
    }


    public static void main(String... args) {
        GitHub github = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(GitHub.class, "https://api.github.com");

        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }

    }
}
