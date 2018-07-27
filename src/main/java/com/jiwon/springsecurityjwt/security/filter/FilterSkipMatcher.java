package com.jiwon.springsecurityjwt.security.filter;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class FilterSkipMatcher implements RequestMatcher {
    // skip 할 url 목록들
    private OrRequestMatcher orRequestMatcher;
    private RequestMatcher processingMathcher;

    public FilterSkipMatcher(List<String> pathToSkip, String processingPath){
        this.orRequestMatcher = new OrRequestMatcher(pathToSkip.stream().map(p -> new AntPathRequestMatcher(p)).collect(Collectors.toList()));
        this.processingMathcher = new AntPathRequestMatcher(processingPath);
    }

    @Override
    public boolean matches(HttpServletRequest req) {
        return !orRequestMatcher.matches(req) && processingMathcher.matches(req);
    }
}
