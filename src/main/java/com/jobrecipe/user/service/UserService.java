package com.jobrecipe.user.service;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.jobrecipe.user.vo.UserVO;

public interface UserService {
	
	/*
	 *** 회원가입 관련 서비스 ***
	 */
	public int userEmailCheck(String u_email);
	
	public void signupMember(UserVO userVO);
	
	/*
	 * 로그인 및 비밀번호 찾기 관련 서비스
	 */
	
	//로그인체크
	public UserVO signinCheck(UserVO vo1) throws ClassNotFoundException, SQLException;
	
	//로그인
	public UserVO signin(UserVO vo1) throws ClassNotFoundException, SQLException;
	
	//로그아웃
	public void logout(HttpSession session) throws ClassNotFoundException, SQLException;
	
	//비밀번호찾기
	public void newPwForm(String u_email) throws ClassNotFoundException, SQLException;
	
	//비밀번호 재설정
	/*public void sendNewPwMail();*/
	
	public UserVO viewMember(UserVO vo1) throws ClassNotFoundException, SQLException;
	
    public void pass_change(UserVO vo1) throws Exception;
    
    public int findEmail(String u_email);

    //회원탈퇴
	public void memberDelete(UserVO vo1) throws Exception;
	
	//이메일수신 설정
	public void newsCheck(UserVO vo) throws Exception;
	

}
