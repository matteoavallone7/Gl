package com.example.ispw.controller;

import com.example.ispw.session.Session;

public class LogoutController {

    public void logout()  {
        Session.closeSession();
    }
}
