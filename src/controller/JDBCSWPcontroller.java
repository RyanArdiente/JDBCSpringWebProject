package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import web.JDBCSWPDAO;

@Controller
@SessionAttributes({"password","username"})
public class JDBCSWPcontroller
{
	@ModelAttribute("password")
	public String initPassword()
	{
		return "";
	}
	@ModelAttribute("username")
	public String initUsername()
	{
		return "";
	}
	
	@Autowired
	private JDBCSWPDAO jdbcswpdao;
	
	@RequestMapping(path="loginUser.do", method=RequestMethod.POST)
	public ModelAndView loginUser(@RequestParam("uname") String uname, @RequestParam("pword") String pword, @ModelAttribute("password") String password, @ModelAttribute("username") String username)
	{
		ModelAndView mv = new ModelAndView("menu.jsp");
		password = pword;
		username = uname;
		mv.addObject("password", password);
		mv.addObject("username", username);
		return mv;
	}
	@RequestMapping(path="search.do", method=RequestMethod.POST, params="searchby")
	public ModelAndView searchDB(@ModelAttribute("password") String password, @ModelAttribute("username") String username, @RequestParam("searchby") String searchby)
	{
		try{
		ModelAndView mv = new ModelAndView("searchresults.jsp");		
		List<List<String>> table = jdbcswpdao.searchDB(username, password, searchby); 
		List<String> headerRow = table.get(0);
		table.remove(0);
		mv.addObject("headers", headerRow);
		mv.addObject("rows", table);
		String s = "";
		return mv;
		}
		catch(Exception e){
			return new ModelAndView("searchresults.jsp","rows", null);
		}
	}
	@RequestMapping(path="update.do", method=RequestMethod.POST)
	public ModelAndView updateDB(@ModelAttribute("password") String password, @ModelAttribute("username") String username, @RequestParam("updateby") String updateby)
	{
		ModelAndView mv = new ModelAndView("update.jsp");
		mv.addObject("changes", new Integer(jdbcswpdao.updateDB(username, password, updateby)));
		return mv;
	}
	@RequestMapping(path="logout.do")
	public ModelAndView logout(@ModelAttribute("password")String password, @ModelAttribute("username")String username)
	{
		ModelAndView mv = new ModelAndView("index.html");
		password = "";
		username = "";
		mv.addObject("password", password);
		mv.addObject("username", username);
		
		return mv;
	}
}
