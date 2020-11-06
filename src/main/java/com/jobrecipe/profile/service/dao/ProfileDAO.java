package com.jobrecipe.profile.service.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.jobrecipe.profile.vo.ActAwardVO;
import com.jobrecipe.profile.vo.CareerVO;
import com.jobrecipe.profile.vo.CertiOtherVO;
import com.jobrecipe.profile.vo.EducationVO;
import com.jobrecipe.profile.vo.PortfolioVO;
import com.jobrecipe.profile.vo.ProfileVO;
import com.jobrecipe.profile.vo.ResumeVO;
import com.jobrecipe.review.vo.ReviewVO;
import com.jobrecipe.user.vo.UserVO;

@Repository
public interface ProfileDAO {
	
	public void insertProfile(ProfileVO profileVO);
		
	public ArrayList<ResumeVO> getAllResumes(int u_no);
	
	public ProfileVO getProfile(int u_no);
	
	public ArrayList<EducationVO> getEducation(int e_no);
	
	public ArrayList<CareerVO> getCareer(int car_no);
	
	public ArrayList<ActAwardVO> getActAward(int act_no);
	
	public ArrayList<CertiOtherVO> getCertiOther(int cer_no);
	
	public ArrayList<PortfolioVO> getPortfolio(int por_no);
	
	//�̷¼����� �⺻ ���� ������ rec_profiles ���̺��� �ش� ������ ������Ʈ
	public void updateProfile(ProfileVO profileVO);
	
	//�̷¼� �Է� : �з»���
	public void insertEducation(EducationVO educationVO);
	
	//�̷¼� �Է� : ��»���
	public void insertCareer(CareerVO careerVO);
	
	//�̷¼� �Է� : Ȱ�� �� ���󳻿�
	public void insertActAward(ActAwardVO actAwardVO);
	
	//�̷¼� �Է� : �ڰ��� �� ��Ÿ
	public void insertCertiOther(CertiOtherVO certiOtherVO);
	
	//�̷¼� �Է� : ��Ʈ������
	public void insertPortfolio(PortfolioVO portfolioVO);
	
	//�̷¼� ���� ���� �Է�
	public void insertResume(ResumeVO resumeVO);
	
	//Ư�� �̷¼��� ���� ������ �ҷ���
	public ResumeVO getResumeInfo(int res_no);
	
	//�̷¼� ���� : �з»���
	public void updateEducation(EducationVO educationVO);
	
	//�̷¼� ���� : ��»���
	public void updateCareer(CareerVO careerVO);
	
	//�̷¼� ���� : Ȱ�� �� ���󳻿�
	public void updateActAward(ActAwardVO actAwardVO);
	
	//�̷¼� ���� : �ڰ��� �� ��Ÿ
	public void updateCertiOther(CertiOtherVO certiOtherVO);
	
	//�̷¼� ���� : ��Ʈ������
	public void updatePortfolio(PortfolioVO portfolioVO);
	
	//�̷¼� ���� ���� ����
	public void updateResume(ResumeVO resumeVO);
	
	//�з»��� ����
	public void deleteEducation(int e_no);
	
	//��»��� ����
	public void deleteCareer(int car_no);
	
	//Ȱ�� �� ���󳻿� ����
	public void deleteActAward(int act_no);
	
	//�ڰ��� �� ��Ÿ ����
	public void deleteCertiOther(int cer_no);
	
	//��Ʈ������ ����
	public void deletePortfolio(int por_no);
	
	//�̷¼� ����
	public void deleteResume(int res_no);
	
	//�ۼ���
	public void drafts1(UserVO vo);
	
	//�ȷ��ױ��
	public void job_applicants1(UserVO vo);
	
	//�̸��� ���� üũ
	public void newsCheck(UserVO vo);
	
	//��й�ȣ ����
	public void pass_change(UserVO vo1)throws Exception;
	
	//����˻�
	public String searchc(String rev_name);
	
	//���� �� ��ü ����
	public ArrayList<ReviewVO> getAllReview(int u_no) throws Exception;
	
	//���� �� ��ü ���� ���� Ȯ��
	public int getAllRevCount(int u_no) throws Exception;
	
	//Ư�� ���� �ҷ�����
	public ReviewVO getReview(int rev_no);
	
	//���� ������Ʈ
	public void updateReview(ReviewVO vo1);
	
	//���� ����
	public void deleteReview(int rev_no);
	
	
}
