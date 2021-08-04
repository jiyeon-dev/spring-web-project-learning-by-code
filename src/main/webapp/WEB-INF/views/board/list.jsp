<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@ include file="../includes/header.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Tables</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board List Page
                <button id="regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <!-- searchFrom -->
                <div class="row">
                    <div class="col-lg-1"></div>
                    <form id="searchForm" action="/board/list" method="get">
                        <select name="type">
                            <option value="" <c:out value="${pageMaker.cri.type == null ? 'selected' : '' }"/>>--</option>
                            <c:set var="options" value="${fn:split('T,C,W,TC,TW,TCW', ',')}" />
                            <c:forEach var="opt" items="${options}">
                                <option value="${opt}" <c:out value="${pageMaker.cri.type == opt ? 'selected' : '' }"/>>
                                    <c:choose>
                                        <c:when test="${opt == 'T'.toString()}">제목</c:when>
                                        <c:when test="${opt == 'C'.toString()}">내용</c:when>
                                        <c:when test="${opt == 'W'.toString()}">작성자</c:when>
                                        <c:when test="${opt == 'TC'.toString()}">제목+내용</c:when>
                                        <c:when test="${opt == 'TW'.toString()}">제목+작성자</c:when>
                                        <c:when test="${opt == 'TCW'.toString()}">제목+내용+작성자</c:when>
                                    </c:choose>
                                </option>
                            </c:forEach>
                        </select>
                        <input type="text" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>" />
                        <input type="hidden" name="pageNum" value="<c:out value='${pageMaker.cri.pageNum}'/>" />
                        <input type="hidden" name="amount" value="<c:out value='${pageMaker.cri.amount}'/>" />

                        <button class="btn btn-default">search</button>
                    </form>
                </div>
                <!-- ./searchForm -->

                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                    <thead>
                    <tr>
                        <th>#번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${list}" var="board">
                        <tr>
                            <td><c:out value="${board.no}" /></td>
                            <td>
                                <a class="move" href="<c:out value='${board.no}' />">
                                    <c:out value="${board.title}" />
                                </a>
                            </td>
                            <td><c:out value="${board.writer}" /></td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>

                <!-- paging -->
                <div class="pull-right">
                    <ul class="pagination">
                        <c:if test="${pageMaker.prev}">
                            <li class="pagination_button previous">
                                <a href="${pageMaker.startPage - 1}">Previous</a>
                            </li>
                        </c:if>

                        <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                            <li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : ''}">
                                <a href="${num}">${num}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${pageMaker.next}">
                            <li class="pagination_button next">
                                <a href="${pageMaker.endPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <!-- /.paging -->

                <!-- paging form -->
                <form id="actionForm" action="/board/list" method="get">
                    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
                    <input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
                    <input type="hidden" name="type" value="<c:out value='${pageMaker.cri.type}'/>" />
                    <input type="hidden" name="keyword" value="<c:out value='${pageMaker.cri.keyword}'/>" />
                </form>
                <!-- /.paging-form -->

                <!-- modal -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            </div>
                            <div class="modal-body">처리가 완료되었습니다.</div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save Changes</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
    $(document).ready(function() {
        var result = '<c:out value="${result}" />';

        checkModal(result);

        history.replaceState({}, null, null);

        function checkModal(result) {
            if (!result || history.state) return;
            if (parseInt(result) > 0) {
                $(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
            }

            $("#myModal").modal("show");
        }

        // `Register New Board` Button Event
        $("#regBtn").on("click", function() {
            self.location = "/board/register";
        });

        // paging
        var actionForm = $("#actionForm");
        $(".paginate_button a").on("click", function (e) {
            e.preventDefault();

            actionForm.find("input[name='pageNum']").val($(this).attr("href"));
            actionForm.submit();
        });

        // 게시물 조회
        $(".move").on("click", function (e) {
            e.preventDefault();

            actionForm.append("<input type='hidden' name='no' value='" + $(this).attr("href") + "'>");
            actionForm.attr("action", "/board/get");
            actionForm.submit();
        });

        // 조건 검색
        var searchForm = $("#searchForm");
        $("#searchForm button").on("click", function (e) {
            if (!searchForm.find("option:selected").val()) {
                alert("검색 종류를 선택하세요.");
                return false;
            }
            if (!searchForm.find("input[name='keyword']").val()) {
                alert("키워드를 입력하세요.");
                return false;
            }

            searchForm.find("input[name='pageNum']").val("1");  // 1page로 변경
            e.preventDefault();
            searchForm.submit();
        })

    })
</script>
