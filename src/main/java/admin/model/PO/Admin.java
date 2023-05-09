package admin.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("edu_admin")
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String code;
    private Date registerTime ;

}
