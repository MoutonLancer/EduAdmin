package admin.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("edu_teacherinfo")
public class Teacher {
    private String teacherId;
    private String teacherName;
    private String department;
    private String position;
    private String number;
}
