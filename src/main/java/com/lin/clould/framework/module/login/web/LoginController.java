package com.lin.clould.framework.module.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lin.clould.framework.common.annotation.Controller;
import com.lin.clould.framework.common.annotation.RequestMapping;
import com.lin.clould.framework.common.util.session.SessionUtil;
import com.lin.clould.framework.common.util.string.StringUtil;
import com.lin.clould.framework.common.view.View;
import com.lin.clould.framework.module.login.service.impl.LoginDao;
import com.lin.clould.framework.module.login.service.impl.LoginVO;

@Controller("login")
public class LoginController {
	
	private LoginDao dao = LoginDao.getInstance();
	
	@RequestMapping("login.do")
	public View login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("module", "login");
		String view = null;
		try {
			if(SessionUtil.sessionCheck(request) ){
				return new View(view, true);
			}else{
				String id = StringUtil.nvl(request.getParameter("id"));
				String pw = StringUtil.nvl(request.getParameter("pw"));
				
				LoginVO loginVO = dao.selectUserById(id);
				
				// 인증이 성공하면..
				if (loginVO.getId().equals(id) && loginVO.getPw().equals(pw)) {
					
					
					HttpSession session = request.getSession();
					session.setAttribute("person.sessionInfo", loginVO);
					view = "main.do";
					return new View(view, true);
				}else{
					System.out.println("인증 실패");
					throw new Exception("아이디 또는 비밀번호가 일치하지 않습니다. \\n다시 확인 후 입력해 주시기 바랍니다.");
				}
			}
		} catch (Exception e) {
			view = "login/login";
			request.setAttribute("message", e.getMessage());
		}
		
		return new View(view);
		
	}
	
	@RequestMapping("logout.do")
	public View logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		//request.setAttribute("message", "로그아웃 되었습니다.");
		//return new CommandResult("login/login");
		return new View("/framework/framework/main/main.do", true);
	}
}
