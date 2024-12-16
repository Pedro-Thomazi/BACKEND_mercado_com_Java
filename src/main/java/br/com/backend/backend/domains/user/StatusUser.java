package br.com.backend.backend.domains.user;

public enum StatusUser {
    ADMIN("admin"),
    USER("user");

    private String status;

    StatusUser(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
