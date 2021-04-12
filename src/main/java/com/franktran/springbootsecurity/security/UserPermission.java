package com.franktran.springbootsecurity.security;

public enum UserPermission {

    STUDENT_READ("STUDENT:READ"),
    STUDENT_WRITE("STUDENT:WRITE"),
    COURSE_READ("COURSE:READ"),
    COURSE_WRITE("COURSE:WRITE");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
