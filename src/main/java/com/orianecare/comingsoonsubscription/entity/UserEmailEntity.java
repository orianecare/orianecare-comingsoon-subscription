/**
 * 
 */
package com.orianecare.comingsoonsubscription.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vennelakanti
 *
 */

@Entity
@Table(name = "CMNG_SOON_USER_EMAILS")
@Getter
@Setter
public class UserEmailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_ID")
	private Integer id;
	
	@Column(name = "SUB_EMAIL", unique = true, length = 50)
	private String email;

}
