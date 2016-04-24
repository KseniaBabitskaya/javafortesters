package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Ксюшенька on 24.04.2016.
 */
public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("19d9cbc55dd4941229abe306e30695e7fba21365");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("ksynnn", "javafortesters")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
