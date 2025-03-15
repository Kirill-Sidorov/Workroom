package org.sidorov.workroom.servlet;

import org.sidorov.workroom.command.Command;
import org.sidorov.workroom.command.result.ResponseType;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.DaoFactory;
import org.sidorov.workroom.datalayer.DbType;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.sidorov.workroom.command.Command.EMPTY;
import static org.sidorov.workroom.command.result.ResponseType.FORWARD;
import static org.sidorov.workroom.command.result.ResponseType.REDIRECT;

public class FrontController extends HttpServlet {

    @Override
    public void init() {
        DaoFactory factory = DbType.POSTGRE.getDaoFactory();
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("OrderDao", factory.getOrderDao());
        servletContext.setAttribute("OrderReplacementPartDao", factory.getOrderReplacementPartDao());
        servletContext.setAttribute("ReplacementPartDao", factory.getReplacementPartDao());
        servletContext.setAttribute("UserDao", factory.getUserDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        Result result = commandType.getCommand().execute(request);

        String resourceName = result.getResource();
        ResponseType responseType = result.getResponseType();
        if (FORWARD == responseType) {
            request.getRequestDispatcher(resourceName).forward(request, response);

        } else if (REDIRECT == responseType) {
            response.sendRedirect(resourceName);
        }
    }

    @Override
    public void destroy() {
        UserDao userDao = (UserDao) getServletContext().getAttribute("UserDao");
        if (userDao != null) {
            List<User> users = userDao.getListUsers();
            for (User user : users) {
                userDao.setAuthorized(false, user.getId());
            }
        }
    }
}
