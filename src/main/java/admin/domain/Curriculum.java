package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@TableName("edu_curriculum")
public class Curriculum {
    private String courseId;
    private String courseName;
    private String address;
    private String teacherName;
    private Integer credits;
}
