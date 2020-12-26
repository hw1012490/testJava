package com.example.demo.sms.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * QNotifySms
 *
 * @author auto generated
 * @date 2020-09-11 16:09:12
 */
@Data
@Entity
@Table(name = "q_notify_sms")
public class QNotifySms implements Serializable {

	/**
	 * id
	 * nullable : false
	 * default  : null
	 */
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true)
	@JsonIgnore
	private Long id;

	/**
	 * guid
	 * nullable : false
	 * default  : null
	 */
	@Column(name = "guid", nullable = true, length = 64)
	private String guid;

	/**
	 * smsFromDown
	 * nullable : false
	 * default  : null
	 */
	@Column(name = "sms_from_down", nullable = true)
	@JsonIgnore
	private Boolean smsFromDown;

	/**
	 * businessId
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "business_id", nullable = true)
	private String businessId;

	/**
	 * content
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "content", nullable = true)
	private String content;

	/**
	 * phones
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "phones", nullable = true)
	private String phones;

	/**
	 * createTime
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "create_time", nullable = true)
	private String createTime;

	/**
	 * updateTime
	 * nullable : true
	 * default  : null
	 */
	@Column(name = "update_time", nullable = true)
	private String updateTime;
}
