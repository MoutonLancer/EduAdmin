package admin.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("edu_studentcourse")
public class Course {
    private Integer id;
    private String studentId;
    private String courseId;
    private String state;
}
