package com.jiwon.springsecurityjwt.security.jwt;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HeaderTokenExtractorTest {

    private HeaderTokenExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new HeaderTokenExtractor();
    }

    @Test
    public void test() {
        String header = "Bearer your_token_here";
        assertThat(extractor.extract(header), is("your_token_here"));

    }
}