package admin.model.VO;

import admin.model.PO.Curriculum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumVO extends BaseVO<Curriculum,CurriculumVO>{
    private Integer id;
    private String courseId;
    private String courseName;
    private String timeSlot;
    private String dayOfWeek;
    private String address;

}
