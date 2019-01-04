var vmchat =new Vue({
    el: '#chat',
    data: {
        message:'',
        messageBox:'',
        allUser:'',
        head:''
    },
    methods: {
        getAllUSer:function(){

            this.$http.post('/getChatUser',).then(function (res) {
                for(var i=0;i<res.body.data.length;i++){
                    this.allUser+=" <el-col :span='24'>" +
                        "<div style='height: 60px;width: 75px;display: table-cell;vertical-align: middle;text-align: center;' @click='chatTo'>" +
                        "<img @click='chatTo()' style='width: 90%;height: 90%' src='"+res.body.data[i].headImg+"'>" +res.body.data[i].userName+
                        "</div>"
                        "</el-col>" ;
                }
            });
        },
        sendMessage:function () {
            if(this.message==''){
                this.$message({
                    type: 'warning',
                    message: '发送消息不能为空',
                });
            }else{
                this.messageBox+="<el-row>"+
                    "                            <el-col :span='6'>" +
                    "                                <img src='../../images/head/jerry.png' height='50' width='50'>" +
                    "                            </el-col>" +
                    "                            <el-col :span='18' style='background: #fff'>" +
                    "                                <span style='font-size: 30px'>"+this.message+"</span>" +
                    "                            </el-col>"+
                    "</el-row>"
                    ;
                this.message = '';
            }

        },
        chatTo:function () {
            alert(1);
        }
    }
});
vmchat.getAllUSer();

