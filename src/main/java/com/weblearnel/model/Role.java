package com.weblearnel.model;

import lombok.Getter;

public enum Role {
    ADMIN(0), USER(1);

    @Getter
    private final Integer value;

    Role(Integer value) {
        this.value = value;
    }

    public static Role getRole(Integer value) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role");
    }

}
