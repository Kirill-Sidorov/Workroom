package org.sidorov.workroom.command.storekeeper;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.ReplacementPart;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.ReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.UserType;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class CreateReplacementPartCommand implements ActionCommand {

    private final Result errorPartCreationResult = new ForwardResult(AppManager.getProperty("page.storekeeperCreatePart"));

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.STOREKEEPER)) {
            ReplacementPart part = new ReplacementPart();
            part.setName(request.getParameter("partName"));
            part.setCost(Integer.parseInt(request.getParameter("cost")));
            part.setInStock(Integer.parseInt(request.getParameter("inStock")));

            if (!checkTextFields(part)) {
                request.setAttribute("errorPartCreationMessage", AppManager.getProperty("message.allFieldsNeedFilled"));
                request.setAttribute("part", part);
                return errorPartCreationResult;
            }
            ReplacementPartDao replacementPartDao = (ReplacementPartDao) request.getServletContext().getAttribute("ReplacementPartDao");
            replacementPartDao.createPart(part);
        }
        return sessionUser.getUserType().getDefaultResult();
    }

    private boolean checkTextFields(ReplacementPart part) {
        return part.getName().length() != 0 && part.getCost() > 0;
    }
}
