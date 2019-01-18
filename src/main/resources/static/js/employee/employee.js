var employee = new Vue({
    el: '#employee',
    data: {},
    methods: {
        init: function () {
            var table = $('#tables').DataTable({
                ajax: '/employee/selectAll',
                "lengthMenu": [[2, 4, 6, -1], [2, 4, 6, "All"]],
                dom: '<"top"f>rt<"bottom"lp><"clear">',
                stateSave: true,//当你刷新页面的时候会保存你上次选择的分页信息，但是只会保留两个小时。可以设置
                pagingType: "full_numbers",//会显示首页上一页数子下一页和尾页
                scrollY: 200,
                scrollCollapse: true,
                jQueryUI: true,
                paging: "false",
                language: {
                    lengthMenu: "每页 _MENU_ 条记录",
                    zeroRecords: "没有找到记录",
                    info: "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                    infoEmpty: "无记录",
                    infoFiltered: "(从 _MAX_ 条记录过滤)",
                    oPaginate: {
                        "sFirst": "首页",
                        "sPrevious": "上页",
                        "sNext": "下页",
                        "sLast": "末页"
                    },
                },

                columns: [
                    {data: "name"},
                    {data: "age"},
                    {data: "sex"},
                    {data: "adress"},
                    {data: "birthday"},
                    {data: "salary"},
                ],

            });

            $('#tables tbody').on('click', 'tr', function () {
                $(this).toggleClass('selected');
            });
            $('#button').click(function () {
                alert(table.rows('.selected').data().length + ' row(s) selected');
                employee.download(table.rows('.selected').data());
            });

        },
        download: function (data) {
            var ids = [];
            for (var i = 0; i < data.length; i++) {
                ids.push(data[i]["id"])
            }

            this.$http.post('/employee/download', ids).then(function (res) {
                this.$message({
                    type: res.body.status == true ? 'success' : 'error',
                    message: res.body.message,
                });
            });
        }
    }

});
employee.init();