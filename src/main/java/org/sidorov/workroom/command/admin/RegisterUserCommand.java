package org.sidorov.workroom.command.admin;

import org.mindrot.jbcrypt.BCrypt;
import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.RedirectResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.UserDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterUserCommand implements ActionCommand {

    private static final String ERROR_REGISTRATION_MESSAGE = "errorRegistrationMessage";
    private static final String CREATED_USER = "createdUser";


    private final String salt = BCrypt.gensalt();
    private final Result errorRegistration = new RedirectResult("workroom?command=show_registration_page");

    @Override
    public Result execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute(ERROR_REGISTRATION_MESSAGE);
        session.removeAttribute(CREATED_USER);

        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {

            User user = new User();
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setPhone(request.getParameter("phoneNumber"));
            user.setUserType(request.getParameter("userType"));
            user.setLogin(request.getParameter("login"));

            String password = request.getParameter("password");
            String passwordRepeat = request.getParameter("passwordRepeat");

            UserDao userDao = (UserDao) request.getServletContext().getAttribute("UserDao");
            User existUser = userDao.getUserByLogin(user.getLogin());

            if (existUser != null) {
                session.setAttribute(ERROR_REGISTRATION_MESSAGE, AppManager.getProperty("message.wrongLogin"));
                user.setLogin("");
                session.setAttribute(CREATED_USER, user);
                return errorRegistration;
            }
            if (!checkTextFields(user)) {
                session.setAttribute(ERROR_REGISTRATION_MESSAGE, AppManager.getProperty("message.allFieldsNeedFilled"));
                session.setAttribute(CREATED_USER, user);
                return errorRegistration;
            }
            if (!checkPass(password, passwordRepeat)) {
                session.setAttribute(ERROR_REGISTRATION_MESSAGE, AppManager.getProperty("message.wrongPassword"));
                session.setAttribute(CREATED_USER, user);
                return errorRegistration;
            }

            user.setPassword(BCrypt.hashpw(password, salt));
            userDao.createUser(user);
        }
        return sessionUser.getUserType().getDefaultResult();
    }

    private boolean checkTextFields(User user) {
        return user.getFirstName().length() != 0
                && user.getLastName().length() != 0
                && user.getPhone().length() != 0
                && user.getLogin().length() != 0;
    }

    private boolean checkPass(String pass, String repeatPass) {
        return pass.length() >= 3 && pass.equals(repeatPass);
    }
}
