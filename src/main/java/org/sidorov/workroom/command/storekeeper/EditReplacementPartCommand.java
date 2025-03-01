package org.sidorov.workroom.command.storekeeper;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.ReplacementPart;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;

public class EditReplacementPartCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.STOREKEEPER)) {
            ReplacementPart part = new ReplacementPart();
            part.setId(Integer.parseInt(request.getParameter("partId")));
            part.setName(request.getParameter("partName"));
            part.setCost(Integer.parseInt(request.getParameter("cost")));
            part.setInStock(Integer.parseInt(request.getParameter("inStock")));
            ReplacementPartDao replacementPartDao = (ReplacementPartDao) request.getServletContext().getAttribute("ReplacementPartDao");
            replacementPartDao.updatePart(part);
        }
        return sessionUser.getUserType().getDefaultResult();
    }
}
