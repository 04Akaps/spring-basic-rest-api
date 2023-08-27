package my.first.spring.myfirstspring.manager;


import my.first.spring.myfirstspring.type.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;

@Component
@Scope("singleton")
public class UserManager {

    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public User addUser(String name, int age) {
        if (getUserByName(name) == null) {
            User user = new User(name, age);
            users.add(user);
            return user;
        }
        return null;
    }
    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null; // 해당 이름을 가진 사용자를 찾지 못한 경우
    }

    public OptionalInt getUserAge(String name) {
        // int타입에 대해서  에러 처리를 하기 힘든경우 OptionalInt를 사용하면 됩니다.
        // 이후 해당 값을 받는 부분에서 값이 있는지 확인하려면, isPresent메서드를 사용하면 된다.
        // 이후 값을 가져오려면, getAsInt를 사용하면 된다.
        for (User user : users) {
            if( user.getUsername().equals(name) ){
                return OptionalInt.of(user.getAge());
            }
        }

        return OptionalInt.empty();
    }

    public User deleteUser(String name) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(name)) {
                iterator.remove();
                return user; // 사용자를 삭제하고 true 반환
            }
        }
        return null; // 해당 이름을 가진 사용자를 찾지 못한 경우
    }
}




