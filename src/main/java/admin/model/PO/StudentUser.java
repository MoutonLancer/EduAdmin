package admin.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("edu_studentuser")
public class StudentUser {
    private Integer id;
    private String username;
    private String password;
    private String studentId;
    private Date registerTime ;

}
