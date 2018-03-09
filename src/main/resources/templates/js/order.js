$(function(){
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
					$(this).prev().focus();
	});
	$.ajax({
		url:"/order/list",
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
					
					var status="";
					if(list[x].status==1){
						status="已确认"
					}else if(list[x].status=='2'){
						status="已发货"
					}else{
						status="退货"
					}
					h+="<tr><td>"+list[x].pname+"</td>"+
						"<td>"+list[x].cname+"</td>"+
						"<td>"+list[x].mobile+"</td>"+
						"<td>"+status+"</td>"+
						"<td>"+list[x].price+"</td>"+
						"<td>"+list[x].num+"</td>"+
						"<td>"+list[x].source+"</td>"+
						"<td>"+new Date(list[x].time).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].express+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"sendOrder("+list[x].oid+")\">"+
									"<i class=\"icon-envelope icon-white\"></i>发货</button> &nbsp;&nbsp;&nbsp;&nbsp;"+
								"<button class=\"btn btn-xs btn-danger\" onclick=\"returnOrder("+list[x].oid+")\">"+
									"<i class=\"icon-edit icon-white\"></i>退货</button>"+	
									"</td></tr>"
				}
			}
			var obj=$("#pageDiv");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#orderList").html(h)
		}
	});
})

function switchPage(divid,pageNo){
	searchorders(pageNo,20);
}
function refreshList(){
	$("#cnameSearch").val("");
	$("#mobileSearch").val("");
	$("#pnameSearch").val("");
	$("#sourceSearch").val("");
	$("#statusSearch").val("1");
	$("#timeStart").val("");
	$("#timeEnd").val("");
	$.ajax({
		url:"/order/list",
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
					
					var status="";
					if(list[x].status==1){
						status="已确认"
					}else if(list[x].status=='2'){
						status="已发货"
					}else{
						status="退货"
					}
					h+="<tr><td>"+list[x].pname+"</td>"+
						"<td>"+list[x].cname+"</td>"+
						"<td>"+list[x].mobile+"</td>"+
						"<td>"+status+"</td>"+
						"<td>"+list[x].price+"</td>"+
						"<td>"+list[x].num+"</td>"+
						"<td>"+list[x].source+"</td>"+
						"<td>"+new Date(list[x].time).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].express+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"sendOrder("+list[x].oid+")\">"+
									"<i class=\"icon-envelope icon-white\"></i>发货</button> &nbsp;&nbsp;&nbsp;&nbsp;"+
								"<button class=\"btn btn-xs btn-danger\" onclick=\"returnOrder("+list[x].oid+")\">"+
									"<i class=\"icon-edit icon-white\"></i>退货</button>"+	
									"</td></tr>"
				}
			}
			var obj=$("#pageDiv");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#orderList").html(h)
		}
	});
}

function searchorders(pageNo,pageSize){
	var cname=$("#cnameSearch").val();
	var mobile=$("#mobileSearch").val();
	var pname=$("#pnameSearch").val();
	var source=$("#sourceSearch").val();
	var status=$("#statusSearch").val();
	var timeStart=$("#timeStart").val();
	var timeEnd=$("#timeEnd").val();
	var param="";
	if(cname.length>0){
		param+="&cname="+cname;
	}
	if(mobile.length>0){
		param+="&mobile="+mobile;
	}
	if(pname.length>0){
		param+="&pname="+pname;
	}
	if(source.length>0){
		param+="&source="+source;
	}
	if(timeStart.length>0){
		param+="&timeStart="+timeStart;
	}
	if(timeEnd.length>0){
		param+="&timeEnd="+timeEnd;
	}
	param+="&pageNo="+pageNo+"&pageSize="+pageSize
	param=param.substr(1,param.length)

	
		$.ajax({
		url:"/order/list",
		data:param,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#orderList").html("")
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					var status="";
					if(list[x].status==1){
						status="已确认"
					}else if(list[x].status=='2'){
						status="已发货"
					}else{
						status="退货"
					}
					
					
					h+="<tr><td>"+list[x].pname+"</td>"+
						"<td>"+list[x].cname+"</td>"+
						"<td>"+list[x].mobile+"</td>"+
						"<td>"+status+"</td>"+
						"<td>"+list[x].price+"</td>"+
						"<td>"+list[x].num+"</td>"+
						"<td>"+list[x].source+"</td>"+
						"<td>"+new Date(list[x].time).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].express+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"sendOrder("+list[x].oid+")\">"+
									"<i class=\"icon-envelope icon-white\"></i>发货</button> &nbsp;&nbsp;&nbsp;&nbsp;"+
								"<button class=\"btn btn-xs btn-danger\" onclick=\"returnOrder("+list[x].oid+")\">"+
									"<i class=\"icon-edit icon-white\"></i>退货</button>"+	
									"</td></tr>"
				}
			}
			var obj=$("#pageDiv");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#orderList").html(h)
			
		}
	});
		
}
function sendOrder(id){
	$("#sendOrder_dia").modal("show");
	$("#sendOrder_id").val(id);
	$("#sendOrder_express").val("");
}
function sendOrder_dia_save(){
	var id= $("#sendOrder_id").val();
	var express=$("#sendOrder_express").val();
	$.ajax({
		url:"/order/updateExpress",
		data:"id="+id+"&express="+express,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#sendOrder_dia").modal("hide");
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

function returnOrder(id){
	$("#returnOrder_dia").modal("show");
	$("#returnOrder_id").val(id);
	$("#returnOrder_remark").val("");
	$.ajax({
		url:"/order/getById",
		data:"id="+id,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item =info.result;
			$("#returnOrder_remark").val(item.remark);
		}
	});
	
}
function returnOrder_dia_save(){
	if(window.confirm("是否确认退货")){
		var id= $("#returnOrder_id").val();
		var remark=$("#returnOrder_remark").val();
		$.ajax({
			url:"/order/returnOrder",
			data:"id="+id+"&remark="+remark,
			type:"POST",
			async:false,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
			},
			success : function(info) {
				$("#sendOrder_dia").modal("hide");
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
}
