package backend.interceptor;

import backend.model.Correntista;
import backend.model.Papel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("/auth");
            return false;
        }

        Correntista usuario =
                (Correntista) session.getAttribute("usuario");

        String path = request.getRequestURI();

        if (path.startsWith("/correntistas")
                && usuario.getPapel() != Papel.ADMINISTRADOR) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Acesso negado."
            );

            return false;
        }

        return true;
    }
}