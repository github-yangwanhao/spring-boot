package cn.yangwanhao.bill.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账单列表VO
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/9 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillListVo implements Serializable {

    private Date happenDate;

    private String category;

    private String categoryName;

    private String payChannel;

    private String payChannelName;

    private BigDecimal amount;

    private String remark;
}
