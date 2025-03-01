package org.sidorov.workroom.filter;

import org.sidorov.workroom.command.Command;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.sidorov.workroom.command.Command.EMPTY;
import static org.sidorov.workroom.command.Command.LOGIN;
import static org.sidorov.workroom.command.Command.SHOW_LOGIN_PAGE;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String commandString = request.getParameter("command");

        if (commandString == null) {
            commandString = EMPTY.toString();
        }

        Command commandType;
        try {
            commandType = Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = EMPTY;
        }

        boolean isLoginCommand = LOGIN.equals(commandType) || SHOW_LOGIN_PAGE.equals(commandType);

        User sessionUser = (User) httpServletRequest.getSession().getAttribute("user");
        if (sessionUser == null && !isLoginCommand) {
            httpServletRequest.getRequestDispatcher(AppManager.getProperty("page.login")).forward(request, response);
            return;
        }

        if (sessionUser != null && isLoginCommand) {
            Result mainPageResult = sessionUser.getUserType().getDefaultResult();
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(mainPageResult.getResource());
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
