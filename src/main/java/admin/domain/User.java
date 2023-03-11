package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("edu_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String position ;
    private String code ;
    private Date registerTime ;

}
