package admin.model.VO;

import admin.model.PO.Leave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveVO extends BaseVO<Leave,LeaveVO>{
    private Integer id;
    private String reason;
    private String state;
    private Long startTime;
    private Long endTime ;
    private String approver;
}
