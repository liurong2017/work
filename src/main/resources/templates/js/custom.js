$(function(){
	
	$.ajax({
		url:"/custom/list",
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					h+="<tr><td>"+list[x].name+"</td>"+
						"<td>"+list[x].source+"</td>"+
						"<td>"+list[x].mobile+"</td>"+
						"<td>"+list[x].sex+"</td>"+
						"<td>"+list[x].wechatName+"</td>"+
						"<td>"+list[x].wechatCode+"</td>"+
						"<td>"+list[x].level+"</td>"+
						"<td>"+new Date(list[x].addTime).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].address1+"</td>"+
						"<td>"+list[x].address2+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"editCustom("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<button class=\"btn btn-xs btn-success\" onclick=\"addOrder("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>下单</button>"			
									+"</td></tr>"
				}
				//加载分页信息
				
			}
			var obj=$("#pageDiv");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#CustomList").html(h)
		}
	});
})

function addCustom(){
	$("#addCustom_dia").modal("show");
	$("input[name=add_custom]").each(function(){
		$(this).val("")
	})
	$("textarea[name=add_custom]").each(function(){
		$(this).val("")
	})
}
function addCustom_dia_save(){
	var name=$("#addCustom_name").val();
	var level=$("#addCustom_level").val();
	var mobile=$("#addCustom_mobile").val();
	var sex=$("#addCustom_sex").val();
	var wechatCode=$("#addCustom_wechatCode").val();
	var wechatName=$("#addCustom_wechatName").val();
	var address1=$("#addCustom_address1").val();
	var address2=$("#addCustom_address2").val();
	var remark=$("#addCustom_remark").val();
	var source=$("#addCustom_source").val();
	var param="name="+name+"&level="+level+"&mobile="+mobile +"&sex="+sex+"&wechatCode="+wechatCode+"&wechatName="+wechatName
			+"&address1="+address1+"&address2="+address2+"&remark="+remark
			+"&source="+source;
	
	$.ajax({
		url:"/custom/add",
		data:param,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#addCustom_dia").modal("hide");
			var code =info.code;
			if(code ==200){
				alert("添加成功");
				window.location.reload();
			}else{
				alert(info.result)
			}
		}
	});
}
function editCustom(id){
	$("#updCustom_dia").modal("show");
	$.ajax({
		url:"/custom/getById",
		data:"id="+id,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			$("#updCustom_id").val(item.id);
			$("#updCustom_name").val(item.name);
			 $("#updCustom_level").val(item.level);
			 $("#updCustom_mobile").val(item.mobile);
			 $("#updCustom_sex").val(item.sex);
			 $("#updCustom_wechatCode").val(item.wechatCode);
			$("#updCustom_wechatName").val(item.wechatName);
			 $("#updCustom_address1").val(item.address1);
			 $("#updCustom_address2").val(item.address2);
			 $("#updCustom_remark").val(item.remark);
			 $("#updCustom_source").val(item.source);
			
		}
	});

}
function updCustom_dia_save(){
	var id=$("#updCustom_id").val()
	var name=$("#updCustom_name").val();
	var level=$("#updCustom_level").val();
	var mobile=$("#updCustom_mobile").val();
	var sex=$("#updCustom_sex").val();
	var wechatCode=$("#updCustom_wechatCode").val();
	var wechatName=$("#updCustom_wechatName").val();
	var address1=$("#updCustom_address1").val();
	var address2=$("#updCustom_address2").val();
	var remark=$("#updCustom_remark").val();
	var source=$("#updCustom_source").val();
	var param="id="+id+"&name="+name+"&level="+level+"&mobile="+mobile
	      +"&sex="+sex+"&wechatCode="+wechatCode+"&wechatName="+wechatName
			+"&address1="+address1+"&address2="+address2+"&remark="+remark
			+"&source="+source
	$.ajax({
		url:"/custom/update",
		data:param,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#updCustom_dia").modal("hide");
			var code =info.code;
			if(code ==200){
				alert("修改成功");
				window.location.reload();
			}else{
				alert(info.result)
			}
		}
	});
}
function switchPage(divid,pageNo){
	searchCustoms(pageNo,20);
}

function searchCustoms(pageNo,pageSize){
	var name=$("#CustomNameS").val();
	var mobile=$("#CustomMobileS").val();
	var wc=$("#CustomWechatCodeS").val();
	var wn=$("#CustomWechatNameS").val();
	var souece=$("#CustomSourceS").val();

	var param="";
	if(name.length>0){
		param+="&name="+name;
	}
	if(mobile.length>0){
		param+="&mobile="+mobile
	}
	if(wc.length>0){
		param+="&wechatCode="+wc
	}
	if(wn.length>0){
		param+="&wechatName="+wn
	}
	if(souece.length>0){
		param+="&source="+source
	}
	param+="&pageNo="+pageNo+"&pageSize="+pageSize
	param=param.substring(1,param.length);
    $("#CustomList").html("")
		$.ajax({
		url:"/custom/list",
		data:param,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					h+="<tr><td>"+list[x].name+"</td>"+
						"<td>"+list[x].source+"</td>"+
						"<td>"+list[x].mobile+"</td>"+
						"<td>"+list[x].sex+"</td>"+
						"<td>"+list[x].wechatName+"</td>"+
						"<td>"+list[x].wechatCode+"</td>"+
						"<td>"+list[x].level+"</td>"+
						"<td>"+new Date(list[x].addTime).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].address1+"</td>"+
						"<td>"+list[x].address2+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"editCustom("+list[x].id+")\">"+
                         "<i class=\"icon-edit icon-white\"></i>编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<button class=\"btn btn-xs btn-success\" onclick=\"addOrder("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>下单</button>"		
									+"</td></tr>"
				}
			}
			var obj=$("#pageDiv");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#CustomList").html(h)
			
		}
	});		
}
function addOrder(id){
	var url=window.location.href;
		window.location.href=url.substr(0,url.lastIndexOf("/"))+"/add_order.html?cid="+id
}
