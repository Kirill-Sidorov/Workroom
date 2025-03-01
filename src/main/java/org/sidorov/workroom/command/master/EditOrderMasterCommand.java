package org.sidorov.workroom.command.master;

import org.sidorov.workroom.command.ActionCommand;
import org.sidorov.workroom.command.result.Result;
import org.sidorov.workroom.datalayer.OrderReplacementPart;
import org.sidorov.workroom.datalayer.User;
import org.sidorov.workroom.datalayer.data.OrderDao;
import org.sidorov.workroom.datalayer.data.OrderReplacementPartDao;
import org.sidorov.workroom.helper.UserAccessHelper;
import org.sidorov.workroom.logic.OrderStatus;
import org.sidorov.workroom.logic.UserType;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditOrderMasterCommand implements ActionCommand {

    @Override
    public Result execute(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (UserAccessHelper.checkUser(sessionUser, UserType.MASTER)) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int costWork = Integer.parseInt(request.getParameter("costWork"));
            OrderDao orderDao = (OrderDao) request.getServletContext().getAttribute("OrderDao");
            String[] selectedParts = request.getParameterValues("selectedParts");
            String[] numberParts = request.getParameterValues("numberParts");
            if (selectedParts != null && numberParts != null) {
                OrderReplacementPartDao orderReplacementPartDao = (OrderReplacementPartDao) request.getServletContext()
                        .getAttribute("OrderReplacementPartDao");
                updateOrderPartsList(orderId, selectedParts, numberParts, orderReplacementPartDao);
                orderDao.setOrderStatus(OrderStatus.WAIT_REPLACEMENT_PARTS, orderId);
            }
            orderDao.setOrderCostWork(orderId, costWork);
        }
        return sessionUser.getUserType().getDefaultResult();
    }


    private void updateOrderPartsList(int orderId, String[] selectedParts, String[] numberParts, OrderReplacementPartDao dao) {

        List<OrderReplacementPart> lastPartsList = dao.getPartsListByOrderId(orderId);

        Map<Integer, OrderReplacementPart> lastPartsMap = new HashMap<>();
        for (OrderReplacementPart part : lastPartsList) {
            lastPartsMap.put(part.getPartId(), part);
        }

        for (int i = 0; i < selectedParts.length; i++) {
            int partId = Integer.parseInt(selectedParts[i]);
            int number = Integer.parseInt(numberParts[i]);
            if (lastPartsMap.containsKey(partId)) {
                if (lastPartsMap.get(partId).getNumberParts() != number) {
                    dao.updateOrderPart(orderId, partId, number);
                }
                lastPartsMap.remove(partId);
            } else {
                dao.createOrderPart(orderId, partId, number);
            }
        }

        if (!lastPartsMap.isEmpty()) {
            lastPartsMap.forEach((k, v) -> dao.deleteOrderPart(v.getOrderId(), v.getPartId()));
        }
    }
}
