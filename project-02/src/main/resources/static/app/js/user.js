const main = {
    init: function () {
        const _this = this;
        let path;
        // User
        $('#btn-signup').on('click', function () {
            _this.signUp();
        });
        $('#btn-signin').on('click', function () {
            _this.signIn();
        });
        $('#btn-signout').on('click', function () {
            _this.signOut();
        });
        $('#btn-checkPw').on('click', function () {
            _this.checkPw(path);
        });
        $('#btn-user-update').on('click', function () {
            _this.userUpdate();
        });
        $('#update-path').on('click', function () {
            path = 'update';
        });
        $('#delete-path').on('click', function () {
            path = 'delete';
        });
    },
    signUp: function () {
        const data = {
            userId: $('#userId').val(),
            userPw: $('#userPw').val(),
            userNick: $('#userNick').val(),
            userIntro: $('#userIntro').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/user/signup',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원가입에 성공했습니다. 감사합니다.')
            window.location.href = '/';
        }).fail(function () {
            alert('회원가입에 실패했습니다. 다시 시도해주세요.')
        });
    },
    signIn: function () {
        const data = {
            userId: $('#signin-id').val(),
            userPw: $('#signin-pw').val()
        };

        $.ajax({
            method: 'POST',
            url: '/api/user/signin',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (check) {
            if (check) {
                alert('로그인에 성공했습니다.')
                window.location.href = '/'
            } else {
                alert('아이디 혹은 비밀번호가 맞지 않습니다.');
            }
        }).fail(function () {
            alert('로그인에 실패했습니다.');
        })
    },
    signOut: function () {
        alert('로그아웃 되었습니다.')
    },
    checkPw: function (path) {
        const data = {
            id: $('#id').val(),
            userPw: $('#checkPw').val()
        };

        $.ajax({
            method: 'POST',
            url: '/api/user/check',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (check) {
            if (check) {
                if (path === 'update') {
                    window.location.href = '/user/update/' + data.id;
                } else {
                    const deleteDate = {
                        id: data.id
                    }
                    $.ajax({
                        method: 'DELETE',
                        url: '/api/user/delete',
                        contentType: 'application/json; charset=utf-8',
                        data: JSON.stringify(deleteDate)
                    }).done(function () {
                        alert('성공적으로 탈퇴되었습니다. 이용해주셔서 감사합니다.');
                        window.location.href = '/signout';
                    }).fail(function () {
                        alert('알 수 없는 오류로 탈퇴에 실패했습니다. 다시 시도 해주세요.');
                    })
                }
            } else {
                alert('비밀번호가 일치하지 않습니다.');
            }
        }).fail(function () {
            alert('알 수 없는 오류가 발생했습니다.');
        })
    },
    userUpdate: function () {
        const data = {
            id: $('#id').val(),
            userId: $('#userId').val(),
            userPw: $('#userPw').val(),
            userNick: $('#userNick').val(),
            userIntro: $('#userIntro').val()
        };

        $.ajax({
            method: 'PATCH',
            url: '/api/user/update',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원 정보 수정 완료');
            window.location.href = '/user/detail/' + data.id;
        })
    },
}
main.init();