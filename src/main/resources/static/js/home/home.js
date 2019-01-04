var vm =new Vue({
    el:'#app',
    data:{

    },
    methods:{
        echart:function () {
            var iframe=document.getElementById("left");
            iframe.src="right.html";
        },
        chat:function () {
            var iframe=document.getElementById("left");
            iframe.src="chat.html";
        },
        download:function () {
            var tableName = JSON.stringify("USER");
            this.$http.post('/download',tableName).then(function (res) {
                this.$message({
                    type: res.body.status == true ? 'success' : 'error',
                    message: res.body.message,
                });
            });
        }
    },

})
