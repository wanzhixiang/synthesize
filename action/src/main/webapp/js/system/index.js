$(function () {
    $(".content-left,.content-right").height($(window).height() - 130);
})
$(".accordion-inner").click(function () {
    $(".active").html($(this).find(".left-body").text());
    //变更右边内容
    var _link = $(this).attr('_link');
    $("#main").attr("src",_link);
})

$(window).resize(function () {
    $(".content-left,.content-right").height($(window).height() - 130);
}
)