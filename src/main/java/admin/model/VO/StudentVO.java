package admin.model.VO;

import admin.model.PO.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO extends BaseVO<Student,StudentVO>{
    private String studentId;
    private String studentName;
    private String department;
    private String subject;
    private String clas;
}
