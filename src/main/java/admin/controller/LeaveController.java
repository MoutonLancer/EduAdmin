package admin.controller;

import admin.Utils.MyUtils;
import admin.domain.Course;
import admin.domain.Leave;
import admin.domain.protocol.Result;
import admin.service.LeaveService;
import admin.service.StudentInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/leaves")
public class LeaveController {
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    LeaveService leaveService;

    @GetMapping
    public Result getAll(){
        List<Leave> leaveAll = leaveService.getAll();
        if(leaveAll.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(leaveAll);
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id){
        Leave leave = leaveService.getByPrimaryKey(id);
        if(leave == null)
            return Result.EMPTY;
        return new Result<>().okResult(leave);
    }

    @GetMapping("/_{studentId}/_{state}/_{startTime}/_{endTime}/_{approver}")
    public Result getByInfo(@PathVariable String studentId,@PathVariable String state, @PathVariable String startTime, @PathVariable String endTime, @PathVariable String approver){
        startTime = startTime.equals("0") ? "null":startTime;
        endTime   = endTime.equals("0") ? "null":endTime;
        List<Leave> leaves = leaveService.getByInfo(null,studentId, state, startTime, endTime, approver);
        if(leaves.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(leaves);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<Leave> page = leaveService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，改为重新查询最后一页
            page = leaveService.getPage((int)page.getPages(), pageSize);

        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }

    @PostMapping
    public Result save(@RequestBody Leave leave){
        if (!studentInfoService.studentIsExist(leave.getStudentId()))
            return Result.FAIL.setMessage("学生信息不存在");

        if (leaveService.save(leave))
            return Result.SUCCESS.setMessage("添加成功");
        return Result.FAIL.setMessage("添加失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (leaveService.removeByPrimaryKey(id))
            return Result.SUCCESS.setMessage("删除成功");
        return Result.FAIL.setMessage("删除失败");
    }

    @PutMapping
    public Result update(@RequestBody Leave leave){
        if (leaveService.update(leave))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");
    }

    @PatchMapping
    public Result updateState(@RequestBody Map<String,Object> map){
        if (leaveService.updateState((Integer) map.get("id"),(String) map.get("state")))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");

    }
}
