$(function () {
    var zblogNickName=Core.getCookie("zblog-username");
    var zblogQQ=Core.getCookie("zblog-qq");
    var zblogEmail=Core.getCookie("zblog-email");
    if(zblogNickName!=""){
        $("#user-name-content").show();
        $("#user-name").text(zblogNickName);
        $("#nickname").val(zblogNickName);
        $("#qq").val(zblogQQ);
        $("#email").val(zblogEmail);
    }else{
        $("#user-info").show();
    }
    var simplemde = new SimpleMDE({
        element: document.getElementById("comment-textarea"),
        toolbar: [],
        autoDownloadFontAwesome: false,
        placeholder: "说点什么好呢",
        renderingConfig: {
            codeSyntaxHighlighting: true
        },
        tabSize: 4,
        status:false
    });
    $("#qq").blur(function(){
        var qq=$("#qq").val();
        var nickname=$("#nickname").val();
        if(nickname.length==0&&qq.length > 0){
            if(!isNaN(qq)){
                Core.getQqInfo(qq,function (data) {
                    $("#nickname").val(data.nickname);
                });
            }else{
                layer.msg("qq格式不正确！")
            }
        }
    });

    function init(pageNumber) {
        Core.postAjax("/blog/api/comments",{"sid":sid,"pageNumber": (pageNumber==null? 1 : pageNumber),"status":1},function (data) {
            var commentOne="";
            if(data.list.length==0){
                commentOne+='<div class="no-comment">暂无评论，快来占领宝座</div>';
                $("#comment-ul").append(commentOne);
            }else{
                $.each(data.list,function (index,value) {
                    commentOne +=
                        '<li>'+
                        '	<div class="comment-body" id="comment-'+value.id+'">'+
                        '		<div class="comment-user-img">'+
                        '			<img src="'+(value.avatar||"/img/user-default.png")+'" onerror="this.src=\'/img/user-default.png\'" />'+
                        '		</div>'+
                        '		<div class="comment-info">'+
                        '			<div class="comment-top">'+
                        '				<span class="comment-nickname">'+
                        '					<a href="javascript:void(0)">'+value.nickname+'</a>'+
                        '				</span>'+
                        '				<span class="comment-time">'+value.createTime+
                        '				</span>'+
                        '			</div>'+
                        '           <div class="comment-content">';
                    if(value.parent!=null){
                        commentOne +=
                            '<div class="comment-parent">'+
                            '				<div class="comment-parent-user"><a class="comment-link" data-link="comment-'+value.parent.id+'">@'+value.parent.nickname+'</a></div>'+
                            '				<div class="comment-parent-content">'+value.parent.content+'</div>'+
                            '			</div> ';
                    }

                    commentOne +=value.content+
                        '			</div>'+
                        '			<div class="comment-footer">'+
                        '				<span class="reply mr-5" reply-id="'+value.id+'">回复</span>'+
                        '				<span class="cancel-reply mr-5" style="display: none;">取消回复</span>'+
                        '				<span class="comment-support pointer fa fa-thumbs-o-up" biz-id="'+value.id+'">'+value.loveCount+'</span>'+
                        '			</div>'+
                        '		</div>'+
                        '	</div> '+
                        '</li>';
                })
                $("#comment-more").remove();
                if(data.hasNextPage){
                    commentOne+='<div id="comment-more" data-page="'+data.nextPage+'" class="comment-more">加载更多</div>'
                }
                $("#comment-ul").append(commentOne);
                /*加载更多*/
                $("#comment-more").click(function () {
                    init($(this).attr("data-page"));
                })
                /*link至评论*/
                $(".comment-link").click(function () {
                    var commentLinkId = $(this).attr("data-link");
                    $("html,body").animate({
                        scrollTop:$("#"+commentLinkId).offset().top-55},{duration: 300,easing: "swing"})
                })
                
                $(".reply").click(function () {
                    var replyId=$(this).attr("reply-id");
                    if($("#reply-comment-form").length>0){
                        $replyForm=$("#reply-comment-form");
                        $("#reply-comment-form").remove();
                        $(this).parent().after($replyForm);
                        $("#reply-comment-form").show();
                        $(".reply[style='display: none;']").next().hide();
                        $(".reply[style='display: none;']").show();
                        $("#replyId").val(replyId);
                    }else{
                        var replyForm =
                            '<form id="reply-comment-form" class="form-horizontal mt-10">'+
                            '   <input name="sid" type="hidden" value="'+sid+'"  />'+
                            '   <input id="replyId" name="pid" type="hidden" value="'+replyId+'"  />'+
                            '   <div class="form-group" style="display: '+(zblogNickName==""?"block":"none")+'">'+
                            '       <div class="col-sm-4">'+
                            '           <input id="reply-nickname" value="'+zblogNickName+'"  type="text" class="form-control" name="nickname" placeholder="昵称(必填)" />'+
                            '       </div>'+
                            '       <div class="col-sm-4">'+
                            '           <input id="reply-qq" value="'+zblogQQ+'" type="text" class="form-control" name="qq" placeholder="QQ（可获取头像和昵称）" />'+
                            '       </div>'+
                            '       <div class="col-sm-4">'+
                            '           <input id="reply-email" value="'+zblogEmail+'" type="text" class="form-control" name="email" placeholder="邮箱" />'+
                            '       </div>'+
                            '   </div>'+
                            '   <div class="form-group">'+
                            '       <div class="col-xs-12">'+
                            '           <textarea name="content" id="reply-comment-textarea"></textarea>'+
                            '       </div>'+
                            '   </div>'+
                            '   <div>'+
                            '   <button id="submitReplyCommentBtn" type="button" class="btn btn-primary">发表评论</button>'+
                            '   </div>'+
                            '</form>'
                        $(this).parent().after(replyForm);
                        var replySimplemde = new SimpleMDE({
                            element: document.getElementById("reply-comment-textarea"),
                            toolbar: [],
                            autoDownloadFontAwesome: false,
                            placeholder: "说点什么好呢",
                            renderingConfig: {
                                codeSyntaxHighlighting: true
                            },
                            tabSize: 4,
                            status:false
                        });

                        $("#reply-qq").blur(function(){
                            var qq=$("#reply-qq").val();
                            var nickname=$("#reply-nickname").val();
                            if(nickname.length==0&&qq.length > 0){
                                if(!isNaN(qq)){
                                    Core.getQqInfo(qq,function (data) {
                                        $("#reply-nickname").val(data.nickname);
                                    });
                                }else{
                                    layer.msg("qq格式不正确！")
                                }
                            }
                        });
                    }
                    $(this).hide();
                    $(this).next().show();
                    $("#submitReplyCommentBtn").on('click',function () {
                        if($("#reply-nickname").val()==""){
                            layer.msg("请输入昵称")
                            return;
                        }else if(replySimplemde.value()==""){
                            layer.msg("说点什么吧")
                            return;
                        }
                        $("#reply-comment-textarea").val(replySimplemde.markdown(replySimplemde.value()));
                        Core.postAjax("/blog/api/comment/save",$("#reply-comment-form").serialize(),function (data) {
                            if(Core.getCookie("zblog-username")==""){
                                Core.setCookie("zblog-username",$("#reply-nickname").val(),30);
                                Core.setCookie("zblog-qq",$("#reply-qq").val(),30);
                                Core.setCookie("zblog-email",$("#reply-email").val(),30);
                            }
                            layer.msg(data.msg, {
                                offset: '30%',
                                time: 800
                            }, function () {
                                if(data.status==200){
                                    location.reload();
                                }
                            });
                        })
                    })
                })
                $(".cancel-reply").click(function () {
                    $("#reply-comment-form").hide();
                    $(this).hide();
                    $(this).prev().show();
                })
                
                $(".comment-support").click(function () {
                    $thisLove = $(this);
                    Core.postAjax("/blog/api/love",{"bizId":$(this).attr("biz-id"),"bizType":2},function (data) {
                        if(data.status==200){
                            $thisLove.text(parseInt($thisLove.text())+1);
                        }
                    });
                })
            }

        })
    }
    init();
    
    /*提交评论*/
    $("#submitCommentBtn").click(function () {
        if($("#nickname").val()==""){
            layer.msg("请输入昵称")
            return;
        }else if(simplemde.value()==""){
            layer.msg("说点什么吧")
            return;
        }
        $("#comment-textarea").val(simplemde.markdown(simplemde.value()));
        Core.postAjax("/blog/api/comment/save",$("#comment-form").serialize(),function (data) {
            layer.msg(data.msg, {
                offset: '30%',
                time: 800
            }, function () {
                if(data.status==200){
                    if(zblogNickName!=$("#nickname").val()||zblogQQ!=$("#qq").val()||zblogEmail!=$("#email").val()){
                        Core.setCookie("zblog-username",$("#nickname").val(),30);
                        Core.setCookie("zblog-qq",$("#qq").val(),30);
                        Core.setCookie("zblog-email",$("#email").val(),30);
                    }
                    location.reload();
                }
            });
        })
    })
    /*点击用户名*/
    $("#user-name").click(function () {
        if($("#user-info").hasClass("user-show")){
            $("#user-info").slideUp();
            $("#user-info").removeClass("user-show");
        }else{
            $("#user-info").slideDown();
            $("#user-info").addClass("user-show");
        }

    })
})

