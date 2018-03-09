function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

$(function(){
	var  cid=GetQueryString("cid");
	if(cid==null||cid.length==0){
		var url=window.location.href;
		window.location.href=url.substr(0,url.lastIndexOf("/"))+"/custom.html"
	}
	//获取用户信息
	$.ajax({
		url:"/custom/getById",
		data:"id="+cid,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			$("#addOrder_cid").val(item.id);
			$("#addOrder_cname").val(item.name);
			$("#addOrder_address").val(item.address1)
			 $("#addOrder_address1").val(item.address1);
			 $("#addOrder_address2").val(item.address2);
		}
	});
})

function getAdress(){
	var address=$("#address_btn").val();
	var info=$("#"+address).val()
	$("#addOrder_address").val(info)
}
function openSearch(){
	$("#search_product_dia").modal("show");
	$("#productList").html("")
	$("#ProductNameS").val("")
	$("#ProductBrandS").val("")
	$("#ProductClassS").val("")
	
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
}
function searchProducts(){
	var name=$("#ProductNameS").val();
	var brandId=$("#ProductBrandS").val();
	var classID=$("#ProductClassS").val();
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
	if(param.length==0){
		return;
	}
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
			
			var h="";
			var list=info.result.records;
			if(list!=null&&list.length>0){
				for (var x=0;x<list.length;x++) {
					h+="<tr><td><input type=\"checkbox\" name=\"checkPs\" value=\""+list[x].id+"\" /></td>"+
					   "<td>"+list[x].name+"</td>"+
						"<td>"+list[x].stock+"</td>"+
						"<td>"+list[x].marketPrice+"</td>"+
						"<td><button class=\"btn btn-xs btn-info\" onclick=\"addOne("+list[x].id+")\">"
						+"<i class=\"icon-edit icon-white\"></i>添加</button>"
						+"</td></tr>"
				}
			}
			$("#productList").html(h)
		}
	});
}
function closeDia(name){
	$("#"+name).modal("hide")
}
function addOne(id){
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
			var inner="";
			var num=$("#addOrder_productList").children().length;
			inner+="<tr id=\"pList_tr_"+num+"\"><td><input type=\"hidden\" id=\"pList_id_"+num+"\" value=\""+item.id+"\" />"
			       +"<input  id=\"pList_name_"+num+"\" value=\""+item.name+"\" readonly=\"readonly\" /></td>"+
						"<td><input name='pList_item' id=\"pList_num_"+num+"\" value=\"\" /></td>"+
						"<td><input  name='pList_item' id=\"pList_totalPrice_"+num+"\" value=\"\" /></td>"+
						"<td><button class=\"btn btn-xs btn-info\" onclick=\"removeOne("+item.id+")\">"
						+"<i class=\"icon-remove icon-white\"></i>移除</button>"
						+"</td></tr>"
			$("#addOrder_productList").append(inner)			
		}
	});
	
}
function addMore(){
	var ids=""
	
	$("input[type=checkbox][name=checkPs]:checked").each(function(){
		ids+=$(this).val()+",";
	})
	if(ids.length>0){
		ids=ids.substr(0,ids.length-1)
	}
	$.ajax({
		url:"/product/getByIds",
		data:"ids="+ids,
		type:"GET",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			var item=info.result;
			var inner="";
			if(item!=null&&item.length>0){
				var num=$("#addOrder_productList").children().length;
				for(var x=0;x<item.length;x++){
					inner+="<tr id=\"pList_tr_"+(num+x)+"\"><td><input type=\"hidden\" id=\"pList_id_"+(num+x)+"\" value=\""+item[x].id+"\" />"
			       +"<input  id=\"pList_name_"+(num+x)+"\" value=\""+item[x].name+"\" readonly=\"readonly\" /></td>"+
						"<td><input name='pList_item' id=\"pList_num_"+(num+x)+"\" value=\"\" /></td>"+
						"<td><input  name='pList_item' id=\"pList_totalPrice_"+(num+x)+"\" value=\"\" /></td>"+
						"<td><button class=\"btn btn-xs btn-info\" onclick=\"removeOne("+item[x].id+")\">"
						+"<i class=\"icon-remove icon-white\"></i>移除</button>"
						+"</td></tr>"
				}	
			}
			$("#addOrder_productList").append(inner)			
		}
	});
}
function removeOne(id){
	$("tr[id=pList_tr_"+id+"]").remove();
}
function saveOrder(){
	var flag=true
	$("input[name=pList_item]").each(function(){
		if($(this).val().length==0){
			flag=false;			
		}
	})
	if(!flag){
		alert("销售信息不全");
		return;
	}
    var cid=$("#addOrder_cid").val();
	var cname=$("#addOrder_cname").val();
	var source=$("#addOrder_source").val();
	var address=$("#addOrder_address").val()
	var remark=$("#addOrder_remark").val()
	var pList=""
	$("#addOrder_productList").children().each(function(index){
		pList+=$("#pList_id_"+index).val()+","
		         +$("#pList_num_"+index).val()+","
		         +$("#pList_totalPrice_"+index).val()+"@"
	})
	
	var param="cid="+cid+"&cname="+cname+"&address="+address+"&remark="+remark+"&pList="+pList+"&source="+source;
	$.ajax({
		url:"/order/add",
		data:param,
		type:"POST",
		async:false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.status);
		},
		success : function(info) {
			if(info.code==200){
				alert("保存订单成功");
				var url=window.location.href;
		        window.location.href=url.substr(0,url.lastIndexOf("/"))+"/custom.html"
			}
		}
	});
	
	
	
}
