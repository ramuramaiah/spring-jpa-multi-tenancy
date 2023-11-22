package springdata.multitenancy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springdata.multitenancy.entity.User;
import springdata.multitenancy.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{tenantId}/users", method = RequestMethod.POST)
    public ResponseEntity<User> register(@PathVariable final String tenantId, @RequestBody User user) {
        log.info("Tenant id {}", tenantId);
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
