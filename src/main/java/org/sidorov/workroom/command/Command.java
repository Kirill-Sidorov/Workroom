package org.sidorov.workroom.command;

import org.sidorov.workroom.command.admin.DeleteUserCommand;
import org.sidorov.workroom.command.admin.RegisterUserCommand;
import org.sidorov.workroom.command.admin.ShowAdminMainPageCommand;
import org.sidorov.workroom.command.admin.ShowRegistrationPageCommand;
import org.sidorov.workroom.command.admin.ShowUserEditorPageCommand;
import org.sidorov.workroom.command.admin.UpdateUserCommand;
import org.sidorov.workroom.command.customer.CreateOrderCommand;
import org.sidorov.workroom.command.customer.ShowCustomerMainPageCommand;
import org.sidorov.workroom.command.customer.ShowFullCostPageCommand;
import org.sidorov.workroom.command.customer.ShowOrderCreationPageCommand;
import org.sidorov.workroom.command.main.CancelOrderCommand;
import org.sidorov.workroom.command.main.EditOrderCommand;
import org.sidorov.workroom.command.main.EmptyCommand;
import org.sidorov.workroom.command.main.LoginCommand;
import org.sidorov.workroom.command.main.LogoutCommand;
import org.sidorov.workroom.command.main.ShowLoginPageCommand;
import org.sidorov.workroom.command.main.ShowOrderEditorPageCommand;
import org.sidorov.workroom.command.master.ConfirmOrderExecutionCommand;
import org.sidorov.workroom.command.master.EditOrderMasterCommand;
import org.sidorov.workroom.command.master.ShowMasterMainPageCommand;
import org.sidorov.workroom.command.master.ShowMasterOrderEditorPageCommand;
import org.sidorov.workroom.command.master.TakeOrderCommand;
import org.sidorov.workroom.command.moderator.ChangeUserStatusCommand;
import org.sidorov.workroom.command.moderator.CheckOrderCommand;
import org.sidorov.workroom.command.moderator.DeleteOrderCommand;
import org.sidorov.workroom.command.moderator.ShowModeratorMainPageCommand;
import org.sidorov.workroom.command.storekeeper.ConfirmReplacementPartsCommand;
import org.sidorov.workroom.command.storekeeper.CreateReplacementPartCommand;
import org.sidorov.workroom.command.storekeeper.DeleteReplacementPartCommand;
import org.sidorov.workroom.command.storekeeper.EditReplacementPartCommand;
import org.sidorov.workroom.command.storekeeper.ShowOrderPartsPageCommand;
import org.sidorov.workroom.command.storekeeper.ShowReplacementPartCreationPageCommand;
import org.sidorov.workroom.command.storekeeper.ShowReplacementPartEditorPageCommand;
import org.sidorov.workroom.command.storekeeper.ShowStorekeeperMainPageCommand;

public enum Command {
    EMPTY(new EmptyCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER_USER(new RegisterUserCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    EDIT_ORDER(new EditOrderCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    DELETE_ORDER(new DeleteOrderCommand()),
    CHECK_ORDER(new CheckOrderCommand()),
    TAKE_ORDER(new TakeOrderCommand()),
    EDIT_ORDER_MASTER(new EditOrderMasterCommand()),
    EDIT_REPLACEMENT_PART(new EditReplacementPartCommand()),
    DELETE_REPLACEMENT_PART(new DeleteReplacementPartCommand()),
    CONFIRM_REPLACEMENT_PARTS(new ConfirmReplacementPartsCommand()),
    CONFIRM_ORDER_EXECUTION(new ConfirmOrderExecutionCommand()),
    CREATE_REPLACEMENT_PART(new CreateReplacementPartCommand()),

    SHOW_LOGIN_PAGE(new ShowLoginPageCommand()),
    SHOW_REGISTRATION_PAGE(new ShowRegistrationPageCommand()),
    SHOW_ORDER_CREATION_PAGE(new ShowOrderCreationPageCommand()),
    SHOW_USER_EDITOR_PAGE(new ShowUserEditorPageCommand()),
    SHOW_ORDER_PARTS_PAGE(new ShowOrderPartsPageCommand()),
    SHOW_MASTER_ORDER_EDITOR_PAGE(new ShowMasterOrderEditorPageCommand()),
    SHOW_REPLACEMENT_PART_CREATION_PAGE(new ShowReplacementPartCreationPageCommand()),
    SHOW_ORDER_EDITOR_PAGE(new ShowOrderEditorPageCommand()),
    SHOW_REPLACEMENT_PART_EDITOR_PAGE(new ShowReplacementPartEditorPageCommand()),
    SHOW_FULL_COST_PAGE(new ShowFullCostPageCommand()),
    SHOW_ADMIN_MAIN_PAGE(new ShowAdminMainPageCommand()),
    SHOW_CUSTOMER_MAIN_PAGE(new ShowCustomerMainPageCommand()),
    SHOW_MASTER_MAIN_PAGE(new ShowMasterMainPageCommand()),
    SHOW_MODERATOR_MAIN_PAGE(new ShowModeratorMainPageCommand()),
    SHOW_STOREKEEPER_MAIN_PAGE(new ShowStorekeeperMainPageCommand());

    private final ActionCommand command;

    Command(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}
