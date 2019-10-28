/*
 *
 *  Copyright (c) 2019 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 *
 */
package com.jalasoft.hr.controller;

import com.jalasoft.hr.entities.Cache;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jalasoft.hr.utils.Constants.AUTHORIZATION;
import static com.jalasoft.hr.utils.Constants.LOGIN_PATH;

/**
 * TokenFilter class..
 */
@WebFilter(urlPatterns = {"/*"})
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Filter method to request a valid token in the header.
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        String auth = req.getHeader(AUTHORIZATION);

        String token = null;
        if (auth != null) {
            token = auth.split(" ")[1];
        }

        if (url.contains(LOGIN_PATH) || Cache.getInstance().isValid(token)) {
            chain.doFilter(request, response);
        } else {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
        }
    }

    @Override
    public void destroy() {

    }
}
