package com.rightfindpro.become.controller;

import com.rightfindpro.become.user.AdminController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    AdminController adminController = new AdminController();

    @Test
    void admin() {
       assertTrue(adminController.admin().equals("<p>Admin</p>"));
    }
}