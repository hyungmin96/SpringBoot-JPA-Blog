let index = {
    init: function(){
        $("#btn-save").on("click", ()=>{ //function(){} 이 아닌 () => {}를 사용하는건 this를 바인딩하기 위하여
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        })
        $("#btn-update").on("click", ()=>{
            this.update();
        })
    },

    update: function(){

        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/"+ id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert('글수정이 완료되었습니다.');
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    deleteById: function(){

        let id = $("#id").text();

        $.ajax({
            type: "POST",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function(resp){
            alert('게시글이 삭제되었습니다.');
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    save: function(){
        //alert('user의 save함수 호출됨');
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        //console.log(data)
        // ajax호출시 default가 비동기 호출
        //ajax 통신을 이요해서 3개의 데이터를 json으로 변경하여 insert 요청
        //ajax 통신성공하고 json 리턴하면 서버가 자동으로 java object로 변환
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), //http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(Mime)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(String) 생긴게 json 이라면 => javascript 오브젝트로 변경됨
        }).done(function(resp){
            alert('글작성이 완료되었습니다.');
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); 
    }
}

index.init();