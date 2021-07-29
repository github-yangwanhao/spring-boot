package cn.yangwanhao.boot.springboot.test.event.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V1.0
 * @since 2021/7/8 17:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMessageBean implements Serializable {

    private String messageId;

    private String messageBody;

}
