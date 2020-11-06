<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>잡레시피</title>
<link type="text/css" href="/resources/css/jobCSS.css" rel="stylesheet">
<script type="text/javascript" src="/resources/js/jobJS.js"></script>
<script src="/resources/js/jquery-3.1.1.js"></script>
<style type="text/css">
textarea.flexible {
	min-width: 200px;
	min-height: 50px;
}
</style>
<script type="text/javascript">
	$(function() {
		//textarea 자동 크기 조절
		$("textarea.flexible").on('keydown keyup', function() {
		  $(this).height(1).height( $(this).prop('scrollHeight')+12 );	
		});
		var upload = document.querySelector('#upload');
	    var preview = document.querySelector('#preview');
	 
	    upload.addEventListener('change',function (e) {
	        var get_file = e.target.files;

	        var image = document.createElement('img');
	        image.id = 'prev_img';
	        /* FileReader 객체 생성 */
	        var reader = new FileReader();
	 
	        /* reader 시작시 함수 구현 */
	        reader.onload = (function (aImg) {
	            console.log(1);
	 
	            return function (e) {
	                console.log(3);
	                /* base64 인코딩 된 스트링 데이터 */
	                aImg.src = e.target.result
	            }
	        })(image)
	 
	        if(get_file){
	            /* 
	                get_file[0] 을 읽어서 read 행위가 종료되면 loadend 이벤트가 트리거 되고 
	                onload 에 설정했던 return 으로 넘어간다.
	                이와 함게 base64 인코딩 된 스트링 데이터가 result 속성에 담겨진다.
	            */
	            reader.readAsDataURL(get_file[0]);
	            console.log(2);
	        }
	 		$("#preview").empty();
	        preview.appendChild(image);
	        $("#prev_img").css("height", "20vh");
	    });
	    
	});
	
	
</script>

<style type="text/css">
/* Noto Sans KR 폰트 추가 */
@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);
body {
	font-family: 'Noto Sans KR', sans-serif;
}

input, textarea {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: initial;
}
</style>

</head>
<body>
	<header>
		<jsp:include page="../include/header.jsp"></jsp:include>
	</header>
	<section>
		<!-- 전체 화면 -->
		<div style="width: 100%; display: flex; flex-wrap: nowrap; min-height: 70vh;">
			<div style="width: 15%; margin-left: 3%;">
				<div id="myPageMenu">
					<div class="mypagemenu_wrap">
						<div style="display: flex; flex-direction: column; flex-wrap: nowrap; text-align: center">
							<div onclick="location.href='settings.do'" style="padding:1%;font-size:18pt;cursor:pointer;margin-bottom:3%;
								border:1px solid #fac5a1;color:#ff6900;border-radius:10px;">
								계정
							</div>
							<div onclick="location.href='resumes.do'" style="padding:1%;font-size:18pt;cursor:pointer;margin-bottom:3%;
								border:1px solid #fac5a1;color:#ff6900;border-radius:10px;">
								프로필
							</div>
							<div onclick="location.href='reviews.do'" style="padding:1%;font-size:18pt;cursor:pointer;margin-bottom:3%;
								border:1px solid #fac5a1;color:#ff6900;border-radius:10px;">
								내 리뷰
							</div>
						</div>
					</div>
				</div>
			</div>

			<div style="width: 60%; display: flex; flex-direction: column; margin-bottom: 5%; margin-left: 5%" id="rsm_contents_wrap">
				<form action="insertResume.do" method="post" enctype="multipart/form-data">
					<div id="rsm_contents">
						<!-- 기본 정보 섹션 -->
						<div class="section_wrap" style="padding:3%;margin-bottom:5%;margin-right:auto; margin-left:auto;border:1px solid #fafafa;background-color:#fafafa;">
							<!-- 각 입력 내용 섹션 분류용 래퍼 -->
							<h2 style="font-size: 16pt; font-weight: 900; margin-bottom: 5%;">기본정보</h2>
							<input type="hidden" name="pro_no" value="${profileVO.pro_no}">
							<div class="resume_title">
								<input type="text" placeholder="이력서의 제목을 입력해주세요." name="res_title" value="${res_title}" style="width: 50%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;">
							</div>
							<div class="gender_radio rsm_half_cell">
								<!-- rsm_half_cell : 가로 기준 섹션 셀의 반을 차지 -->
								<div class="gender_header">
									<h3	style="font-size: 13pt; font-weight: 600; margin-bottom: 3%;">성별</h3>
								</div>
								<label class="btn_radio" for="gender_man">
									<input type="radio" name="pro_gender" id="gender_man" value="1" ${profileVO.pro_gender eq 1 ? "checked" : ""}>
									<span class="radio_txt">남자</span>
								</label>
								<label class="btn_radio" for="gender_woman"	style="margin-left: 15%;">
									<input type="radio"	name="pro_gender" id="gender_woman" value="2" ${profileVO.pro_gender eq 2 ? "checked" : ""}>
									<span class="radio_txt">여자</span>
								</label>
							</div>
							<div class="birth_wrap rsm_half_cell">
								<div class="birth_header">
									<h3	style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">출생연도</h3>
								</div>
								<div class="birthYear_select">
									<select id="birth_year" name="pro_birthYear" style="width: 15%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;">
										<option value="none">선택해주세요.</option>
										<option value="2006" ${profileVO.pro_birthYear eq 2006 ? "selected" : ""}>2006년</option>
										<option value="2005" ${profileVO.pro_birthYear eq 2005 ? "selected" : ""}${profileVO.pro_gender eq 2006 ? "selected" : ""}>2005년</option>
										<option value="2004" ${profileVO.pro_birthYear eq 2004 ? "selected" : ""}>2004년</option>
										<option value="2003" ${profileVO.pro_birthYear eq 2003 ? "selected" : ""}>2003년</option>
										<option value="2002" ${profileVO.pro_birthYear eq 2002 ? "selected" : ""}>2002년</option>
										<option value="2001" ${profileVO.pro_birthYear eq 2001 ? "selected" : ""}>2001년</option>
										<option value="2000" ${profileVO.pro_birthYear eq 2000 ? "selected" : ""}>2000년</option>
										<option value="1999" ${profileVO.pro_birthYear eq 1999 ? "selected" : ""}>1999년</option>
										<option value="1998" ${profileVO.pro_birthYear eq 1998 ? "selected" : ""}>1998년</option>
										<option value="1997" ${profileVO.pro_birthYear eq 1997 ? "selected" : ""}>1997년</option>
										<option value="1996" ${profileVO.pro_birthYear eq 1996 ? "selected" : ""}>1996년</option>
										<option value="1995" ${profileVO.pro_birthYear eq 1995 ? "selected" : ""}>1995년</option>
										<option value="1994" ${profileVO.pro_birthYear eq 1994 ? "selected" : ""}>1994년</option>
										<option value="1993" ${profileVO.pro_birthYear eq 1993 ? "selected" : ""}>1993년</option>
										<option value="1992" ${profileVO.pro_birthYear eq 1992 ? "selected" : ""}>1992년</option>
										<option value="1991" ${profileVO.pro_birthYear eq 1991 ? "selected" : ""}>1991년</option>
										<option value="1990" ${profileVO.pro_birthYear eq 1990 ? "selected" : ""}>1990년</option>
									</select>
								</div>
							</div>
							<div class="occu_wrap">
								<!-- 관심 직종 -->
								<div class="occu_header">
									<h3 style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">현재(관심)직종</h3>
								</div>
								<div class="select">
									<select id="occu_content" name="pro_occu" style="width: 15%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;">
										<option value="none">선택해주세요.</option>
										<option value="IT/인터넷">IT/인터넷</option>
									</select>
									<select id="subOccu_content" name="pro_subOccu"	style="width: 15%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;">
										<option value="none">선택해주세요.</option>
										<option value="JAVA">JAVA</option>
										<option value="데이터베이스">데이터베이스</option>
										<option value="네트워크">네트워크</option>
										<option value="보안">보안</option>
									</select>
								</div>
							</div>
							<div class="career_wrap">
								<div class="career_header">
									<h3	style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">총	경력</h3>
								</div>
								<div class="career_select">
									<select id="career" name="pro_career"
										style="width: 15%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;">
										<option value="none">선택해주세요.</option>
										<option value="0" ${profileVO.pro_career eq 0 ? "selected" : ""}>0</option>
										<option value="1" ${profileVO.pro_career eq 1 ? "selected" : ""}>1</option>
										<option value="2" ${profileVO.pro_career eq 2 ? "selected" : ""}>2</option>
										<option value="3" ${profileVO.pro_career eq 3 ? "selected" : ""}>3</option>
										<option value="4" ${profileVO.pro_career eq 4 ? "selected" : ""}>4</option>
										<option value="5" ${profileVO.pro_career eq 5 ? "selected" : ""}>5</option>
										<option value="6" ${profileVO.pro_career eq 6 ? "selected" : ""}>6</option>
										<option value="7" ${profileVO.pro_career eq 7 ? "selected" : ""}>7</option>
										<option value="8" ${profileVO.pro_career eq 8 ? "selected" : ""}>8</option>
										<option value="9" ${profileVO.pro_career eq 9 ? "selected" : ""}>9</option>
										<option value="10" ${profileVO.pro_career eq 10 ? "selected" : ""}>10</option>
									</select>
								</div>
							</div>
							<div class="skill_wrap">
								<div class="skill_header">
									<h3 style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">업무 및 스킬</h3>
								</div>
								<div class="skill_content">
									<input type="text" name="pro_skill"
										placeholder="(예시) java, python, 서버 구축 (업무와 관련된 자격증 및 기술명  포함)"
										style="width: 65%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;">
								</div>
							</div>
							<div class="profile_wrap">
								<div class="profile1_header">
									<h3
										style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">이름,
										연락처(필수)</h3>
								</div>
								<div class="proflie1_content">
									<div class="profile_input">
										<div class="set1">
											<!-- set1 옆에 프로필 이미지가 라벨 형식으로 붙음 -->
											<input type="text" class="input_name" name="pro_name"
												placeholder="이름"
												style="width: 15%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										</div>
										<div class="set2">
											<input type="text" class="input_email" name="pro_email"
												placeholder="이메일"
												style="width: 30%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										</div>
										<div class="set2">
											<input type="text" class="input_tel" name="pro_tel"
												placeholder="전화번호"
												style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										</div>
									</div>
									<label class="resume_addPhoto" for="resume_addPhoto_id">
										<input type="file" id="upload" name="pro_imgFile" style="width: 60%;" onclick="changeImg();">
										<span id='preview' style="text-align: right;">
											이미지를 선택해주세요.
									    </span>
									</label>
								</div>
								<div class="profile2_header">
									<h3
										style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">자기소개</h3>
								</div>
								<div class="profile2_content">
									<div class="flexible_textarea">
										<!-- class 통해서 텍스트에어리어 크기 조절 필요 -->
										<textarea class="flexible" name="pro_aboutMe"
											placeholder="경력, 경험 위주의 자기소개를 작성해주세요. 관심분야, 앞으로의 목표등의 내용도 좋습니다."
											style="width: 90%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;"></textarea>
									</div>
								</div>
							</div>
						</div>

						<!-- 학력, 경력, 활동 수상 내역, 포트폴리오 등등 입력 섹션 -->
						<div class="section_wrap" style="padding:3%;margin-bottom:5%;margin-right:auto; margin-left:auto;border:1px solid #fafafa;background-color:#fafafa;">
							<!-- 학력사항 -->
							<div class="education_header">
								<h3 style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">학력사항</h3>
							</div>
							<div class="flexible_resume">
								<div class="left_resume_flexible">
									<div class="group_date">
										<!-- <div class="month_picker"> -->
										<input type="text" name="e_edate" placeholder="년/월"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 0%;">
										<!-- </div> -->
										<span class="resume_etc">~</span>
										<!-- <div class="month_picker"> -->
										<input type="text" name="e_gdate" placeholder="년/월"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										<!-- </div> -->
									</div>
									<div class="group_check" style="margin-bottom: 1%;">
										<label class="btn_check"> <!-- 체크박스 클릭시 value 변경해주는 javascript 로직 필요 / 아래도 동일 -->
											<input id="e_attendcheck" name="e_attendcheck" type="checkbox" onclick="users_changeCkboxVal('e_attendcheck');" value="0" style=""> <!-- <span class="check_icon"></span> -->
											<span class="check_txt">재학중</span>
										</label>
									</div>
								</div>
								<div class="right_resume_flexible">
									<div class="sname">
										<input placeholder="학교명" type="text" name="e_sname"
											style="width: 30%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
									</div>
									<div class="majordegree">
										<input placeholder="전공 및 학위" type="text" name="e_majordegree"
											style="width: 30%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
									</div>
									<div class="flexible_textarea">
										<!-- class 통해서 텍스트에어리어 크기 조절 필요 -->
										<textarea class="flexible" name="e_description"
											placeholder="학력사항에 추가로 기재할 특별한 사항이 있을 경우 입력하세요. 추가로 기재할 사항이 없을 경우 입력하지 않으셔도 괜찮습니다."
											style="width: 90%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;"></textarea>
									</div>
								</div>
							</div>


							<!-- 경력사항 -->
							<div class="career_header">
								<h3
									style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">경력사항</h3>
							</div>
							<div class="flexible_resume">
								<div class="left_resume_flexible">
									<div class="group_date">
										<!-- <div class="month_picker"> -->
										<input type="text" name="car_edate" placeholder="년/월"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										<!-- </div> -->
										<span class="resume_etc">~</span>
										<!-- <div class="month_picker"> -->
										<input type="text" name="car_rdate" placeholder="년/월"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 0%;">
										<!-- </div> -->
									</div>
									<div class="group_check" style="margin-bottom: 1%;">
										<label class="btn_check">
											<input id="car_inoffice" name="car_inoffice" type="checkbox" onclick="users_changeCkboxVal('car_inoffice');" value="0"> <!-- <span class="check_icon"></span> -->
											<span class="check_txt" style="margin-bottom: 1%;">재직중</span>
										</label>
									</div>
								</div>
								<div class="right_resume_flexible">
									<div class="sname">
										<input placeholder="기업명" type="text" name="car_cname"
											style="width: 30%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
									</div>
									<div class="depart_post">
										<input placeholder="부서" type="text" name="car_department"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										<span class="rsm_dash">/</span> <input placeholder="직책"
											type="text" name="car_position"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 0%;">
									</div>
									<div class="duty" style="">
										<input placeholder="직무" type="text" name="car_duty"
											style="width: 30%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
									</div>
									<div class="flexible_textarea">
										<!-- class 통해서 텍스트에어리어 크기 조절 필요 -->
										<textarea class="flexible" name="car_workexp"
											placeholder="업무경험을 입력하세요. 추상적인 내용보다는 수치가 들어간 간결하고 객관적인 내용을 입력하는 것이 좋습니다. 예) 미디어 사업부(연간 매출 약 10억, 회원 50만 증가)의 사업전략 수립, 신규 사업 도입으로 3년간 내년 매출 약 2배 상승 기여"
											style="width: 90%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;"></textarea>
									</div>
								</div>
							</div>

							<!-- 활동 및 수상내역 -->
							<div class="actAwards_header">
								<h3
									style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">활동
									및 수상내역</h3>
							</div>
							<div class="flexible_resume">
								<div class="left_resume_flexible">
									<div class="group_date">
										<!-- <div class="month_picker"> -->
										<input type="text" name="act_sdate" placeholder="년/월"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										<!-- </div> -->
										<span class="resume_etc">~</span>
										<!-- <div class="month_picker"> -->
										<input type="text" name="act_edate" placeholder="년/월"
											style="width: 20%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
										<!-- </div> -->
									</div>
									<div class="group_check" style="margin-bottom: 1%;">
										<label class="btn_check"> <!-- 체크박스 클릭시 value 변경해주는 javascript 로직 필요 / 아래도 동일 -->
											<input id="act_awardCheck" name="act_awardCheck" type="checkbox" onclick="users_changeCkboxVal('act_awardCheck');" value="0"> <!-- <span class="check_icon"></span> -->
											<span class="check_txt">수상</span>
										</label>
									</div>
								</div>
								<div class="right_resume_flexible">
									<div class="actAwardname">
										<input placeholder="활동 및 수상명을 작성합니다." type="text"
											name="act_actawardname"
											style="width: 45%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
									</div>
									<div class="flexible_textarea">
										<textarea class="flexible" name="act_description"
											placeholder="활동 및 수상과 관련한 상세한 정보가 필요한 경우 입력하세요. 추가로 기재할 사항이 없을 경우 입력하지 않으셔도 괜찮습니다."
											style="width: 90%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;"></textarea>
									</div>
								</div>
							</div>

							<!-- 자격증 및 기타 -->
							<div class="certiOthers_header">
								<h3
									style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">자격증
									및 기타</h3>
							</div>
							<div class="certiOthers_body">
								<div class="certiOhtersName">
									<input placeholder="항목(자격증, 언어, 스킬 등 자유롭게 기재)" type="text"
										name="cer_item"
										style="width: 45%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 1%;">
								</div>
								<div class="flexible_textarea">
									<textarea class="flexible" name="cer_description"
										placeholder="항목에 해당하는 상세 내용을 입력하세요. 예) 토익/780점"
										style="width: 90%; border: 1px solid #ff6900; border-radius: 10px; padding: 1%; margin-bottom: 3%;"></textarea>
								</div>
							</div>

							<!-- 포트폴리오 파일 첨부 -->
							<div class="portfolios_header">
								<h3 style="font-size: 13pt; font-weight: 600; margin-bottom: 3%; margin-top: 3%;">포트폴리오 파일 첨부</h3>
							</div>
							<div class="portfolio_body">
								<label class="label_upload_resume"> <input type="file"
									class="input_upload_resume" name="por_file"> <span
									class="btn_upload_resume">파일선택</span>
								</label>
							</div>
						</div>
						<!-- section_wrap-end -->
					</div>
					
					<hr style="margin-bottom:1%; border:solid 1px #fac5a1">
					
					<div class="resume_btn_complete">
						<button type="submit" style="font-size: 12pt;font-weight: 600;background-color:#ff6900;width: 12%;color:white;border:0;border-radius:10px;margin-left: 85%;padding: 1%;">작성완료</button>
					</div>
				</form>
			</div>
		</div>
		&gt;
	</section>
	<footer>
		<jsp:include page="../include/footer.jsp"></jsp:include>
	</footer>

</body>
</html>