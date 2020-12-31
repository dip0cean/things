function deletePost(deleteData) {
    $.ajax({
        method: 'DELETE',
        url: '/api/post/delete',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(deleteData)
    }).done(function () {
        alert('성공적으로 삭제되었습니다.');
        window.location.href = '/post/1';
    }).fail(function () {
        alert('알 수 없는 오류로 게시글을 삭제 할 수 없습니다. 다시 시도 해주세요.');
    });
}

const main = {
    init: function () {
        const _this = this;
        let path;
        $('#btn-checkPw').on('click', function () {
            _this.checkPw(path);
        });
        $('#update-path').on('click', function () {
            path = 'update';
        });
        $('#delete-path').on('click', function () {
            path = 'delete';
        });
        // Post
        $('#btn-post-create').on('click', function () {
            _this.createPost();
        });
        $('#btn-post-update').on('click', function () {
            _this.updatePost();
        });

    },
    checkPw: function (path) {
        const data = {
                id: $('#user_id').val(),
                userPw: $('#checkPw').val()
            }
        ;

        $.ajax({
            method: 'POST',
            url: '/api/user/check',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (check) {
            if (check) {
                if (path === 'update') {
                    window.location.href = '/post/update/' + $('#id').val();
                } else {
                    const deleteData = {
                        id: $('#id').val()
                    }
                    deletePost(deleteData)
                }
            } else {
                alert('비밀번호가 일치하지 않습니다.');
            }
        }).fail(function () {
            alert('알 수 없는 오류가 발생했습니다.');
        })
    },
    createPost: function () {
        const data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            method: 'POST',
            url: '/api/post/create',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (id) {
            alert('게시글을 성공적으로 작성했습니다.');
            window.location.href = '/post/detail/' + id;
        }).fail(function () {
            alert('알 수 없는 오류로 게시글을 작성 할 수 없습니다.');
        });

    },
    updatePost: function () {
        const data = {
            user_id: $('#user_id').val(),
            id: $('#id').val(),
            title: $('#title').val(),
            content: $('#content').val()
        };

        $.ajax({
            method: 'PATCH',
            url: '/api/post/update',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (id) {
            window.location.href = '/post/detail/' + id;
        }).fail(function () {
            alert('알 수 없는 오류로 게시글을 수정 할 수 없습니다.');
        });
    }
}
main.init();