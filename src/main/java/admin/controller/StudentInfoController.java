package admin.controller;

import admin.domain.Student;
import admin.domain.protocol.R;
import admin.service.StudentInfoService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/students")
public class StudentInfoController {

        @Autowired
        private StudentInfoService studentInfoService;

        @GetMapping
        public R getAll(){
            R r = new R();
            List<Student> list = studentInfoService.getAll();
            r.setFlag(!list.isEmpty());
            r.setData(list);
            return r;
        }

        @GetMapping("/{studentId}")
        public R getByID(@PathVariable String studentId){
            R r = new R();
            Student student = studentInfoService.getByPrimaryKey(studentId);
            r.setFlag(student != null);
            r.setData(student);
            return r;
        }

        @GetMapping("_{studentId}/_{studentName}/_{department}/_{subject}/_{clas}")
        public R getByInfo(@PathVariable String studentId,@PathVariable String studentName, @PathVariable String department, @PathVariable String subject, @PathVariable String clas){
            R r = new R();
            List<Student> students = studentInfoService.getByInfo(studentId,studentName,department,subject,clas);
            r.setFlag(!students.isEmpty());
            r.setData(students);
            return r;
        }

        @GetMapping("{currentPage}/{pageSize}")
        public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
            R r = new R();
            Page<Student> page = studentInfoService.getPage(currentPage, pageSize);
            if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
                page = studentInfoService.getPage((int)page.getPages(), pageSize);
            r.setFlag(!page.getRecords().isEmpty());
            r.setData(page);
            return r;
        }





        @PostMapping
        public R save(@RequestBody Student student){
            R r = new R();
            r.setFlag(true);
            r.setData(studentInfoService.save(student));
            return r;
        }

        @DeleteMapping("{studentId}")
        public R delete(@PathVariable String studentId){
            R r = new R();
            r.setFlag(true);
            r.setData(studentInfoService.removeByPrimaryKey(studentId));
            return r;
        }

        @PutMapping
        public R update(@RequestBody Student student){
            R r = new R();
            r.setFlag(true);
            r.setData(studentInfoService.update(student));
            return r;
        }
}
