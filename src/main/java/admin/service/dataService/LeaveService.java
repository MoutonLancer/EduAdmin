package admin.service.dataService;


import admin.Utils.MyUtils;
import admin.dao.LeaveDao;
import admin.domain.Leave;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LeaveService extends ServiceImpl<LeaveDao, Leave> implements IService<Leave> {
    @Autowired
    private LeaveDao leaveDao;
    private final String primaryKey = "id";
    private final Page<Leave> page = new Page<>(1, 10);


    public Page<Leave> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return leaveDao.selectPage(page, null);
    }
    public Page<Leave> getPage() {
        return leaveDao.selectPage(page, null);
    }
    public List<Leave> getAll(){
        return leaveDao.selectList(null);
    }

    public List<Leave> getByInfo(Integer id, String studentId, String state, String startTime, String endTime, String approver){
        if (MyUtils.AllParamIsMeaningful(false,id, studentId, state, startTime, endTime, approver))
                return getPage().getRecords();
        QueryWrapper<Leave> wrapper = new QueryWrapper<Leave>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),"id", id)
                .eq( MyUtils.AllParamIsMeaningful(true, studentId), "student_id", studentId)
                .eq( MyUtils.allStrIsInt(state),"state",state)
                .ge( MyUtils.allStrIsLong(startTime),"start_time",MyUtils.strToLong(startTime))
                .le( MyUtils.allStrIsLong(endTime),"end_time",MyUtils.strToLong(endTime))
                .eq( MyUtils.AllParamIsMeaningful(true, approver),"approver",approver);
        return leaveDao.selectList(wrapper);
    }

    public Leave getByPrimaryKey(Integer key){
        QueryWrapper<Leave> wrapper = new QueryWrapper<Leave>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return this.getOne(wrapper);
    }

    public boolean removeByPrimaryKey(Integer key){
        QueryWrapper<Leave> wrapper = new QueryWrapper<Leave>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return leaveDao.delete(wrapper)>0;
    }

    public boolean update(Leave leave){
        if (leave==null) return  false;
        QueryWrapper<Leave> wrapper = new QueryWrapper<Leave>()
                .eq(primaryKey, leave.getId());
        return 1 == leaveDao.update(leave, wrapper);
    }

    public boolean updateState(Integer id, String state){
        if (id == null || state == null) return  false;
        UpdateWrapper<Leave> updateWrapper = new UpdateWrapper<Leave>()
                .eq(primaryKey, id)
                .set("state", state);
        return 1 == leaveDao.update(null,updateWrapper);
    }

}
