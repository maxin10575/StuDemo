package ConcurrencyTest;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/***
* @Description:
* @Author: maxin
* @Date: 2020/3/25
* @Modified By:
* @Version: 1.0.0
*/
public class HashMapTest {
    @Test
    public void testHashMapUse() {
        Map<Integer, User> userMap = new HashMap<>();

        User zhangsan = new User(1, "zhangsan");
        User lisi = new User(2, "lisi");
        userMap.put(zhangsan.getUserId(), zhangsan);
        userMap.put(lisi.getUserId(), lisi);

        User user1 = userMap.get(1);
        System.out.println(JSONObject.toJSONString(user1));

    }

    @Data
    public class User {
        private Integer userId;
        private String userName;

        public User(Integer userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }
    }
}
