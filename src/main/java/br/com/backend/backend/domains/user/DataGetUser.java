package br.com.backend.backend.domains.user;

public record DataGetUser(
        Long id,
        String name,
        String email,
        StatusUser status,
        Boolean active
) {
    public DataGetUser(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getStatus(),
                user.getActive()
        );
    }
}
