package org.sidorov.workroom.listener;

import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        User sessionUser = (User) event.getSession().getAttribute("user");
        if (sessionUser != null) {
            UserDao userDao = (UserDao) event.getSession().getServletContext().getAttribute("UserDao");
            if (userDao != null) {
                userDao.setAuthorized(false, sessionUser.getId());
            }
        }
    }
}
