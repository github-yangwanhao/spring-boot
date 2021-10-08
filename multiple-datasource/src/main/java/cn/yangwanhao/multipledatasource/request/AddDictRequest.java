package cn.yangwanhao.multipledatasource.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/10/8 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDictRequest implements Serializable {

    private String dictKey;

    private String dictName;

    private String dictDesc;

    private String tenantId;

    private String itemKey;

    private String itemName;

}
