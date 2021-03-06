package com.spring.jwt.springbootjwtreact.customize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomJwtEntryPoint implements AuthenticationEntryPoint{


    private static final Logger logger = LoggerFactory.getLogger(CustomJwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException auth)
            throws IOException, ServletException {

        logger.error("Responding with unauthorized error. Message - {}",
                auth.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Maaf proses authorized gagal & tidak dapat mengakses resource ini");

    }
}
