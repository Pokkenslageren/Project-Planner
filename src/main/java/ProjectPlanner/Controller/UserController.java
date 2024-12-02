package ProjectPlanner.Controller;

import ProjectPlanner.Model.User;
import ProjectPlanner.Repository.UserRepository;
import ProjectPlanner.Service.UserService;
import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user}/home/createUser")
    public String createUser(@PathVariable("user")int userId, String username, String password, int companyId, Model model) {
        User user = userService.readUserById(userId);
//            if(user == null) {
//                model.addAttribute("Error", "user not found");
//            return "redirect:/error";
//            }
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping("/{user}/home/createUser")
    public String createUser(@PathVariable("user") int userId,@ModelAttribute User user) {
        userService.createUser(user.getUsername(),user.getPassword(), user.getUserId(),user.getCompanyId());
        return "redirect:/home";
    }
}