<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
      <form>
        <input type = "hidden" id="id" value="${board.id}"/>
        <div class="form-group">
          <input value="${board.title}" class="form-control" placeholder="Enter Title" id="title">
        </div>

        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
      <button id="btn-update" class="btn btn-primary">게시글 수정</button>
      </form>
    </div>

    <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>
    
    <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>