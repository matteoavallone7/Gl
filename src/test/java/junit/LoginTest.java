package junit;

import com.example.ispw.bean.LoginCredentialsBean;
import com.example.ispw.controller.LoginController;
import com.example.ispw.exceptions.InvalidUserCredentialsException;
import com.example.ispw.exceptions.SessionUserException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void testCheckLogin() throws SessionUserException {
        LoginCredentialsBean loginBean = new LoginCredentialsBean("matteo71", "123");
        LoginController loginController = new LoginController();
        loginController.checkUser(loginBean);

        int expectedRole = 1;
        int actualRole = loginBean.getRole();

        assertEquals(expectedRole, actualRole); //test ha successo
    }

}