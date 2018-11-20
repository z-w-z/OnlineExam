package com.exam.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.web.common.AjaxResult;
import com.exam.web.common.ExamConst;
import com.exam.web.model.Comment;
import com.exam.web.model.Contest;
import com.exam.web.model.Post;
import com.exam.web.model.Question;
import com.exam.web.model.Reply;
import com.exam.web.model.Subject;
import com.exam.web.model.User;
import com.exam.web.service.CommentService;
import com.exam.web.service.ContestService;
import com.exam.web.service.PostService;
import com.exam.web.service.QuestionService;
import com.exam.web.service.ReplyService;
import com.exam.web.service.SubjectService;
import com.exam.web.service.UserService;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	private static Log LOG = LogFactory.getLog(IndexController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private ContestService contestService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReplyService replyService;

	/**
	 * 首页
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		return "/home";
	}

	/**
	 * 在线考试列表页
	 */
	@RequestMapping(value = "/contest/index", method = RequestMethod.GET)
	public String contestIndex(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		contestService.updateStateToStart();
		contestService.updateStateToEnd();
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		Map<String, Object> data = contestService.getContests(page, ExamConst.contestPageSize);
		model.addAttribute(ExamConst.DATA, data);
		return "/contest/index";
	}

	/**
	 * 在线考试页
	 */
	@RequestMapping(value = "/contest/{contestId}", method = RequestMethod.GET)
	public String contestDetail(HttpServletRequest request, @PathVariable("contestId") int contestId, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		Contest contest = contestService.getContestById(contestId);
		if (currentUser == null || contest.getStartTime().getTime() > System.currentTimeMillis()
				|| contest.getEndTime().getTime() < System.currentTimeMillis()) {
			return "redirect:/contest/index";
		}
		List<Question> questions = questionService.getQuestionsByContestId(contestId);
		for (Question question : questions) {
			question.setAnswer("");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("contest", contest);
		data.put("questions", questions);
		model.addAttribute(ExamConst.DATA, data);
		return "/contest/detail";
	}

	/**
	 * 题库中心页
	 */
	@RequestMapping(value = "/problemset/list", method = RequestMethod.GET)
	public String problemSet(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		Map<String, Object> data = subjectService.getSubjects(page, ExamConst.subjectPageSize);

		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		model.addAttribute(ExamConst.DATA, data);
		return "/problem/problemset";
	}

	/**
	 * 题目列表页
	 */
	@RequestMapping(value = "/problemset/{problemsetId}/problems", method = RequestMethod.GET)
	public String problemList(HttpServletRequest request, @PathVariable("problemsetId") Integer problemsetId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "difficulty", defaultValue = "0") int difficulty, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		Map<String, Object> data = questionService.getQuestionsByProblemsetIdAndContentAndDiffculty(page,
				ExamConst.questionPageSize, problemsetId, content, difficulty);
		Subject subject = subjectService.getSubjectById(problemsetId);
		data.put("subject", subject);
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		model.addAttribute(ExamConst.DATA, data);
		return "/problem/problemlist";
	}

	/**
	 * 题目详情页
	 */
	@RequestMapping(value = "/problemset/{problemsetId}/problem/{problemId}", method = RequestMethod.GET)
	public String problemDetail(HttpServletRequest request, @PathVariable("problemsetId") Integer problemsetId,
			@PathVariable("problemId") Integer problemId, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		Map<String, Object> data = new HashMap<>();
		Question question = questionService.getQuestionById(problemId);
		Subject subject = subjectService.getSubjectById(problemsetId);
		data.put("question", question);
		data.put("subject", subject);
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		model.addAttribute(ExamConst.DATA, data);
		return "/problem/problemdetail";
	}

	/**
	 * 讨论区首页
	 */
	@RequestMapping(value = "/discuss", method = RequestMethod.GET)
	public String discuss(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);

		Map<String, Object> data = postService.getPosts(page, ExamConst.postPageSize);
		List<Post> posts = (List<Post>) data.get("posts");
		Set<Integer> authorIds = posts.stream().map(Post::getAuthorId).collect(Collectors.toCollection(HashSet::new));
		List<User> authors = userService.getUsersByIds(authorIds);
		Map<Integer, User> id2author = authors.stream().collect(Collectors.toMap(User::getId, user -> user));
		for (Post post : posts) {
			post.setAuthor(id2author.get(post.getAuthorId()));
		}
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		model.addAttribute(ExamConst.DATA, data);
		return "/discuss/discuss";
	}

	/**
	 * 帖子详情页
	 */
	@RequestMapping(value = "/discuss/{postId}", method = RequestMethod.GET)
	public String discussDetail(HttpServletRequest request, @PathVariable("postId") Integer postId, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);

		Map<String, Object> data = new HashMap<>();
		Post post = postService.getPostById(postId);
		User author = userService.getUserById(post.getAuthorId());
		post.setAuthor(author);
		data.put("post", post);

		List<Comment> comments = commentService.getCommentsByPostId(postId);
		List<Reply> replies = replyService.getReliesByPostId(postId);
		Set<Integer> userIds = new HashSet<>();
		for (Comment comment : comments) {
			comment.setReplies(new ArrayList<>());
			userIds.add(comment.getUserId());
		}
		for (Reply reply : replies) {
			userIds.add(reply.getUserId());
			userIds.add(reply.getAtuserId());
		}
		List<User> users = userService.getUsersByIds(userIds);
		Map<Integer, User> id2user = users.stream().collect(Collectors.toMap(User::getId, user -> user));
		for (Comment comment : comments) {
			comment.setUser(id2user.get(comment.getUserId()));
		}
		for (Reply reply : replies) {
			reply.setUser(id2user.get(reply.getUserId()));
			if (reply.getAtuserId() != 0) {
				reply.setAtuser(id2user.get(reply.getAtuserId()));
			}
		}
		Map<Integer, Comment> id2comment = comments.stream()
				.collect(Collectors.toMap(Comment::getId, comment -> comment));
		for (Reply reply : replies) {
			Comment comment = id2comment.get(reply.getCommentId());
			comment.getReplies().add(reply);
		}
		data.put("comments", comments);
		if (currentUser != null) {
			data.put("userId", currentUser.getId());
		} else {
			data.put("userId", 0);
		}

		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		model.addAttribute(ExamConst.DATA, data);
		return "/discuss/discussDetail";
	}

	/**
	 * 发布帖子页
	 */
	@RequestMapping(value = "/discuss/post", method = RequestMethod.GET)
	public String postDiscuss(HttpServletRequest request, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		Map<String, Object> data = new HashMap<>();
		data.put("authorId", currentUser.getId());
		model.addAttribute(ExamConst.CURRENT_USER, currentUser);
		model.addAttribute(ExamConst.DATA, data);
		return "/discuss/postDiscuss";
	}

	/**
	 * 获取服务器端时间,防止用户篡改客户端时间提前参与考试
	 *
	 * @return 时间的json数据
	 */
	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult time() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return new AjaxResult().setData(localDateTime);
	}

	/**
	 * 测试分布式一致性session
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/uid", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return new AjaxResult().setData(session.getId());
	}
}
