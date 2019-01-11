package com.exam.common;

public class ExamConst {

	// Session
	public final static String CURRENT_USER = "current_user";
	 //本地服务器-文件,图片所在位置,图片上传位置
    public static final String UPLOAD_FILE_PATH = "file:D:/upload/";
    public static final String UPLOAD_FILE_IMAGE_PATH = "D:/upload/images/";

	// 云服务器-文件,图片所在位置,图片上传位置
	//public static final String UPLOAD_FILE_PATH = "file:/usr/java/data/online-exam/upload/";
	//public static final String UPLOAD_FILE_IMAGE_PATH = "usr/java/data/online-exam/upload/images/";

	// 默认头像url
	public static final String DEFAULT_AVATAR_IMG_URL = "headimg_placeholder.png";
	// 课程默认图片
	public static final String DEFAULT_SUBJECT_IMG_URL = "problemset_default.jpg";

	public static final int subjectPageSize = 16;
	public static final int contestPageSize = 10;
	public static final int questionPageSize = 10;
	public static final int userPageSize = 16;
	public static final int postPageSize = 8;
	public static final int gradePageSize = 12;
	public static final int commentPageSize = 16;

	// MD5加盐
	public static final String MD5_SALT = "exam";
	// 分页数据
	public final static String DATA = "data";

	public final static String SPLIT_CHAR = "_~_";

}
