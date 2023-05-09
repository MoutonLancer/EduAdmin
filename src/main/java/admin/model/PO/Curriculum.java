package admin.model.PO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("edu_curriculum")
public class Curriculum {
    private Integer id;
    private String courseId;
    private String courseName;
    private String timeSlot;
    private String dayOfWeek;
    private String address;
    private String teacherId;
    private Integer credits;
}
