package admin.controller;

import admin.domain.Student;
import admin.domain.protocol.Result;
import admin.service.StudentInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/students")
public class StudentInfoController {

        @Autowired
        private StudentInfoService studentInfoService;

        @GetMapping
        public Result getAll(){
            List<Student> studentAll = studentInfoService.getAll();
            if(studentAll.isEmpty())
                return Result.EMPTY;
            return new Result<>().okResult(studentAll);
        }

        @GetMapping("/{studentId}")
        public Result getByID(@PathVariable String studentId){
            Student student = studentInfoService.getByPrimaryKey(studentId);
            if(student == null)
                return Result.EMPTY;
            return new Result<>().okResult(student);
        }

        @GetMapping("_{studentId}/_{studentName}/_{department}/_{subject}/_{clas}")
        public Result getByInfo(@PathVariable String studentId, @PathVariable String studentName, @PathVariable String department, @PathVariable String subject, @PathVariable String clas){
            List<Student> students = studentInfoService.getByInfo(studentId,studentName,department,subject,clas);
            if(students.isEmpty())
                return Result.EMPTY;
            return new Result<>().okResult(students);
        }

        @GetMapping("{currentPage}/{pageSize}")
        public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize){
            Page<Student> page = studentInfoService.getPage(currentPage, pageSize);
            if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
                page = studentInfoService.getPage((int)page.getPages(), pageSize);

            if(page.getRecords().isEmpty())
                return Result.EMPTY;
            return new Result<>().okResult(page);
        }

        @PostMapping
        public Result save(@RequestBody Student student){
            if (studentInfoService.save(student))
                return Result.SUCCESS.setMessage("添加成功");
            return Result.FAIL.setMessage("添加失败");
        }

        @DeleteMapping("{studentId}")
        public Result delete(@PathVariable String studentId){
            if (studentInfoService.removeByPrimaryKey(studentId))
                return Result.SUCCESS.setMessage("删除成功");
            return Result.FAIL.setMessage("删除失败");
        }

        @PutMapping
        public Result update(@RequestBody Student student){
            if (studentInfoService.update(student))
                return Result.SUCCESS.setMessage("修改成功");
            return Result.FAIL.setMessage("修改失败");
        }
}
