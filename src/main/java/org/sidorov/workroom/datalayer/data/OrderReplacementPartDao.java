package org.sidorov.workroom.datalayer.data;

import org.sidorov.workroom.datalayer.OrderReplacementPart;

import java.util.List;

public interface OrderReplacementPartDao {
    List<OrderReplacementPart> getPartsListByOrderId(int orderId);

    List<OrderReplacementPart> getOrderPartsWithAllParts(int orderId);

    List<OrderReplacementPart> getOrderPartsWithNumberPartsInStock(int orderId);

    List<OrderReplacementPart> getOrderPartsList(int orderId);

    void updateOrderPart(int orderId, int partId, int numberParts);

    void createOrderPart(int orderId, int partId, int numberParts);

    void deleteOrderPart(int orderId, int partId);
}
