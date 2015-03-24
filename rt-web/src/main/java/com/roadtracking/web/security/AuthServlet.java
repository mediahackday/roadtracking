package com.roadtracking.web.security;

import com.google.inject.Singleton;
import com.roadtracking.web.security.oauth.manager.ISecurityManager;
import com.roadtracking.web.security.oauth.model.OauthToken;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.common.base.Strings.nullToEmpty;
import static java.lang.String.format;

@Singleton
public class AuthServlet extends HttpServlet {

	public static final String URL = "/auth";

	@Inject
	private Logger logger;

    @Inject
    private ISecurityManager securityManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String code = req.getParameter("code");
        String state = nullToEmpty(req.getParameter("state"));
        if (state.isEmpty()) {
            state = "highscore.html";
        }
        String loginHint = nullToEmpty(req.getParameter("loginHint"));
        PrintWriter pw = res.getWriter();
        if (code == null) {
            res.sendRedirect(securityManager.getInitialOauthUrl(state, loginHint));
        } else if ("test".equals(state)) {
            pw.write(code);
        } else {
            res.setContentType("text/html");
            logger.info("code = {}", code);
            OauthToken oauthToken = securityManager.getOauthToken(code);
            if (oauthToken == null) {
                res.setStatus(401);
                pw.write("Access denied for this resource");
            } else {
                String email = securityManager.getUserEmail(oauthToken.getAuthHeader());
				logger.info("email = {}", email);
                logger.info("state = {}", state);
				if (securityManager.isUserAllowed(email)) {
					res.setContentType("text/html");
					pw.write("<!DOCTYPE html>");
					pw.write("<html lang='en'>");
					pw.write("<body>");
					pw.write("<script language='javascript' type='text/javascript'>");
					pw.write(format("localStorage.setItem('auth-token', '%s');", oauthToken.getAccessToken()));
					pw.write(format("localStorage.setItem('auth-expired', '%s');", oauthToken.getExpired()));
					pw.write(format("localStorage.setItem('auth-user', '%s');", email));
					pw.write(format("window.location.href='./%s';", state));
					pw.write("</script>");
					pw.write("</body>");
					pw.write("</html>");
				} else {
					res.setStatus(401);
					pw.write("Access denied for this resource for this user");
				}
			}
		}
	}
}