package admin.model.VO;

import org.springframework.beans.BeanUtils;

public class BaseVO<PO, VO extends BaseVO<PO, VO>> {
    @SuppressWarnings("unchecked")
    public VO toVO(PO po) {
        VO vo = null;
        try {
            vo = (VO) getClass().newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
}
