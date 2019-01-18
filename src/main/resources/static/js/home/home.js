var vm = new Vue({
    el: '#app',
    data: {},
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        Echart: function () {
            var iframe = document.getElementById("left");
            iframe.src = "right.html";
        },
        chat: function () {
            var iframe = document.getElementById("left");
            iframe.src = "chat.html";
        },
        peopleExcle: function () {
            var iframe = document.getElementById("left");
            iframe.src = "../../views/employee/employee/employee.html";
        },
        download: function () {
            var tableName = JSON.stringify("USER");
            this.$http.post('/download', tableName).then(function (res) {
                this.$message({
                    type: res.body.status == true ? 'success' : 'error',
                    message: res.body.message,
                });
            });
        },
    },

})
