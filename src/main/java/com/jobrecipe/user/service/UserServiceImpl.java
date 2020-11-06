package com.jobrecipe.user.service;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobrecipe.user.service.dao.UserDAO;
import com.jobrecipe.user.vo.UserVO;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	/*
	 *** ȸ������ ���� ���� ***
	 */

	@Override
	public int userEmailCheck(String u_email) {
		System.out.println("userEmailCheck-service call");
		return userDAO.userEmailCheck(u_email);
	}
	
	@Override
	public void signupMember(UserVO userVO) {
		System.out.println("signupMember-service call");
		userDAO.insertMember(userVO);
	}
	
	//�α���üũ
	@Override
	public UserVO signinCheck(UserVO vo1) throws ClassNotFoundException, SQLException {
		return userDAO.signinCheck(vo1);
	}

	//�α���
	@Override
	public UserVO signin(UserVO vo1) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return userDAO.signin(vo1);
	}

	//�α׾ƿ�
	@Override
	public void logout(HttpSession session) throws ClassNotFoundException, SQLException {
		session.invalidate();
	}

	//�̸���üũ
	@Override
	public void newPwForm(String u_email) {
		userDAO.emailCheck(u_email);
	}

	@Override
	public UserVO viewMember(UserVO vo1) throws ClassNotFoundException, SQLException {
		return userDAO.viewMember(vo1);
	}

	@Override
	public void pass_change(UserVO vo1) throws Exception {	
		userDAO.pass_change(vo1);
	}

	@Override
	public int findEmail(String u_email) {
		int checkNum = userDAO.findEmail(u_email);

		return checkNum;
	}

	//ȸ��Ż��
	@Override
	public void memberDelete(UserVO vo1) throws Exception {
		userDAO.memberDelete(vo1);
		
	}

	@Override
	public void newsCheck(UserVO vo) throws Exception {
		userDAO.newsCheck(vo);
		
	}
	
}