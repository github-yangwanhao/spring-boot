package cn.yangwanhao.news_mail.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @version V3.0
 * @since 2021/11/13 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaiduNewsDto implements Serializable {

    private Integer orderNum;

    private String title;

    private String desc;

    private String imageUrl;

    private String queryUrl;

    private Long hotScore;
}
