package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("edu_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean position;
    private String code;
    private Date registerTime ;

}
