package cn.yangwanhao.bill.dto;

import java.io.Serializable;
import java.util.Date;

import cn.yangwanhao.model.dto.BaseQueryPageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询账单的查询入参
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/9 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryBillDto extends BaseQueryPageDto implements Serializable {

    private Date beginDate;

    private Date endDate;

    private String category;

    private String payChannel;

    private String remark;
}
