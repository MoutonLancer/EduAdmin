package admin.dao;

import admin.domain.Curriculum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurriculumDao extends BaseMapper<Curriculum> {
}
