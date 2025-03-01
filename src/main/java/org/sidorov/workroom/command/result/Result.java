package org.sidorov.workroom.command.result;

public abstract class Result {

    private final String resource;

    public Result(String resource) {
        this.resource = resource;
    }

    public abstract ResponseType getResponseType();

    public String getResource() {
        return resource;
    }
}
