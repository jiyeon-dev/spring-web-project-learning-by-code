<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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

                <button data-oper="modify" class="btn btn-default">Modify</button>
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

<!-- row - reply -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-comments fa-fw"></i> Reply
                <button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
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
        var bnoValue = '<c:out value="${board.no}" />';
        var replyUL = $(".chat");

        showList(1);

        function showList(page) {
            replyService.getList({ bno: bnoValue, page: page || 1 }, function (list) {
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
                showList(1);
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
                showList(1);
            });
        });

        // 댓글 삭제 버튼 클랙 이벤트
        modalRemoveBtn.on("click", function (e) {
            var rno = modal.data("rno");

            replyService.remove(rno, function (result) {
                alert(result);
                modal.modal("hide");
                showList(1);
            });
        });

    });
</script>