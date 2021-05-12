let comment = {
    init: function () {
        $('#btn-comment').on('click', () => {
            this.comment();
        });
        $("#button" + $("#commentId").val()).on('click', ()=>{
            let value = "button" + $("#commentId").val();
            alert(value);
            this.commentDelete();
        });
    },

    commentDelete: function (board, reply) {
        let data = {
            boardId: board,
            replyid: reply,
        };

        console.log(data);
        alert("test");

        $.ajax({

            type: "POST",
            url: "/api/comment/delete",
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: "json"
        }).done(function(resp){
            alert(resp);
            location.href = "/board/" + data.boardId;
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    comment: function () {
        let data = {
            userId: $('#userId').val(),
            boardId: $('#id').text(),
            content: $('#comment-box').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/board/' + data.boardId + '/comment',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
        })
            .done(function (resp) {
                alert('덧글작성이 완료되었습니다.');
                location.href = '/board/' + data.boardId;
            })
            .fail(function (error) {
                alert(JSON.stringify(error));
            });
    },
};

comment.init();