package admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@TableName("edu_leave")
public class Leave {
    private Integer id;
    private String student_id;
    private String reason;
    private Boolean state;
    private Date startTime;
    private Date endTime ;
    private Date applicationTime ;
    private Date approvalTime ;
    private String approver;

}
