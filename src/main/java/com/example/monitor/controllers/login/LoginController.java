package com.example.monitor.controllers.login;

import com.example.monitor.domain.forms.LoginForm;
import com.example.monitor.domain.models.User;
import com.example.monitor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index(LoginForm loginForm) {
        Map<String, Object> map = new HashMap<>();
        map.put("login", loginForm);
        return new ModelAndView("login/login", map);
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginForm loginForm, BindingResult result, HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        map.put("login", loginForm);
        Map<String, String> errMap = new HashMap<>();
        if (result.hasErrors()) {

            List<FieldError> fieldErrors = result.getFieldErrors();

            for (FieldError fe : fieldErrors) {
                String field = fe.getField();
                String message = fe.getDefaultMessage();
                errMap.put(field, message);
            }

            map.put("error", errMap);
            return new ModelAndView("login/login", map);
        }

        List<User> users = userRepository.findByUsername(loginForm.getUsername());
        if (!CollectionUtils.isEmpty(users)) {

            User user = users.get(0);
            if (loginForm.getPassword().equals(user.getPassword())) {

                request.getSession().setAttribute("currentUser", user);

                // 成功，将用户信息保存到session
                return new ModelAndView(new RedirectView("/monitor", true));
            } else {

                errMap.put("password", "用户名或密码错误");
                map.put("error", errMap);

                // 用户名和密码不匹配
                return new ModelAndView("login/login", map);
            }
        }

        errMap.put("password", "用户名或密码错误");
        map.put("error", errMap);

        // 用户名不存在
        return new ModelAndView("login/login", map);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {

        return new ModelAndView(new RedirectView("/login", true));
    }
}
