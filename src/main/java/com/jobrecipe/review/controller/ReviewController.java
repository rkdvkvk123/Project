package com.jobrecipe.review.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobrecipe.review.service.ReviewService;
import com.jobrecipe.review.vo.ReviewVO;
import com.jobrecipe.user.vo.UserVO;

@Controller
@RequestMapping("/hire/*")
public class ReviewController {

	@Resource(name="reviewService")
	private ReviewService reviewService;
	
	@RequestMapping(value="/reviewAll.do") //��� ����
	public String reviewAll(String rev_name, ReviewVO vo, HttpServletRequest request, Model model) {
		int page = 1;
		if(request.getParameter("page") != null) 
			page = Integer.parseInt(request.getParameter("page"));
		vo.setStartRow((page - 1) * 5 + 1);
		vo.setEndRow(page * 5);
		vo.setRev_name(rev_name);
		
		int total = this.reviewService.getReviewCount(rev_name); //���� ����
		ArrayList<ReviewVO> alist = this.reviewService.getAllReview(vo); //����
		String allStar = this.reviewService.getReviewStar(rev_name); //��� ����
		
		int pageCount = total / 5 + (total % 5 == 0 ? 0 : 1); //�� ������ ��
		int pageBlock = 5; //�� ���� ������ ������ �� ����
		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		model.addAttribute("rev_name", rev_name);
		model.addAttribute("alist", alist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("page", page);
		model.addAttribute("allStar", allStar);
		
		return "hire/reviews";
	}
	
	@RequestMapping(value="/InsertReviewForm.do") //���� �߰� ������
	public String insertReviewForm(String rev_name, HttpSession session, RedirectAttributes rttr, Model model) {
		UserVO loginInfo = null; 
		if(session.getAttribute("login") != null) {
			loginInfo = (UserVO)session.getAttribute("login");
		}
		System.out.println(loginInfo);
		if(loginInfo != null) {
			model.addAttribute("rev_name", rev_name);
			return "hire/InsertReviewForm";
		} else {
			rttr.addFlashAttribute("msg", "success");
			return "redirect:/sign_in.do";
		}
	}
	
	@RequestMapping(value="/insertReview.do") //���� �߰�
	public String insertReview(ReviewVO vo, HttpSession session, RedirectAttributes rttr) {
		//���ǿ��� u_no �޾ƿ��� ���� �߰�
		UserVO loginInfo = (UserVO) session.getAttribute("login");
		int u_no = loginInfo.getU_no();
		vo.setU_no(u_no);
		
		this.reviewService.insertReview(vo); 
		this.reviewService.companyStarUpdate(vo); //���� �߰��� ȸ�� ���� ��� ������Ʈ
		rttr.addFlashAttribute("msg", "success");
		rttr.addAttribute("rev_name", vo.getRev_name());
		return "redirect:/hire/reviewAll.do";
		
	}
	
	@RequestMapping(value="/reviewCntUp.do") //���� ��õ�� ����
	@ResponseBody
	public String reviewCntUp(ReviewVO vo, Model model) {
		this.reviewService.reviewCntUp(vo.getRev_no());
		ReviewVO vo1 = this.reviewService.getOneReview(vo.getRev_no());
		String rev_cnt = Integer.toString(vo1.getRev_cnt());
		
		return rev_cnt;
	}
	
	
}
