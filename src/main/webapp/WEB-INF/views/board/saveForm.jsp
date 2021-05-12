<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
      <form>

        <div class="form-group">
          <h4>상품이름 :</h4> <input type="text" class="form-control" placeholder="Enter Title" id="title">
        </div>
        <br/>

        <div class="form-group">
            <h4>상품설명 : </h4><textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>

      <button id="btn-save" class="btn btn-primary">게시글 작성</button>
      </form>
    </div>

    <script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 400
      });
    </script>
    
    <script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
      var fileName = $(this).val().split("\\").pop();
      $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
    </script>

    <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>