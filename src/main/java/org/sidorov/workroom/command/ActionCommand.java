package org.sidorov.workroom.command;

import org.sidorov.workroom.command.result.Result;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    Result execute(HttpServletRequest request);
}
