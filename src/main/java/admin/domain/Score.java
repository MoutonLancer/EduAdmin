package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@AllArgsConstructor
@TableName("edu_studentScore")
public class Score {
    private Integer id;
    private String courseId;
    private String courseName;
    private String teacherId;
    private String StudentId;
    private Integer score;
}
