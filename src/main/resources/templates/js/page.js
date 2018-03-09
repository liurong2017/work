/**
 * 对页面进行分页
 * @param obj 页码标签对象
 * @param  pageCount number 总页面数
 * @param  pageSize number 分页大小
 * @param currentPage number 当前页
 */
function PagingManage(obj, pageCount, pageSize, currentPage) {
    if (obj) {
        var dataCount = pageCount; //数据总数
        var pagesize = pageSize;//单页数量
        var currentpage = currentPage;//当前页面
        var pageNum;//分页总数
        var showPageNum = 6;//显示多少个页码

        var pagehtml = "";
        var divId = "" + obj.attr('id');

        //计算分页总数
        if (dataCount % pagesize == 0) {
            pageNum = parseInt(dataCount / pagesize);
        } else {
            pageNum = parseInt(dataCount / pagesize) + 1;
        }

        console.log('log01:dataCount:' + dataCount + '<>pageSize:' + pagesize + '<>currentPage:' + currentpage);
        console.log('log02:pageNum:' + pageNum + '<>showPageNum:' + showPageNum + '<>divId:' + divId);

        //只有一页内容
        if (pageNum <= 1) {
            pagehtml = "";
        }

        //大于一页内容
        if (pageNum > 1) {
            if (currentpage > 1) {
                pagehtml += '<span><a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + (currentpage - 1) + ')">上一页</a></span>&nbsp;&nbsp;';
            }

            var startPage = 1;
            //计算页码开始位置
            if (showPageNum > pageNum) {//如果要显示的页码大于总的页码数
                console.log('showPageNum > pageNum');
                startPage = 1
            } else {//如果要显示的页码小于总的页码数
                if (currentpage - (showPageNum / 2) <= 0) {//如果当前页面
                    startPage = 1;
                    console.log('currentpage - (showPageNum / 2) < 0');
                } else if (currentpage + (showPageNum / 2) >= pageNum) {
                    startPage = pageNum - showPageNum;
                    console.log('currentpage + (showPageNum / 2) > pageNum');
                } else {
                    startPage = currentpage - (showPageNum / 2);
                }
            }

            startPage = parseInt(startPage);
            console.log('log03:startPage:' + startPage);

            for (var i = startPage; i < (startPage + showPageNum); i++) {

                //如果要输出的页面大于总的页面数,则退出
                if (i > pageNum) {
                    break;
                }

                if (i == currentpage) {
                    pagehtml += '<span class="activeS"><a href="javascript:void(0);" >' + i + '</a></span>&nbsp;&nbsp;';
                } else {
                    pagehtml += '<span><a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + i + ')">' + i + '</a></span>&nbsp;&nbsp;';
                }
            }


            if (currentpage < pageNum) {
                pagehtml += '<span><a href="javascript:void(0);" onclick="switchPage(\'' + divId + '\',' + (currentpage + 1) + ')">下一页</a></span>&nbsp;&nbsp;';
            }
        }
        console.log(pagehtml)
        obj.html(pagehtml);
    }
}
//$(function(){
//	var obj=$("#pageDiv");
//	PagingManage(obj,50,10,3);
//})
