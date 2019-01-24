package com.exam.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.exam.model.Examination;
import com.exam.model.Question;
import com.exam.model.Subject;
import com.exam.model.User;
import com.exam.service.ExamQuestionService;
import com.exam.service.ExaminationService;
import com.exam.service.QuestionService;
import com.exam.service.SubjectService;
import com.exam.util.CoreConst;
import com.exam.util.PageUtil;
import com.exam.util.ResultUtil;
import com.exam.vo.ExaminationConditionVo;
import com.exam.vo.base.PageResultVo;
import com.exam.vo.base.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("exam")
public class ExaminationController {
	
	@Autowired
	private ExaminationService examService;
	
	@Autowired
	private SubjectService subjectSevice;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ExamQuestionService examQuestionSevice;

	@PostMapping("list")
	@ResponseBody
	public PageResultVo loadExam(ExaminationConditionVo examConditionVo, Integer limit, Integer offset) {
		PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
		List<Examination> examList = examService.findByCondition(examConditionVo);
		PageInfo<Examination> pages = new PageInfo<>(examList);
		return ResultUtil.table(examList, pages.getTotal());
	}
	
	@GetMapping("/add")
	public String addExam(Model model) {
		Subject subject = new Subject();
		subject.setStatus(CoreConst.STATUS_VALID);
		List<Subject> subjects = subjectSevice.selectSubjects(subject);
		List<Question> questions = questionService.select(new Question());
		model.addAttribute("subjects", JSON.toJSONString(subjects));
		model.addAttribute("questions", questions);
		return "exam/publish";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseVo add(Examination examination, Integer[]question) {
		try {
			User user = (User)SecurityUtils.getSubject().getPrincipal();
			examination.setUserId(user.getUserId());
			examination.setAuthor(user.getNickname());
			Examination exam = examService.insertExam(examination);
			examQuestionSevice.insertList(exam.getId(),question);
			return ResultUtil.success("发布考试成功");
		} catch (Exception e) {
			return ResultUtil.error("发布考试失败");
		}
	}
	
}
