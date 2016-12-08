var currentProduct;
var separator = "\"";
var comma = ",";

jQuery(document).ready(function () {
    var selector = $('#selector');
    $('#validate_button').click(function () {
        sendTrade(createJSON());
    });
    selector.change(function () {
        $('select option').each(function () {
            var product = $('#' + $(this).val());
            product.attr("hidden", true);
        });
        currentProduct = selectedProduct();
        $('#' + currentProduct).removeAttr("hidden");
    });
    selector.change();
});

var selectedProduct = function () {
    var selectedProduct = "";
    $('select option:selected').each(function () {
        selectedProduct += $(this).val();
    });
    return selectedProduct;
};

var sendTrade = function (json) {
    $.ajax({
        type: 'POST',
        data:json,
        contentType:'application/json;charset=utf-8',
        dataType:'json',
        success: function (result) {
            var product = JSON.parse(result["trade"]);
            $(".error").each(function(){
                $(this).empty();
            });
            $(".value").each(function(){
                $(this).val("");
            });

            var type = product["type"];

            $.each(result, function(key, value) {
                $('#error_' + type + "_" + key).html(value);
            });
            var trade = JSON.parse(result["trade"]);
            $.each(trade, function(key, value) {
                $('#value_' + type + "_" + key).val(value);
            })
        },
        error: function (err) {
            console.log(err)
        }
    })
};

var createJSON = function () {
    var currentProduct = selectedProduct();
    var typeField = "type";
    var json = separator + typeField + separator + ":" + separator + currentProduct + separator;
    $('.value_' + currentProduct).each(function () {
        var _this = $(this);
        var field = _this.attr("field");
        if (field != typeField)
            json += comma + separator + field + separator + ":" + separator + _this.val() + separator;
    });
    json = "{" + json + "}";
    return json;
};