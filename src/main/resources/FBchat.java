import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Webhook")
public class FBchat extends HttpServlet {

    // FB accessToken
    private String accessTokenFb = "EAADJso6JZBEkBAEQSZCqGTnxb9xrzKAWyrOAw7fwtq5nMuadZAYwCnhJLyD9gtfsIB9PLc9u4ItrxZAZA1O8ChljaRc2xZBSsCT121HliBhi3qAPZBdUq5OSwZAgZAvjqVLRsFFcl5s7BOaRlFerzLpwVeTMyNZBZAMbHEFOhmOydpBOVR4eFTAKcwj";
    // 給FB認證用的Token
    private String verifyToken = "fbBotDvd"

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
