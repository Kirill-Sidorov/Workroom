package org.sidorov.workroom.datalayer.data;

import org.sidorov.workroom.datalayer.ReplacementPart;

import java.util.List;

public interface ReplacementPartDao {
    List<ReplacementPart> getReplacementPartsList();

    ReplacementPart getPartById(int id);

    void updatePart(ReplacementPart part);

    void deletePart(int partId);

    void createPart(ReplacementPart part);

    void updateInStock(int partId, int inStock);
}
