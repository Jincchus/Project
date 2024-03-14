package com.cyber.university.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cyber.university.dto.professor.ApplySubjectDto;
import com.cyber.university.dto.professor.ProfessorAndSubjectFormDto;
import com.cyber.university.dto.professor.SubInfoDto;
import com.cyber.university.dto.professor.UpdateProfessorInfoDto;
import com.cyber.university.dto.response.PrincipalDto;
import com.cyber.university.dto.response.ProfessorInfoDto;
import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.interfaces.ProfessorRepository;
import com.cyber.university.repository.model.User;
import com.cyber.university.utils.Define;

import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cyber.university.dto.ProfessorListForm;
import com.cyber.university.repository.model.ApplySubject;
import com.cyber.university.repository.model.Professor;

/**
 * @FileName : ProfessorService.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 11.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 교수 서비스
 */

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	

	/**
	 * @Method Name : selectProfessorInfoWithCollegeAndDepartment
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 장명근
	 * @변경이력 :
	 * @Method 설명 : 교수 내 정보 조회 서비스
	 */
	public ProfessorInfoDto selectProfessorInfoWithCollegeAndDepartment(Integer id) {

		return professorRepository.selectProfessorInfoWithCollegeAndDepartment(id);
	}
	
	
	public List<ProfessorAndSubjectFormDto> findAllProfessor() {
		return professorRepository.findProfessorAndDept();
	}

	/**
	 * @Method Name : selectProfessorInfo
	 * @작성일 : 2024. 3. 11.
	 * @작성자 : 장명근
	 * @변경이력 :
	 * @Method 설명 : 교수 개인 정보 조회
	 */
	public UpdateProfessorInfoDto selectProfessorInfo(Integer id) {

		return professorRepository.selectProfessorInfo(id);
	}
	

	/**
	 * @param professorListForm
	 * @return 교수 리스트 조회
	 */
	@Transactional
	public List<Professor> readProfessorList(ProfessorListForm professorListForm) {
		List<Professor> list = null;
		if (professorListForm.getProfessorId() != null) {
			list = professorRepository.selectByProfessorId(professorListForm);
		} else if (professorListForm.getDeptId() != null) {
			list = professorRepository.selectByDepartmentId(professorListForm);
		} else {
			list = professorRepository.selectProfessorList(professorListForm);
		}

		return list;
	}

	/**
	 * 
	 * @param studentListForm
	 * @return 교수 수
	 */
	@Transactional
	public Integer readProfessorAmount(ProfessorListForm professorListForm) {

		Integer amount = null;
		if (professorListForm.getDeptId() != null) {
			amount = professorRepository.selectProfessorAmountByDeptId(professorListForm.getDeptId());
		} else {
			amount = professorRepository.selectProfessorAmount();
		}

		return amount;
	}
	
	/**
	  * @Method Name : insertApplySubject
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 신청 
	  */
	@Transactional
	public void insertApplySubject(ApplySubjectDto dto, Integer proId) {
		
		if (selectSubName(dto.getSubName()) != null) {
			throw new CustomRestfullException("이미 존재하는 강의 입니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ApplySubject subject = new ApplySubject();
		subject.setProId(dto.getProId());
		subject.setSubName(dto.getSubName());
		subject.setProName(dto.getProName());
		subject.setSubTime(dto.getTime());
		subject.setType(dto.getType());
		subject.setSubGrade(dto.getSubGrade());
		subject.setCapacity(dto.getCapacity());
		
		int resultRowCount = professorRepository.insertApplySubject(subject);
		if (resultRowCount != 1) {
			throw new CustomRestfullException("강의 신청 실패하셨습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	  * @Method Name : selectSubName
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 강의 이름 조회
	  */
	public ApplySubjectDto selectSubName(String subName) {
		return professorRepository.selectSubName(subName);
	}
	
	/**
	  * @Method Name : selectMysub
	  * @작성일 : 2024. 3. 13.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 교수 본인 강의 조회
	  */
	public List<SubInfoDto> selectMySub(Integer professorId) {
		
		List<SubInfoDto> list =  professorRepository.selectMySub(professorId);
		
		return list != null ? list : Collections.emptyList();
	}
	
	
	/**
	  * @Method Name : selectAllSub
	  * @작성일 : 2024. 3. 14.
	  * @작성자 : 장명근
	  * @변경이력 : 
	  * @Method 설명 : 개설 된 강의 모두 조회
	  */
	public List<SubInfoDto> selectAllSub(Integer professorId) {
		
		List<SubInfoDto> list =  professorRepository.selectMySub(professorId);
		
		return list != null ? list : Collections.emptyList();
	}
	
}
