package com.iso.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {
    @NotEmpty(message = "Old password is mandatory")
    @Size(min = 8, max = 30, message = "Old password must be at least 8 characters long")
    private String oldPassword;

    @NotEmpty(message = "New password is mandatory")
    @Size(min = 8, max = 30, message = "New password must be at least 8 characters long")
    private String newPassword;

    @NotEmpty(message = "Confirm password is mandatory")
    @Size(min = 8, max = 30, message = "Confirm password must be at least 8 characters long")
    private String newPasswordRepeat;

    public PasswordUpdateRequest(String oldPassword, String newPassword, String newPasswordRepeat) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }
}
