package com.jobrecipe.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jobrecipe.user.service.UserServiceImpl;
import com.jobrecipe.user.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private JavaMailSender mailSender;

	@Resource(name = "UserService")
	private UserServiceImpl userService;
	
	/*
	 *** ȸ������ ***
	 */
	
	//ȸ������ ��ư�� ������ ȸ������ �������� �̵�
	@RequestMapping(value = "/sign_up.do")
	public String signUpForm() {
		return "users/sign_up";
	}
	
	
	//�̸��� �ߺ� üũ
	@RequestMapping(value = "/emailCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public int emailCheck(@RequestParam("u_email") String u_email) {
		return userService.userEmailCheck(u_email);
	}
	
	/* 
	 * ȸ������ ���ο��� ������ ������ �������� �������� �̵��ϸ�,
	 * red_users ���̺� ȸ�� ���� ����
	 * ���� ������ ���� �Է� ���� �������� ProfileController���� ó��
	 */
	@RequestMapping(value = "/signupMember.do")
	public String signupMember(UserVO userVO, Model model) {
		userService.signupMember(userVO);
		model.addAttribute("u_no", userVO.getU_no());
		//model.addAttribute("userVO", userVO);
		return "wizard/signup_questionnaire";
	}
	

	/*
	 * �̸��� ������ �����ϸ� �α����� �� ���·� ���� �������� �Ѿ��.
	 * ���н� js�� ���� �޽��� �ȳ�
	 * ���� ������ ���� ��ū �ʿ�
	 */
	@RequestMapping(value = "/authEmail.do")
	public String authEmail() {
		return "main";
	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/sign_in.do")
	public String signinForm() {
		return "users/sign_in";
	}

	// 로그인
	@RequestMapping(value = "/signinCheck.do")
	public String login(UserVO vo, HttpServletRequest request, RedirectAttributes rttr, HttpSession session)
			throws Exception {
		
		session = request.getSession();
		
		UserVO login = userService.signinCheck(vo);
		
		if (login == null) {
			session.setAttribute("login", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:/sign_in.do";
		} else {
			
			if(login.getU_email().equals("admin@jobrecipe.com")) {
				return "admin/insert_company";
			} else {
				session.setAttribute("login", login);
				session.setAttribute("u_email", login.getU_email());
				return "redirect:/";
			}
		}			
	}

	// �α׾ƿ�
	@RequestMapping("logout.do")
	public String logout(HttpSession session) throws ClassNotFoundException, SQLException {
		userService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("msg", "logout");
		return "redirect:/hire/Search_Job.do";
	}

	// ��й�ȣ ã�� �������� �̵�
	@RequestMapping(value = "/findPw.do")
	public String newPwForm(UserVO vo1) {
		return "users/password/new";
	}

	/*
	 * ��й�ȣ ã�� ���������� �̸��� �Է� �� ��й�ȣ ã�� ��ư �������� �����ϸ� ������ ���� �� �α��� �������� �̵�, �����ϸ� js�� ����
	 * �޽��� �ȳ�
	 */
	@RequestMapping(value = "sendNewPwMail.do")
	public String sendNewPwMail() {
		return "users/sign_in";
	}

	// ȸ���� �̸��Ϸ� ������ �� ��й�ȣ ���� �������� ��ũ�� �������� �Ʒ� �޼ҵ带 ���� �̵�
	@RequestMapping(value = "editPw.do")
	public String editPwForm() {
		return "users/password/edit";
	}

	//��й�ȣ ã�� ó�� (1) �̸��� �߼�
    @RequestMapping(value = "find_pass.do", method = RequestMethod.POST)
    public ModelAndView find_pass(HttpServletRequest request, String u_email,
            HttpServletResponse response_email) throws IOException{
        
        //������ ���� (������ȣ)�� �����ؼ� �̸��Ϸ� ������ �� ������ȣ�� �Է��ϸ� ��й�ȣ�� ������ �� �ִ� �������� �̵���Ŵ
    	response_email.setContentType("text/html; charset=UTF-8");
		PrintWriter out_email = response_email.getWriter();
		ModelAndView mv = new ModelAndView();    //ModelAndView�� ���� �������� �����ϰ�, ���� ���� �����Ѵ�.
		
    	String tomail = request.getParameter("u_email");    //�޴� ����� �̸���
        int checkNum = userService.findEmail(tomail);
    	
    	if(checkNum == 1) {
    		Random r = new Random();
    		int dice = r.nextInt(157211)+48271;
    		
    		String setfrom = "wjdrndkel@gmail.com";
    		String title = "��й�ȣ ã�� ���� �̸��� �Դϴ�.";    //����
    		String content =
    				
    				System.getProperty("line.separator")+
    				
    				System.getProperty("line.separator")+
    				
    				"�ȳ��ϼ��� ȸ���� ���� Ȩ�������� ã���ּż� �����մϴ�"
    				
                +System.getProperty("line.separator")+
                
                System.getProperty("line.separator")+
                
                "��й�ȣ ã�� ������ȣ�� " +dice+ " �Դϴ�. "
                
                +System.getProperty("line.separator")+
                
                System.getProperty("line.separator")+
                
                "������ ������ȣ�� Ȩ�������� �Է��� �ֽø� �������� �Ѿ�ϴ�."; // ����
    		
    		try {
    			MimeMessage message = mailSender.createMimeMessage();
    			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    			
    			messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
    			messageHelper.setTo(tomail); // �޴»�� �̸���
    			messageHelper.setSubject(title); // ���������� ������ �����ϴ�
    			messageHelper.setText(content); // ���� ����
    			
    			mailSender.send(message);
    			
    		} catch (Exception e) {
    			System.out.println(e);
    		}
    		
    		mv.setViewName("/users/pass_email");     //�����̸�
    		mv.addObject("dice", dice);
    		mv.addObject("u_email", u_email);
    		
    		System.out.println("mv : "+mv);
    		
    		out_email.println("<script>alert('�̸����� �߼۵Ǿ����ϴ�. ������ȣ�� �Է����ּ���.');</script>");
    		out_email.flush();
    			
    		return mv;
    		
    	} else {
    		mv.setViewName("/users/password/new");
    		
    		out_email.println("<script>alert('��ϵ��� ���� �̸����Դϴ�. �̸����� �ٽ� �Է����ּ���.');</script>");
    		out_email.flush();
    		
    		return mv;
    	}
    }

    //������ȣ�� �Է��� �Ŀ� Ȯ�� ��ư�� ������ �ڷᰡ �Ѿ���� ��Ʈ�ѷ�
    @RequestMapping(value = "pass_injeung.do{dice},{u_email}", method = RequestMethod.POST)
        public ModelAndView pass_injeung(String pass_injeung, @PathVariable String dice, @PathVariable String u_email, 
                HttpServletResponse response_equals) throws IOException{
        
        System.out.println("������ : pass_injeung : "+pass_injeung);
        
        System.out.println("������ : dice : "+dice);
        
        ModelAndView mv = new ModelAndView();
      
        mv.setViewName("/users/pass_change");
        
        mv.addObject("u_email",u_email);
        
        if (pass_injeung.equals(dice)) {
            
            //������ȣ�� ��ġ�� ��� ������ȣ�� �´ٴ� â�� ����ϰ� ��й�ȣ ����â���� �̵���Ų��
        
            mv.setViewName("users/pass_change");
            
            mv.addObject("u_email",u_email);
            
            //���� ������ȣ�� ���ٸ� �̸����� ��й�ȣ ���� �������� �ѱ��, Ȱ���� �� �ֵ��� �Ѵ�.
            
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('������ȣ�� ��ġ�Ͽ����ϴ�. ��й�ȣ ����â���� �̵��մϴ�.');</script>");
            out_equals.flush();
    
            return mv;
  
        }else if (pass_injeung != dice) {

            ModelAndView mv2 = new ModelAndView(); 
            
            mv2.setViewName("users/pass_email");
            
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('������ȣ�� ��ġ�����ʽ��ϴ�. ������ȣ�� �ٽ� �Է����ּ���.'); history.go(-1);</script>");
            out_equals.flush();

            return mv2;
        }    
        return mv;  
    }
    
    //������ ��й�ȣ�� �Է��� �Ŀ� Ȯ�� ��ư�� ������ �Ѿ���� ��Ʈ�ѷ�
    @RequestMapping(value = "pass_change.do{u_email}", method = RequestMethod.POST)
    public ModelAndView pass_change(UserVO vo, HttpServletResponse pass) throws Exception{
    	
    System.out.println(vo);

    userService.pass_change(vo);
    
    ModelAndView mv = new ModelAndView();
    
    mv.setViewName("users/sign_in");
    
    return mv;
                
    }
    
	// ȸ�� Ż�� get
	@RequestMapping(value="/memberDelete.do", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "/users/memberDeleteView";
	}
    
    //ȸ��Ż��
	@RequestMapping(value = "/memberDelete.do")
	public String memberDelete(UserVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		UserVO user = (UserVO) session.getAttribute("login");
		String sessionPw = user.getU_pw();
		String voPw = vo.getU_pw();
		
		if(!(sessionPw.equals(voPw))) {
			rttr.addFlashAttribute("msg",false);
			return "/users/memberDelteView";
		}
		userService.memberDelete(user);
		session.invalidate(); 
		return "redirect:/hire/Search_Job.do";
	}
	
}
