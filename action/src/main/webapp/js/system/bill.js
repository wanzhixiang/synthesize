/**
 * Created by wzx on 2017/2/9.
 */
/*新增保存*/
$("#billSave").click(function () {
    var billName = $("#billName").val();
    var billPrice = $("#billPrice").val();
    var billAddress = $("#billAddress").val();
    var billDate = $("#billDate").val();
    var billRemark = $("#billRemark").val();
    if (inputIsNull(billName,"billName") && inputIsNull(billPrice,"billPrice") && inputIsNull(billAddress,"billAddress") && inputIsNull(billDate,"billDate")) {
        var url = "/bill/add.action";
        var param = {};
        $.post(url,param,function (data) {
            if (data.success){
                $("#close").click;
            }
        })
    }

});
function inputIsNull(value,id) {
    if(value.length==0){
        $("#"+id).focus();
        $("#"+id).attr("style","border:1px solid red")
        return false;
    }else{
        $("#"+id).attr("style","")
    }
    return true;
}
$("#billAdd input").blur(function () {
    var val = $(this).val();
    inputIsNull(val,$(this).attr("id"))
})