package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@TableName("edu_studentCourse")
public class Course {
    private Integer id;
    private String studentId;
    private String courseId;
}
