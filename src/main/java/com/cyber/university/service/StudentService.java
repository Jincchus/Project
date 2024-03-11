package com.cyber.university.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.StudentListForm;
import com.cyber.university.repository.interfaces.StudentRepository;
import com.cyber.university.repository.model.Student;

/**
  * @FileName : StudentService.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학생 서비스
  */
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	/**
	 * 
	  * @Method Name : readStudentList
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생리스트
	 */
	@Transactional
	public List<Student> readStudentList(StudentListForm studentListForm) {

		List<Student> list = null;

		if (studentListForm.getStudentId() != null) {
			list = studentRepository.selectByStudentId(studentListForm);
		} else if (studentListForm.getDeptId() != null) {
			list = studentRepository.selectByDepartmentId(studentListForm);
		} else {
			list = studentRepository.selectStudentList(studentListForm);
		}

		return list;
	}
	
	
	/**
	 * 
	  * @Method Name : readStudentAmount
	  * @작성일 : 2024. 3. 11.
	  * @작성자 : 이준혁
	  * @변경이력 : 
	  * @Method 설명 : 학생수 가져오기
	 */
	@Transactional
	public Integer readStudentAmount(StudentListForm studentListForm) {

		Integer amount = null;
		if (studentListForm.getDeptId() != null) {
			amount = studentRepository.selectStudentAmountByDeptId(studentListForm.getDeptId());
		} else {
			amount = studentRepository.selectStudentAmount();
		}

		return amount;
	}


}
