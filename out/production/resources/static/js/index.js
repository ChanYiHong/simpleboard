var main = {
    init : function () {
        var _this = this;
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/post/delete/'+id,
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            //alert(JSON.stringify(error));
            alert("요청 실패!!!");
        });
    }

};

main.init();