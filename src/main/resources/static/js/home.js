var totalNum = 0;
var totalPage = 0;
var avageNum = 10;
var currentPage = 1;
var deleteObj;
var templte;
//初始化任务信息
getTaskList(currentPage,avageNum);
//初始化项目信息
getProjectList();
//获得项目列表
function getProjectList(){
    $.ajax({type:'GET',
        url:"http://localhost:8080/project",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function(data){
            var projects = data;
            if(!projects.length>0){
                $("#insertProject").click();
            }
            var initPid = projects[0].projectId;
            var initName = projects[0].projectName;
            $("#projectInput").attr("pid",initPid);
            $("#projectInput").attr("placeholder",initName);
            $("#projectName").attr("placeholder",initName);
            $.each(projects,function(index,n){
                var li = document.createElement('li');
                li.className="li_node";
                // $(this).attr("id",projects[index].projectId);
                var child="<a href='javascript:void(0);' onclick='menulist(this);' id='"+projects[index].projectId+"'>"+projects[index].projectName+"</a>";
                li.innerHTML=child;
                $(".dropdown-menu").append(li);
            })
        }});

}
//获取任务列表
function getTaskList(pageNum,pageSize,taskName,projectId,taskDescription){
    $(".table_node").html("");
    $.ajax({type: 'GET',
        url: "http://localhost:8080/task",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:{"pageNum":pageNum,"pageSize":pageSize,"projectId":projectId,"taskName":taskName,"taskDescription":taskDescription},
        success:function(data){
            var tasks = data.data;
            totalNum =data.total;
            console.log(totalNum);
            currentPage = pageNum;
            totalPage = Math.ceil(totalNum/avageNum);
            console.log(totalPage);
            // initPagination(totalPage,totalNum);
            $.each(tasks,function(index,n){
                var row = document.createElement('tr');
                row.className = "tr_node";
                var status = "";
                if(tasks[index].status=='0'){
                    status = "未启动";
                }
                if(tasks[index].status=='1'){
                    status = "已启动";
                }
                var child = "<td>" + tasks[index].taskName + "</td>"
                    + "<td>" + tasks[index].cronExpression + "</td>"
                    + "<td>" + tasks[index].taskDescription + "</td>"
                    + "<td>"+tasks[index].schedulePath + "</td>"
                    + "<td>" + status + "</td>"
                    + "<td>" + tasks[index].createTime + "</td>"
                    + "<td style='color:#009ACD;'> <a href='#' onclick='startTask(this,1)' tid='"+tasks[index].taskId+"'>启动</a>&nbsp;&nbsp;<a href='#' onclick='startTask(this,0)' tid='"+tasks[index].taskId+"'>暂停</a>" + "</td>"
                    + "<td style='color:#009ACD;'><a href='#' tid='"+tasks[index].taskId+"' onclick='deleteTask(this);' data-toggle='modal' data-target='#deleteModal'>删除</a>&nbsp;&nbsp;<a onclick='updateTask(this);' href='#' data-toggle='modal' data-target='#myModalUpdate' tid='"+tasks[index].taskId+"'>编辑</a>&nbsp;&nbsp;<a href='#' data-toggle='modal' data-target='#LogModal' tid='"+tasks[index].taskId+"' onclick='searchLog(this);'>查看日志</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='taskexecution(this);' tid='"+tasks[index].taskId+"'>立即执行</a>"+ "</td>";
                row.innerHTML = child;
                $(".table_node").append(row);
            });
            console.log("data.data.length"+data.data.length);
            $(".pagination").html("");
            $("#pageInfo").empty();
            if(data.data.length > 0){
                initPagination(totalPage,totalNum,taskName,projectId,taskDescription);
            }
        },
        error:function(){
            alert("错误");
        }
    });
}
//初始化分页
function initPagination(totalPage,totalNum,taskName,projectId,taskDescription){
    $(".pagination").html("");
    $(".pagination").append(
        '<li><a href="javascript:void(0);" id="prev">上一页</a></li>'
    );
    for(var i=1;i<=totalPage;i++){
        $(".pagination").append( '<li id="page'+i+'"><a href="javascript:void(0);"  onclick="getTaskList(' + i + ',' + avageNum + ',\''+taskName+'\',\''+projectId+'\',\''+taskDescription+'\');">' + i + '</a></li>');
    }
    $(".pagination").append(
        '<li><a href="javascript:void(0);"  id="next">下一页</a></li>'

    )
    $("#pageInfo").empty();
    $("#pageInfo").append('共'+totalNum+"条数据 &nbsp;&nbsp;"+"共"+totalPage+"页");
    if(currentPage==1){
        $("#page1").addClass("active");
    }else{
        $("#page"+currentPage).addClass("active");
    }
}
$(".pagination").on("click","li",function(){
    var aText = $(this).find('a').html();
    var projectId = $("#projectInput").attr("pid")?$("#projectInput").attr("pid"):"";
    if($("#projectInput").val()==""){
        projectId ="";
    }
    var taskName = $("#search-name").val();
    var taskDescription = $("#search-deswcription").val();
    checkA()
    if(aText =="上一页"){
        $(".pagination > li").removeClass("active");
        $("#page"+(currentPage -1)).addClass("active");
        getTaskList(currentPage - 1,avageNum,taskName,projectId,taskDescription);
        checkA()

    }else if(aText == "下一页"){
        $(".pagination > li").removeClass("active");
        $("#page"+(currentPage +1)).addClass("active");
        getTaskList(currentPage+1,avageNum,taskName,projectId,taskDescription);
        checkA()
    }else{
        $("#pagination  > li").removeClass("active");
        $(this).addClass("active");
    }
});
//验证分页是不是第一页或者最后一页
function checkA() {
    currentPage == 1 ? $("#prev").addClass("btn btn-default disabled") : $("#prev").removeClass("btn btn-default disabled");
    currentPage == totalPage ? $("#next").addClass("btn btn-default disabled") : $("#next").removeClass("btn btn-default disabled");
}
$('#myModal').on('shown.bs.modal', function () {
    if($("#projectInput").val()!=""){
        $("#projectName").val($("#projectInput").val());
    }
    $("#projectName").attr("pid",$("#projectInput").attr("pid"));

    $('#myInput').focus()
});
$('#myModalUpdate').on('shown.bs.modal', function () {
    $('#myInput').focus()
});
$('#deleteModal').on('shown.bs.modal', function () {
});
function menulist(obj){
    console.log($(obj).text());
    $("#projectInput").val($(obj).text());
    console.log($(obj).attr("id"));
    $("#projectInput").attr("pid",$(obj).attr("id"));

}
//添加任务信息
$("#saveTask").click(function(){
    var taskName = $("#taskName").val();
    var taskDescription = $("#taskDescription").val();
    var projectName = $("#projectName").val();
    var cron = $("#cron").val();
    var path = $("#url").val();
    var projectId=$("#projectName").attr("pid");
    $.ajax({
        type:'POST',
        url:"http://localhost:8080/task",
        data:{"taskName":taskName,
            "taskDescription":taskDescription,
            "cronExpression":cron,
            "schedulePath":path,
            "projectId":projectId
        },
        success:function(data){
            $("#projectName").val("");
            $("#taskDescription").val("");
            $("#taskName").val("");
            $("#cron").val("");
            $("#url").val("");
            var row = document.createElement('tr');
            row.className = "tr_node";
            var myDate = new Date();
            var child = "<td>" + taskName + "</td>"
                + "<td>" +cron + "</td>"
                + "<td>" + taskDescription + "</td>"
                + "<td>"+path + "</td>"
                + "<td>" + "未启动" + "</td>"
                + "<td>" +  timeStamp2String(myDate)+ "</td>"
                + "<td style='color:#009ACD;'> <a href='#' onclick='startTask(this,1)' tid='"+data+"'>启动</a>&nbsp;&nbsp;<a href='#' onclick='startTask(this,0);' tid='"+data+"'>暂停</a>" + "</td>"
                + "<td style='color:#009ACD;'><a href='#' onclick='deleteTask(this);' tid='"+data+"'  data-toggle='modal' data-target='#deleteModal'>删除</a>&nbsp;&nbsp;<a onclick='updateTask(this);' href='#' data-toggle='modal' data-target='#myModalUpdate' tid='"+data+"'>编辑</a>&nbsp;&nbsp;<a href='#' data-toggle='modal' data-target='#LogModal' onclick='searchLog(this);' tid='"+data+"'>查看日志</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='taskexecution(this);' tid='"+data+"'>立即执行</a>"+ "</td>";
            row.innerHTML = child;
            $(".table_node").append(row);
            $("#pageInfo").empty();
            $("#pageInfo").append('共'+(totalNum+1)+"条数据 &nbsp;&nbsp;"+"共"+totalPage+"页");
            totalNum=totalNum+1;
        }
    });
})
//返回一定格式的日期类型
function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
function deleteTask(obj){
    deleteObj =$(obj);
    var taskId = $(obj).attr("tid");
    $("#deleteTask").attr("tid",taskId);
}
$("#deleteTask").click(function(){
    var taskId = $("#deleteTask").attr("tid");
    $.ajax({
        type:"DELETE",
        url:"http://localhost:8080/task?taskId="+taskId,
        //contentType:"application/json;charset=utf-8",
        dataType:"text",
        //data:{"taskId":taskId},
        success:function(){
            deleteObj.parent().parent().remove();
            // window.location.reload();
            $("#pageInfo").empty();
            $("#pageInfo").append('共'+(totalNum-1)+"条数据 &nbsp;&nbsp;"+"共"+totalPage+"页");
            totalNum=totalNum-1;
        }
    });
});
function updateTask(obj,task){
    var createTime=$(obj).parent().prev().prev().text();
    templte=$(obj);
    var path = $(obj).parent().prev().prev().prev().prev().text();
    var description = $(obj).parent().prev().prev().prev().prev().prev().text();
    var cron = $(obj).parent().prev().prev().prev().prev().prev().prev().text();
    var taskName =$(obj).parent().parent().children(":first").text();
    var taskId=$(obj).attr("tid");
    console.log("update task");
    console.log("createtime"+" "+createTime);
    console.log("path"+" "+path);
    console.log("description"+" "+description);
    console.log("cron"+" "+cron);
    console.log("taskName"+" "+taskName);
    $("#taskDescriptionUpdate").val(description);
    $("#urlUpdate").val(path);
    $("#cronUpdate").val(cron);
    $("#taskNameUpdate").val(taskName);
    $("#task_Id").attr("tid",taskId);
}
$("#updateTask").click(function(){
    var taskName = $("#taskNameUpdate").val();
    var taskDescription = $("#taskDescriptionUpdate").val();
    var cron = $("#cronUpdate").val();
    var path = $("#urlUpdate").val();
    var taskId = $("#task_Id").attr("tid");
    $.ajax({
        type:'PUT',
        url:"http://localhost:8080/task?taskId="+taskId+"&taskDescription="+taskDescription+"&cronExpression="+cron+"&schedulePath="+path+"&taskName="+taskName,
        success:function(data){
            console.log(templte);
            templte.parent().parent().children(":first").text(taskName);
            templte.parent().prev().prev().prev().prev().prev().text(taskDescription);
            templte.parent().prev().prev().prev().prev().prev().prev().text(cron);
            templte.parent().prev().prev().prev().prev().text(path);
        }
    });
})
$("#searchButton").click(function(){
    var projectId = $("#projectInput").attr("pid")?$("#projectInput").attr("pid"):"";
    if($("#projectInput").val() == ""){
        projectId = "";
    }
    var taskName = $("#search-name").val();
    var taskDescription = $("#search-deswcription").val();
    getTaskList(currentPage,avageNum,taskName,projectId,taskDescription);
})

function taskexecution(obj){
    var schedulePath = $(obj).parent().parent().children(":first").next().next().next().text();
    var taskId =$(obj).attr("tid");
    var path = $(obj).parent().prev().prev().prev().prev().text();
    var description = $(obj).parent().prev().prev().prev().prev().prev().text();
    var cron = $(obj).parent().prev().prev().prev().prev().prev().prev().text();
    var taskName =$(obj).parent().parent().children(":first").text();
    console.log("schedulePath:"+schedulePath);
    $.ajax({
        type:"GET",
        url:schedulePath,
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function(){
        }
    });
}
//条件查询任务信息
function searchLog(obj){
    var taskId = $(obj).attr("tid");
    $(".log_node").html("");
    $.ajax({type: 'GET',
        url: "http://localhost:8080/log",
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:{"taskId":taskId},
        success:function(data){
            $.each(data,function(index,n){
                var row = document.createElement('tr');
                var projectId= data[index].task.project.projectId?data[index].task.project.projectId:"";
                var status =  data[index].task.status;
                var logDescription = data[index].logDescription;
                var createTime = data[index].createTime;
                row.className = "tr_node";
                var child = "<td>"+projectId+"</td>"
                    +"<td>"+status+"</td>"
                    +"<td>"+logDescription+"</td>"
                    +"<td>"+createTime+"</td>";
                row.innerHTML = child;
                $(".log_node").append(row);
            });

        },
        error:function(){
            alert("错误");
        }
    });
}
function startTask(obj,flag){
    var taskId = $(obj).attr("tid");
    var status = "" + flag;
    var cronExpression = $(obj).parent().parent().children(":first").next().text();
    $.ajax({
        type:"PUT",
        url:"http://localhost:8080/task?taskId="+taskId+"&status="+status,
        // data:{"taskId":taskId,"status":flag},
        success:function(){
            if(flag == 1){
                $(obj).parent().prev().prev().text("已启动");
            }
            if(flag == 0){
                $(obj).parent().prev().prev().text("未启动");
            }
        }
    });
}
$("#saveProject").click(function(){
    var projectName = $("#projectNameInsert").val();
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/project",
        data:{"projectName":projectName},
        success:function(){
            window.location.reload();
        }
    })
})
$("#addProject").click(function(){
   $("#projectInfo").text("");
})