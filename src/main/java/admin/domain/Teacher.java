package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@TableName("edu_teacherInfo")
public class Teacher {
    private String teacherId;
    private String teacherName;
    private String department;
    private String position;
    private String number;
}
