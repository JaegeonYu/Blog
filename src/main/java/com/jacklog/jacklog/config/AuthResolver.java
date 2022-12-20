package com.jacklog.jacklog.config;

import com.jacklog.jacklog.config.data.UserSession;
import com.jacklog.jacklog.domain.Session;
import com.jacklog.jacklog.exception.Unauthorized;
import com.jacklog.jacklog.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {
    private final SessionRepository sessionRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 컨트롤러 전에 만들 DTO의 파라미터와 비교하고 반환
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // User Session 넘겨받는 Controller => 인증처리
        String authorization = webRequest.getHeader("Authorization");
        if (authorization == null || authorization.equals("")) throw new Unauthorized();

        Session session = sessionRepository.findByAccessToken(authorization)
                .orElseThrow(Unauthorized::new);

        return new UserSession(session.getUser().getId());
    }
}
