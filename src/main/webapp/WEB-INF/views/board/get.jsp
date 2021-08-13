<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Read Page</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Read Page
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="form-group">
                    <label>No</label>
                    <input class="form-control" name="no" value="<c:out value='${board.no}'/>" readonly="readonly" />
                </div>
                <div class="form-group">
                    <label>Title</label>
                    <input class="form-control" name="title" value="<c:out value='${board.title}'/>" readonly="readonly" />
                </div>
                <div class="form-group">
                    <label>Text area</label>
                    <textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value='${board.content}'/></textarea>
                </div>
                <div class="form-group">
                    <label>Writer</label>
                    <input class="form-control" name="writer" value="<c:out value='${board.writer}'/>" readonly="readonly" />
                </div>

                <sec:authentication property="principal" var="pinfo" />
                <sec:authorize access="isAuthenticated()">
                    <c:if test="${pinfo.username eq board.writer}">
                        <button data-oper="modify" class="btn btn-default">Modify</button>
                    </c:if>
                </sec:authorize>
                <button data-oper="list" class="btn btn-info">List</button>

                <form id="operForm" action="/board/modify" method="get">
                    <input type="hidden" id="no" name="no" value="<c:out value='${board.no}' />" />
                    <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}' />" />
                    <input type="hidden" name="amount" value="<c:out value='${cri.amount}' />" />
                    <input type="hidden" name="type" value="<c:out value='${cri.type}'/>" />
                    <input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>" />
                </form>

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>

<!-- row - attach files -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Files</div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="uploadResult">
                    <ul></ul>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<div class="bigPictureWrapper">
    <div class="bigPicture"></div>
</div>
<!-- /.row - attach files -->

<!-- row - reply -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Reply
                <sec:authorize access="isAuthenticated()">
                    <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
                </sec:authorize>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <ul class="chat">
                    <!-- start reply -->
                    <li class="left clearfix" data-rno="12">
                        <div>
                            <div class="header">
                                <strong class="primary-font">user00</strong>
                                <small class="pull-right text-muted">2021-08-06 12:41</small>
                            </div>
                            <p>Good job!</p>
                        </div>
                    </li>
                    <!-- /.end reply -->
                </ul>
            </div>
            <!-- /.panel-body -->

            <!-- paging -->
            <div class="panel-footer"></div>
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row - reply -->


<!-- Modal - reply -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>Reply</label>
                    <input class="form-control" name="reply" value="New Reply!!!!">
                </div>
                <div class="form-group">
                    <label>Replyer</label>
                    <input class="form-control" name="replyer" value="replyer">
                </div>
                <div class="form-group">
                    <label>Reply Date</label>
                    <input class="form-control" name="replyDate" value="">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="modalModBtn" class="btn btn-warning">Modify</button>
                <button type="button" id="modalRemoveBtn" class="btn btn-danger">Remove</button>
                <button type="button" id="modalRegisterBtn" class="btn btn-primary">Register</button>
                <button type="button" id="modalCloseBtn" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
    $(document).ready(function() {
        var operForm = $("#operForm");
        $("button[data-oper='modify']").on("click", function(e) {
            operForm.submit();
        });

        $("button[data-oper='list']").on("click", function(e) {
            operForm.find("#no").remove()
            operForm.attr("action", "/board/list");
            operForm.submit();
        });
    })
</script>

<script type="text/javascript" src="/resources/js/reply.js?v=2"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // 댓글 paging
        var pageNum = 1;
        var replyPageFooter = $(".panel-footer");

        function showReplyPage(replyCnt) {
            var endNum = Math.ceil(pageNum / 10.0) * 10;
            var startNum = endNum - 9;

            var prev = startNum != 1;
            var next = false;

            if (endNum * 10 >= replyCnt) {
                endNum = Math.ceil(replyCnt / 10.0);
            }
            if (endNum * 10 < replyCnt) {
                next = true;
            }

            var str = "<ul class='pagination pull-right'>";
            if (prev) {
                str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>Previous</a></li>";
            }
            for (var i = startNum; i <= endNum; i++) {
                var active = pageNum == i ? "active" : "";

                str += "<li class='page-item " + active + "'><a class='page-link' href='" + i + "'>" + i + "</a></li>";
            }
            if (next) {
                str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'>Next</a></li>";
            }
            str += "</ul>";

            replyPageFooter.html(str);
        };

        replyPageFooter.on("click", "li a", function (e) {
            e.preventDefault();
            console.log("page click");

            var targetPageNum = $(this).attr("href");
            console.log("targetPageNum: " + targetPageNum);

            pageNum = targetPageNum;
            showList(pageNum);
        });

        var bnoValue = '<c:out value="${board.no}" />';
        var replyUL = $(".chat");

        showList(1);

        function showList(page) {
            replyService.getList({ bno: bnoValue, page: page < 1 ? 1 : page }, function (replyCnt, list) {

                console.log("replyCnt: ", replyCnt);
                console.log("list: ", list);

                if (page == -1) {
                    pageNum = Math.ceil(replyCnt / 10.0);
                    showList(pageNum);
                    return;
                }

               var str = "";
               if (list == null || list.length == 0) {
                   replyUL.html("");
                   return;
               }

               for (var i = 0, len = list.length || 0; i < len; i++) {
                   str += "<li class ='left clearfix' data-rno='" + list[i].rno + "'>";
                   str += "  <div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
                   str += "    <small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
                   str += "    <p>" + list[i].reply + "</p></div></li>";
               }
               replyUL.html(str);
               showReplyPage(replyCnt);
            });
        };

        var modal = $(".modal");
        var modalInputReply = modal.find("input[name='reply']");
        var modalInputReplyer = modal.find("input[name='replyer']");
        var modalInputReplyDate = modal.find("input[name='replyDate']");

        var modalModBtn = $("#modalModBtn");
        var modalRemoveBtn = $("#modalRemoveBtn");
        var modalRegisterBtn = $("#modalRegisterBtn");

        // 댓글 생성 버튼 클릭 이벤트
        $("#addReplyBtn").on("click", function(e) {
            modal.find("input").val("");
            modalInputReplyDate.closest("div").hide();
            modal.find("button[id !='modalCloseBtn']").hide();

            modalRegisterBtn.show();
            $(".modal").modal("show");
        });

        // 댓글 생성 버튼 클릭 이벤트
        modalRegisterBtn.on("click", function (e) {
            var reply = {
                reply: modalInputReply.val(),
                replyer: modalInputReplyer.val(),
                bno: bnoValue
            };

            replyService.add(reply, function(result) {
                alert(result);
                modal.find("input").val("");
                modal.modal("hide");
                showList(-1);
            });
        });

        // 댓글 클릭 시 모달 오픈 이벤트
        $(".chat").on("click", "li", function (e) {
            var rno = $(this).data("rno");

            replyService.get(rno, function (reply) {
                modalInputReply.val(reply.reply);
                modalInputReplyer.val(reply.replyer);
                modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
                modal.data("rno", reply.rno);

                modal.find("button[id != 'modalCloseBtn']").hide();
                modalModBtn.show();
                modalRemoveBtn.show();

                $(".modal").modal("show");
            });
        });

        // 댓글 수정 버튼 클릭 이벤트
        modalModBtn.on("click", function (e) {
            var reply = {
                rno: modal.data("rno"),
                reply: modalInputReply.val(),
            };

            replyService.update(reply, function (result) {
                alert(result);
                modal.modal("hide");
                showList(pageNum);
            });
        });

        // 댓글 삭제 버튼 클랙 이벤트
        modalRemoveBtn.on("click", function (e) {
            var rno = modal.data("rno");

            replyService.remove(rno, function (result) {
                alert(result);
                modal.modal("hide");
                showList(pageNum);
            });
        });
    });
</script>

<script type="text/javascript">
    // 첨부파일 관련
    $(document).ready(function() {
        (function() {
            var bno = '<c:out value="${board.no}" />';

            $.getJSON("/board/getAttachList", { no: bno }, function (arr) {
                console.log(arr);

                var str = "";
                $(arr).each(function (i, attach) {
                    if (attach.fileType) {
                        var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);
                        str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.image + "'><div>"
                            + "<img src='/display?fileName=" + fileCallPath + "'>"
                            + "</div></li>";
                    } else {
                        str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.image + "'><div>"
                            + "<span>" + attach.fileName + "</span>"
                            + "<img src='/resources/img/attach.png'>"
                            + "</div></li>";
                    }
                });
                $(".uploadResult ul").html(str);

            });
        })();

        /**
         * 첨부파일 클릭 이벤트
         */
        $(".uploadResult").on("click", "li", function (e) {
            console.log("view image");

            var liObj = $(this);
            var path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid") + "_" + liObj.data("filename"));

            if (liObj.data("type")) {  // 이미지인 경우 -> 원본 이미지 크게 보기
                showImage(path.replace(new RegExp(/\\/g), "/"));
            } else {  // 파일 다운로드
                self.location = "/download?fileName=" + path;
            }
        });

        /**
         * 원본 이미지 보기
         * @param fileCallPath
         */
        function showImage(fileCallPath) {
            alert(fileCallPath);

            $(".bigPictureWrapper").css("display", "flex").show();

            $(".bigPicture").html("<img src='/display?fileName=" + fileCallPath + "'>")
                .animate({width: "100%", height: "100%"}, 1000);
        };

        /**
         * 원본 이미지 닫기 이벤트
         */
        $(".bigPictureWrapper").on("click", function (e) {
            $(".bigPicture").animate({width: "0%", height: "0%"}, 1000);
            setTimeout(function (e) {
                $(".bigPictureWrapper").hide();
            }, 1000);
        });
    });
</script>

<link href="/resources/css/attach.css" rel="stylesheet">