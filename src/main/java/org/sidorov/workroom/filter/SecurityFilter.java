package org.sidorov.workroom.filter;

import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestUrl = httpServletRequest.getRequestURI();
        if (requestUrl == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (requestUrl.equals("/workroom")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (requestUrl.equals("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (requestUrl.equals("/")) {
            User sessionUser = (User) httpServletRequest.getSession().getAttribute("user");
            if (sessionUser == null) {
                httpServletResponse.sendRedirect("/workroom");
            } else {
                Result mainPageResult = sessionUser.getUserType().getDefaultResult();
                httpServletResponse.sendRedirect(mainPageResult.getResource());
            }
            return;
        }
        // Images checks. Do not forget to add new images!
        if (requestUrl.equals("/img/accept.png")
                || requestUrl.equals("/img/cancel.png")
                || requestUrl.equals("/img/cost.png")
                || requestUrl.equals("/img/delete.png")
                || requestUrl.equals("/img/edit.png")
                || requestUrl.equals("/img/lock.png")
                || requestUrl.equals("/img/search.png")) {

            filterChain.doFilter(request, response);
            return;
        }

        httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void destroy() {
    }
}
