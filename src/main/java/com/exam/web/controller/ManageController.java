package com.exam.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.web.common.ExamConst;
import com.exam.web.model.Comment;
import com.exam.web.model.Contest;
import com.exam.web.model.Grade;
import com.exam.web.model.Post;
import com.exam.web.model.Question;
import com.exam.web.model.Subject;
import com.exam.web.model.User;
import com.exam.web.service.CommentService;
import com.exam.web.service.ContestService;
import com.exam.web.service.GradeService;
import com.exam.web.service.PostService;
import com.exam.web.service.QuestionService;
import com.exam.web.service.SubjectService;
import com.exam.web.service.UserService;

@Controller
@RequestMapping(value = "/manage")
public class ManageController {

	private static Log LOG = LogFactory.getLog(ManageController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ContestService contestService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;

	/**
	 * 管理员登录页
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);

		if (currentUser == null) {
			return "/manage/manage-login";
		} else {
			return "redirect:/manage/contest/list";
		}
	}

	/**
	 * 用户管理
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String userList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			// return "redirect:/";
			return "/error/404";
		} else {
			Map<String, Object> data = userService.getUsers(page, ExamConst.userPageSize);
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-userList";
		}
	}

	/**
	 * 考试管理
	 */
	@RequestMapping(value = "/contest/list", method = RequestMethod.GET)
	public String contestList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = contestService.getContests(page, ExamConst.contestPageSize);
			List<Subject> subjects = subjectService.getSubjects();
			data.put("subjects", subjects);
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-contestBoard";
		}
	}

	/**
	 * 考试管理-查看试题
	 */
	@RequestMapping(value = "/contest/{contestId}/problems", method = RequestMethod.GET)
	public String contestProblemList(HttpServletRequest request, @PathVariable("contestId") Integer contestId,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = new HashMap<>();
			List<Question> questions = questionService.getQuestionsByContestId(contestId);
			Contest contest = contestService.getContestById(contestId);
			data.put("questionsSize", questions.size());
			data.put("questions", questions);
			data.put("contest", contest);
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-editContestProblem";
		}
	}

	/**
	 * 題目管理
	 */
	@RequestMapping(value = "/question/list", method = RequestMethod.GET)
	public String questionList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "content", defaultValue = "") String content, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = questionService.getQuestionsByContent(page, ExamConst.questionPageSize, content);
			List<Question> questions = (List<Question>) data.get("questions");
			List<Subject> subjects = subjectService.getSubjects();
			Map<Integer, String> subjectId2name = subjects.stream()
					.collect(Collectors.toMap(Subject::getId, Subject::getName));
			for (Question question : questions) {
				question.setSubjectName(subjectId2name.getOrDefault(question.getSubjectId(), "未知科目"));
			}
			data.put("subjects", subjects);
			data.put("content", content);
			model.addAttribute("data", data);
			return "/manage/manage-questionBoard";
		}
	}

	/**
	 * 成绩管理-考试列表
	 */
	@RequestMapping(value = "/result/contest/list", method = RequestMethod.GET)
	public String resultContestList(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = contestService.getContests(page, ExamConst.contestPageSize);
			List<Subject> subjects = subjectService.getSubjects();
			data.put("subjects", subjects);
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-resultContestBoard";
		}
	}

	/**
	 * 成绩管理-考试列表-学生成绩列表
	 */
	@RequestMapping(value = "/result/contest/{contestId}/list", method = RequestMethod.GET)
	public String resultStudentList(HttpServletRequest request, @PathVariable("contestId") int contestId,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = new HashMap<>();
			List<Grade> grades = gradeService.getGradesByContestId(contestId);
			Contest contest = contestService.getContestById(contestId);
			List<Question> questions = questionService.getQuestionsByContestId(contestId);
			List<Integer> studentIds = grades.stream().map(Grade::getStudentId).collect(Collectors.toList());
			List<User> students = userService.getUsersByStudentIds(studentIds);
			Map<Integer, User> id2student = students.stream().collect(Collectors.toMap(User::getId, user -> user));
			for (Grade grade : grades) {
				User student = id2student.get(grade.getStudentId());
				grade.setStudent(student);
			}
			data.put("grades", grades);
			data.put("contest", contest);
			data.put("questions", questions);
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-resultStudentBoard";
		}
	}

	/**
	 * 课程管理
	 */
	@RequestMapping(value = "/subject/list", method = RequestMethod.GET)
	public String subjectList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = subjectService.getSubjects(page, ExamConst.subjectPageSize);
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-subjectBoard";
		}
	}

	/**
	 * 帖子管理
	 */
	@RequestMapping(value = "/post/list", method = RequestMethod.GET)
	public String postList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			// return "redirect:/";
			return "/error/404";
		} else {
			Map<String, Object> data = postService.getPosts(page, ExamConst.postPageSize);
			List<Post> posts = (List<Post>) data.get("posts");
			Set<Integer> authorIds = posts.stream().map(Post::getAuthorId)
					.collect(Collectors.toCollection(HashSet::new));
			List<User> authors = userService.getUsersByIds(authorIds);
			Map<Integer, User> id2author = authors.stream().collect(Collectors.toMap(User::getId, user -> user));
			for (Post post : posts) {
				post.setAuthor(id2author.get(post.getAuthorId()));
			}
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-postBoard";
		}
	}

	/**
	 * 评论管理
	 */
	@RequestMapping(value = "/comment/list", method = RequestMethod.GET)
	public String commentList(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		// TODO::处理
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		if (currentUser == null || currentUser.getLevel() < 1) {
			return "/error/404";
		} else {
			Map<String, Object> data = commentService.getComments(page, ExamConst.commentPageSize);
			List<Comment> comments = (List<Comment>) data.get("comments");
			Set<Integer> userIds = comments.stream().map(Comment::getUserId)
					.collect(Collectors.toCollection(HashSet::new));
			List<User> users = userService.getUsersByIds(userIds);
			Map<Integer, User> id2user = users.stream().collect(Collectors.toMap(User::getId, user -> user));
			for (Comment comment : comments) {
				comment.setUser(id2user.get(comment.getUserId()));
			}
			model.addAttribute(ExamConst.DATA, data);
			return "/manage/manage-commentBoard";
		}
	}
}
