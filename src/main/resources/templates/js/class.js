$(function(){
	$.ajax({
		url:"/classify/list",
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
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"editClass("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>编辑</button></td>"+"</tr>"
				}
			}
			$("#ClassList").html(h)
		}
	});
})

function addClass(){
	$("#addClass_dia").modal("show");
	$("#addClass_name").val("")
	$("#addClass_remark").val("")
}
function addClass_dia_save(){
	var name=$("#addClass_name").val();
	var remark=$("#addClass_remark").val();
	$.ajax({
		url:"/classify/add",
		data:"name="+name+"&remark="+remark,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#addClass_dia").modal("hide");
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
function editClass(id){
	$("#updClass_dia").modal("show");
	$.ajax({
		url:"/classify/getById",
		data:"id="+id,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			$("#updClass_id").val(item.id)
			$("#updClass_name").val(item.name)
			$("#updClass_remark").val(item.remark)
			
		}
	});

}
function updClass_dia_save(){
	var id= $("#updClass_id").val()
	var name=$("#updClass_name").val()
	var remark=$("#updClass_remark").val()
	
	$.ajax({
		url:"/classify/update",
		data:"id="+id+"&name="+name+"&remark="+remark,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#updClass_dia").modal("hide");
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
function searchClass(){
	var name=$("#ClassNameS").val();
	if(name.length>0){
		$.ajax({
		url:"/classify/list",
		data:"name="+name,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#ClassList").html("")
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					h+="<tr><td>"+list[x].name+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"editClass("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>编辑</button></td>"+"</tr>"
				}
			}
			$("#ClassList").html(h)
			
		}
	});
		
	}
}
