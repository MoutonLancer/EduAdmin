package admin.dao;

import admin.domain.Leave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeaveDao extends BaseMapper<Leave> {
}
