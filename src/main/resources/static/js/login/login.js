var vm = new Vue({
    el: '#index',
    data: {
        title: "JERRY系统",
        userObj: {},
        loginDo: true,
        regist: false,
        rePassword: '',
    },

    methods: {
        login: function () {
            var formData = JSON.stringify(this.userObj);
            if (this.userObj.userName == '') {
                this.$message({
                    type: 'warning',
                    message: '请输入用户名',
                });
            } else if (this.userObj.password == '') {
                this.$message({
                    type: 'warning',
                    message: '请输入密码',
                });
                ;
            } else {
                this.$http.post('/login', formData,).then(function (res) {
                    if (res.body.status == true) {
                        window.location.href = "static/views/home/home.html"
                    } else {
                        this.$message({
                            type: 'error',
                            message: '用户名或密码错误',
                        });
                    }
                });
            }
        },
        register: function () {
            this.loginDo = false;
            this.regist = true;
        },
        back: function () {
            this.loginDo = true;
            this.regist = false;
        },
        registUser: function () {
            var formData = JSON.stringify(this.userObj);
            if (this.userObj.userName == '') {
                this.$message({
                    type: 'warning',
                    message: '请输入用户名',
                });
            } else if (this.userObj.password == '') {
                this.$message({
                    type: 'warning',
                    message: '请输入密码',
                });
                ;
            } else if (this.userObj.password != this.rePassword) {
                this.$message({
                    type: 'error',
                    message: '密码不一致',
                });
                ;
            } else {
                this.$http.post('/register', formData,).then(function (res) {
                    this.$message({
                        type: res.body.status == true ? 'success' : 'error',
                        message: res.body.message,
                    });
                });
            }
        }

    }
})

