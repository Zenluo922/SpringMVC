package com.powernode.usermgt.dao;

import com.powernode.usermgt.bean.User;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserDao {
    private static List<User> users = new ArrayList<>();
    static {
        User user1 = new User(1001L,"张三",1,"你好.com");
        User user2 = new User(1002L,"张无忌",1,"1.com");
        User user3 = new User(1003L,"张三分",0,"2.com");
        User user4 = new User(1004L,"张飞",1,"3.com");
        User user5 = new User(1005L,"张翠山",1,"4.com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }
    public List<User> selectAll() {
        return users;
    }


    public Long generateId(){
        Long maxId = users.stream().map(user -> user.getId()).reduce((id1, id2) -> id1 > id2 ? id1 : id2).get();
        return maxId+1;
    }
    public void insert(User user){
        Long id = generateId();
        user.setId(id);
        users.add(user);
    }

    public User selectById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    public void update(User user){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i,user);
                return;
            }
        }
    }

    public void deleteById(Long id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.remove(i);
                return;
            }
        }
    }
}
