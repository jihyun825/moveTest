package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	@NotBlank(message = "야 이름내용 채워!!!!")
	@Size(max=3)
	private String userName = "hongkd";
	@NotBlank(message = "야 비번 채워!!!!")
	private String password ="1234";
	@NotBlank(message = "야 아이디 채워!!!!")
	private String userId = "a001";
	private int coin = 100;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@Valid
	private Address address;
	@Valid
	private List<Card> cardList;
	@Email
	private String email;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private boolean foreigner;
	private String developer;
	private String nationality;
	private String cars;
	private String[] carArray;
	private List<String> carList;
	private List<String> hobbyList;
	
	private String introduction;
	private String birthday;

}
