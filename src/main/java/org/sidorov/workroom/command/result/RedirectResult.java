package org.sidorov.workroom.command.result;

public class RedirectResult extends Result {

    public RedirectResult(String resource) {
        super(resource);
    }

    @Override
    public ResponseType getResponseType() {
        return ResponseType.REDIRECT;
    }
}
