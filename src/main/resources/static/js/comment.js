let comment = {

    init: function(){
        $("#btn-comment").on("click", ()=>{
            console.log("asd");
            this.comment();
        });
    },

    comment: function(){

        let data = {
            userId : $("#userId").val(),
            boardId: $("#id").text(),
            content: $("#comment-box").val()
        };
        
        console.log(data);

        $.ajax({
            type: "POST",
            url: '/api/board/' + data.boardId + '/comment',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert('덧글작성이 완료되었습니다.');
            location.href = '/board/' + data.boardId;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });

    }

}

comment.init();