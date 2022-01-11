package cn.yangwanhao.bill.easyexcel.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/12/28 14:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDto implements Serializable {

    private String date;

    private String remark;

    private String category;

    private String payChannel;

    private String amount;
}
