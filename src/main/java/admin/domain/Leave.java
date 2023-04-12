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
    private String studentId;
    private String reason;
    private String state;
    private Long startTime;
    private Long endTime ;
    private Long applicationTime ;
    private Long approvalTime ;
    private String approver;

}
