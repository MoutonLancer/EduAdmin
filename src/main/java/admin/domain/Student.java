package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("edu_studentinfo")
public class Student {
    private String studentId;
    private String studentName;
    private String department;
    private String subject;
    private String clas;
    private String number;
    private String address;
    private String birthday;
}
