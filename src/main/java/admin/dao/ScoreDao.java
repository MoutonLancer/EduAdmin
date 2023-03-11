package admin.dao;

import admin.domain.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScoreDao extends BaseMapper<Score> {
}
