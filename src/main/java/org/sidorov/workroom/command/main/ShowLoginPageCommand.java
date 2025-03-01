package org.sidorov.workroom.command.main;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.ForwardResult;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.resource.AppManager;

import javax.servlet.http.HttpServletRequest;

public class ShowLoginPageCommand implements ActionCommand {

    private final Result result = new ForwardResult(AppManager.getProperty("page.login"));

    @Override
    public Result execute(HttpServletRequest request) {
        return result;
    }
}
