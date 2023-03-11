package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("edu_studentScore")
public class Score {
    private String courseId;
    private String courseName;
    private String teacherId;
    private String StudentId;
    private Integer score;
}
