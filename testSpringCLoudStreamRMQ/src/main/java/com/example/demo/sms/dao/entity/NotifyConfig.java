package com.example.demo.sms.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>*********************************************************************</p>
 * <p>(类注释，类作用)</p>
 * <p>NotifyConfig </p>
 *
 * <p>HIK所有，</p>
 * <p>受到法律的保护，任何公司或个人，未经授权不得擅自拷贝。</p>
 *
 * @author zhouqingfei <br>
 * @copyright Copyright: 2015-2020
 * @date 2020-07-06 15:34:28
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user   :{修改人}
 * <p>**********************************************************************</p>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "q_notify_config")
public class NotifyConfig {
  @Id
  @Column(name ="id")
  private Integer id;
  @Column(name = "business_id")
  private String businessId;
  @Column(name = "content")
  private String content;
  @Column(name = "phones")
  private String phones;
  @Column(name = "notify_flag")
  private String notifyFlag;
}
