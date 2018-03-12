$(function(){
	$.ajax({
		url:"/product/list",
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
					h+="<tr>"+
					     "<td>"+list[x].name+"</td>"+
						"<td>"+list[x].classifyName+"</td>"+
						"<td>"+list[x].brandName+"</td>"+
						"<td>"+list[x].status+"</td>"+
						"<td>"+list[x].level+"</td>"+
						"<td>"+list[x].marketPrice+"</td>"+
						"<td>"+list[x].stock+"</td>"+
						"<td>"+list[x].totalCost+"</td>"+
						"<td>"+list[x].totalIncome+"</td>"+
						"<td>"+new Date(list[x].addTime).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-info\" onclick=\"updProduct("+list[x].id+")\">"
						+"<i class=\"icon-edit icon-white\"></i>编辑</button>	&nbsp;&nbsp;&nbsp;"
						+"<button class=\"btn btn-xs btn-success\" onclick=\"updStock("+list[x].id+",'"+list[x].stock+"')\">"
						+"<i class=\"icon-legal icon-white\"></i>库存/成本</button>"
						+"</td></tr>"
				}
			}
			var obj=$("#pageDiv_p");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#ProductList").html(h)
		}
	});
	$.ajax({
		url:"/brand/getAll",
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			if(item!=null&&item.length>0){
				var inner ="<option value=\"\">++请选择品牌++</option>";
				for(var x=0;x<item.length;x++){
					inner+="<option value=\""+item[x].id+"\">"+item[x].name+"</option>"
				}
				$("#ProductBrandS").html(inner);
			}
		}
	});
	$.ajax({
		url:"/classify/getAll",
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			if(item!=null&&item.length>0){
				var inner ="<option value=\"\">++请选择分类++</option>";
				for(var x=0;x<item.length;x++){
					inner+="<option value=\""+item[x].id+"\">"+item[x].name+"</option>"
				}
				$("#ProductClassS").html(inner);
			}
		}
	});
	
	
})


function switchPage(divid,pageNo){
	searchProducts(pageNo,20);
}

function addProduct(){
	$("#addProduct_dia").modal("show");
	$(".productAdd").each(function(){
		$(this).val("")
	})
	$.ajax({
		url:"/brand/getAll",
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			if(item!=null&&item.length>0){
				var inner ="<option value=\"\">++请选择品牌++</option>";
				for(var x=0;x<item.length;x++){
					inner+="<option value=\""+item[x].id+"\">"+item[x].name+"</option>"
				}
				$("#addProduct_brand").html(inner);
			}
		}
	});
	$.ajax({
		url:"/classify/getAll",
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			if(item!=null&&item.length>0){
				var inner ="<option value=\"\">++请选择分类++</option>";
				for(var x=0;x<item.length;x++){
					inner+="<option value=\""+item[x].id+"\">"+item[x].name+"</option>"
				}
				$("#addProduct_class").html(inner);
			}
		}
	});
	
}
function addProduct_dia_save(){
	var bid=$("#addProduct_brand").val();
	var cid=$("#addProduct_class").val();
	var name=$("#addProduct_name").val();
	var status=$("#addProduct_status").val();
	var stock=$("#addProduct_stock").val();
	var cost=$("#addProduct_cost").val();
	var level=$("#addProduct_level").val();
	var marketPrice=$("#addProduct_marketPrice").val();
	var remark=$("#addProduct_remark").val();
	if(cid.length==0||bid.length==0){
		alert("请选择品牌和分类");
		return;
	}
	var param="brandId="+bid+"&classifyId="+cid+"&name="+name
			+"&status="+status+"&stock="+stock+"&level="+level+"&marketPrice="+marketPrice
	        +"&cost="+cost
	        +"&remark="+remark;
	$.ajax({
		url:"/product/add",
		data:param,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#addProduct_dia").modal("hide");
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
function addOrder(id,name){
	$("#checkCustom_dia").modal("show");
	$("#checkCustom_productId").val(id)
	$("#checkCustom_productName").val(name)
	$("#custom_name").val("")
	$("#custom_wechatCode").val("")
	$("#custom_mobile").val("")
	
}
function checkCustomSearch(){
	var name=$("#custom_name").val();
	var mobile=$("#custom_mobile").val();
	var wc=$("#custom_wechatCode").val();
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
	if(param.length==0){
		return;
	}
	param=param.substring(1,param.length);
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
						"<td>"+list[x].wechatName+"</td>"+
						"<td>"+list[x].address1+"</td>"+
						"<td>"+list[x].address2+"</td>"+
						"<td><button class=\"btn btn-minier btn-success\" onclick=\"checkCustom("+list[x].id+")\">"+
									"<i class=\"icon-edit icon-white\"></i>选择</button></td>"+"</tr>"
				}
			}
			$("#CustomList").html(h)
			
		}
	});	
}
function checkCustom(cid){
	var pid=$("#checkCustom_productId").val();
	$("#checkCustom_dia").modal("hide");
	$("#addOrder_dia").modal("show");
	$(".addOrder").each(function(){
		$(this).val("")
	})
	
	
	$.ajax({
		url:"/product/getById",
		data:"id="+pid,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			$("#addOrder_pid").val(item.id)
			$("#addOrder_pname").val(item.name)
			$("#addOrder_pprice").val(item.marketPrice)
		}
	});
	
	
}




function updProduct(id){
	$("#updProduct_dia").modal("show");
	$.ajax({
		url:"/product/getById",
		data:"id="+id,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			$("#updProduct_id").val(item.id)
			$("#updProduct_name").val(item.name)
			$("#updProduct_status").val(item.status)
			$("#updProduct_level").val(item.level)
			$("#updProduct_remark").val(item.remark)
			$("#updProduct_marketPrice").val(item.marketPrice)
		}
	});

}
function updProduct_dia_save(){
	var id=$("#updProduct_id").val()
	var name=$("#updProduct_name").val()
	var level=$("#updProduct_level").val()
	var status=$("#updProduct_status").val()
	var marketPrice=$("#updProduct_marketPrice").val()
	var remark=$("#updProduct_remark").val()
	var param="";
	if(name.length>0){
		param+="&name="+name;
	}
	if(level.length>0){
		param+="&level="+level
	}
	if(status.length>0){
		param+="&status="+status
	}
	if(marketPrice.length>0){
		param+="&marketPrice="+marketPrice
	}
	if(remark.length>0){
		param+="&remark="+remark
	}
	if(param.length==0){
		return;
	}
	
	$.ajax({
		url:"/product/update",
		data:"id="+id+param,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#updProduct_dia").modal("hide");
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


function updProduct_dia_save(){
	var id= $("#updProduct_id").val()
	var name=$("#updProduct_name").val()
	var remark=$("#updProduct_remark").val()
	
	$.ajax({
		url:"/product/update",
		data:"id="+id+"&name="+name+"&remark="+remark,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#updProduct_dia").modal("hide");
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
function searchProducts(pageNo,pageSize){
	var name=$("#ProductNameS").val();
	var brandId=$("#ProductBrandS").val();
	var classID=$("#ProductClassS").val();
	var level=$("#levelSearch").val();
	var param="";
	if(name.length>0){
		param+="&name="+name;
	}
	if(brandId.length>0){
		param+="&brandId="+brandId;
	}
	if(classID.length>0){
		param+="&classifyId="+classID;
	}
	if(level.length>0){
		param+="&level="+level;
	}
	
	param+="&pageNo="+pageNo+"&pageSize="+pageSize
	param=param.substr(1,param.length);
		$.ajax({
		url:"/product/list",
		data:param,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#ProductList").html("")
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					h+="<tr>"+
					     "<td>"+list[x].name+"</td>"+
						"<td>"+list[x].classifyName+"</td>"+
						"<td>"+list[x].brandName+"</td>"+
						"<td>"+list[x].status+"</td>"+
						"<td>"+list[x].level+"</td>"+
						"<td>"+list[x].marketPrice+"</td>"+
						"<td>"+list[x].stock+"</td>"+
						"<td>"+list[x].totalCost+"</td>"+
						"<td>"+list[x].totalIncome+"</td>"+
						"<td>"+new Date(list[x].addTime).toLocaleString().replace(/\//g, "-")+"</td>"+
						"<td>"+list[x].remark+"</td>"+
						"<td><button class=\"btn btn-xs btn-info\" onclick=\"updProduct("+list[x].id+")\">"
						+"<i class=\"icon-edit icon-white\"></i>编辑</button>	&nbsp;&nbsp;&nbsp;"
						+"<button class=\"btn btn-xs btn-success\" onclick=\"updStock("+list[x].id+",'"+list[x].stock+"')\">"
						+"<i class=\"icon-legal icon-white\"></i>库存/成本</button>"
						+"</td></tr>"
				}
			}
			var obj=$("#pageDiv_p");
	        PagingManage(obj,info.result.totalRecords,info.result.pageSize,info.result.pageNo);
			$("#ProductList").html(h)
			
		}
	});
}
function updStock(id,stock){
	$("#updStock_dia").modal("show");
	$("#updStock_id").val(id)
	$("#updStock_now").val(stock)
	$("#updStock_stock").val("");
	$("#updStock_remark").val("")
	$("#updStock_cost").val("")
}
function updStock_dia_save(){
	var id =$("#updStock_id").val()
   var stock=	$("#updStock_stock").val();
   var flag=	$("#updStock_flag").val();
	var remark=$("#updStock_remark").val()
	var cost=$("#updStock_cost").val()
	$.ajax({
		url:"/product/updateStock",
		data:"id="+id+"&flag="+flag+"&stock="+stock+"&remark="+remark+"&cost="+cost,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			$("#updStock_dia").modal("hide");
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
