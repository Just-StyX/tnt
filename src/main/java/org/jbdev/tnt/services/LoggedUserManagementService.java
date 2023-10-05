package org.jbdev.tnt.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
public class LoggedUserManagementService {
    public String email;
    public int articleID;
    public String lastName;
    public String fullName;
}
