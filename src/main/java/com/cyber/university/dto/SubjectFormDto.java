package com.cyber.university.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 
  * @FileName : SubjectFormDto.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 14. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 강의 폼 DTO
 */
@Data
public class SubjectFormDto {
	
	private Integer id;
	@NotEmpty
	@Size(min=2, max=20)
	private String name;
	@NotEmpty
	@Min(10000000)
	@Max(99999999)
	private Integer professorId;
	@Size(max = 5)
	private String roomId;
	@NotEmpty
	private Integer deptId;
	@NotEmpty
	@Size(max = 2)
	private String type;
	@NotEmpty
	private Integer subYear;
	@NotEmpty
	@Min(1)
	@Max(2)
	private Integer semester;
	@NotEmpty
	@Size(max = 1)
	private String subDay;
	@NotEmpty
	@Min(9)
	@Max(18)
	private Integer startTime;
	@NotEmpty
	@Min(9)
	@Max(18)
	private Integer endTime;
	@NotEmpty
	private Integer grades;
	@NotEmpty
	private Integer capacity;
	private Integer numOfStudent;

}
