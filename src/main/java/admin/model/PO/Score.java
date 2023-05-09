package admin.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("edu_studentscore")
public class Score {
    private Integer id;
    private String courseId;
    private String courseName;
    private String teacherId;
    private String StudentId;
    private Integer score;
}
