package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("edu_teacheruser")
public class TeacherUser {
    private Integer id;
    private String username;
    private String password;
    private String teacherId;
    private Date registerTime ;

}
