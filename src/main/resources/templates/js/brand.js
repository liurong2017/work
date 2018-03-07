$(function(){
	$.ajax({
		url:"/brand/getList",
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
						"<td>"+list[x].level+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"editBrand("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>编辑</button></td>"+"</tr>"
				}
			}
			$("#brandList").html(h)
		}
	});
})

function addBrand(){
	$("#addBrand_dia").modal("show");
	$("#addBrand_level").val("")
	$("#addBrand_name").val("")
	$("#addBrand_remark").val("")
}
function addBrand_dia_save(){
	var name=$("#addBrand_name").val();
	var level=$("#addBrand_level").val();
	var remark=$("#addBrand_remark").val();
	$.ajax({
		url:"/brand/addBrand",
		data:"name="+name+"&level="+level+"&remark="+remark,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#addBrand_dia").modal("hide");
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
function editBrand(id){
	$("#updBrand_dia").modal("show");
	$.ajax({
		url:"/brand/getById",
		data:"id="+id,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			
			$("#updBrand_id").val(item.id)
			$("#updBrand_name").val(item.name)
			$("#updBrand_remark").val(item.remark)
			$("#updBrand_level").val(item.level);
			
		}
	});

}
function updBrand_dia_save(){
	var id= $("#updBrand_id").val()
	var name=$("#updBrand_name").val()
	var remark=$("#updBrand_remark").val()
	var level=$("#updBrand_level").val();
	
	$.ajax({
		url:"/brand/updateBrand",
		data:"id="+id+"&name="+name+"&level="+level+"&remark="+remark,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#updBrand_dia").modal("hide");
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
function searchBrands(){
	var name=$("#BrandNameS").val();
	
	if(name.length>0){
		$.ajax({
		url:"/brand/getList",
		data:"name="+name,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#brandList").html("")
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					h+="<tr><td>"+list[x].name+"</td>"+
						"<td>"+list[x].level+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-success\" onclick=\"editBrand("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>编辑</button></td>"+"</tr>"
				}
			}
			$("#brandList").html(h)
			
		}
	});
		
	}
}
