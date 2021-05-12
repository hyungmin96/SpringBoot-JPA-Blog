<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@ include file="../layout/header.jsp" %>

    <div id="commentContainer" class="container">

      <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
      <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
      </c:if>
      <br /><br />

      <div>
        글 번호 : <span id="id"><i>${board.id}</i></span>
        작성자 : <span><i>${board.user.username} </i></span>
      </div>
      <br />

      <div>
        <h3>${board.title}</h3>
      </div>
      <hr />

      <div>
        <p>${board.content}</p>
      </div>
      <hr />

      <div class="card">
        <div class="card-body">
          <textarea rows="1" id="comment-box" class="form-control"></textarea>
        </div>
        <div class="card-footer"><button id="btn-comment" class="btn btn-primary">덧글작성</button></div>
      </div>
      <br />

      <div class="card">
        <form>
          <input type = "hidden" id="userId" value = "${principal.user.id}"/>
          <input type="hidden" id="boardId" value="${board.id}" />
          <div class="card-header">덧글 리스트</div>
          <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
              <li id="reply-${reply.id}" class="list-group-item">
                <div class="d-flex">
                  <h5> ${reply.user.username} &nbsp;</h5>
                  <button onclick="comment.commentDelete(${board.id}, ${reply.id})">삭제</button>
                </div>
                <p style="margin-bottom: 0;">${reply.content}</p>
              </li>
            </c:forEach>
          </ul>
        </form>
      </div>

    </div>

    <script src="/js/board.js"></script>
    <script src="/js/comment.js"></script>
    <%@ include file="../layout/footer.jsp" %>