package org.sidorov.workroom.logic;

import org.sidorov.workroom.command.result.RedirectResult;
import org.sidorov.workroom.command.result.Result;

public enum UserType {
    ADMIN(1, new RedirectResult("workroom?command=show_admin_main_page")),
    MODERATOR(2, new RedirectResult("workroom?command=show_moderator_main_page")),
    CUSTOMER(3, new RedirectResult("workroom?command=show_customer_main_page")),
    MASTER(4, new RedirectResult("workroom?command=show_master_main_page")),
    STOREKEEPER(5, new RedirectResult("workroom?command=show_storekeeper_main_page"));

    private final int id;
    private final Result defaultResult;

    UserType(int id, Result defaultResult) {
        this.id = id;
        this.defaultResult = defaultResult;
    }

    public int getId() {
        return id;
    }

    public Result getDefaultResult() {
        return defaultResult;
    }
}
