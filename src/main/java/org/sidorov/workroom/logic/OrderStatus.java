package org.sidorov.workroom.logic;

public enum OrderStatus {
    CHECK_BY_MODERATOR(1, "Check by moderator"),
    WAIT_MASTER(2, "Wait master"),
    CREATION_PARTS_LIST(3, "Creation parts list"),
    WAIT_REPLACEMENT_PARTS(4, "Wait replacement parts"),
    REPAIR_PROCESS(5, "Repair process"),
    COMPLETED(6, "Completed");

    private final int id;
    private final String name;

    OrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
