<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

    <div class="container">

    <c:forEach var = "board" items = "${boards}">
        <div class="card m-2" margin="5px">
            <div class="card-body">
              <h4 class="card-title">${board.title}</h4>
                <a href="#" class="btn btn-primary">게시글 보기</a>
            </div>
          </div>
         </div>
    </c:forEach>
        

<%@ include file="layout/footer.jsp"%>