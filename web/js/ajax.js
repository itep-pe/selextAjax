$(document).ready(function () {
     $('form').submit(function (e) {
                    e.preventDefault();
                });
    $('select[name=sltpais]').on('change', function () {
        $.ajax({
            type: "GET",
            url: "paisController",
            data: "codigopais=" + $('select[name=sltpais]').val(),
            statusCode: {
                404: function () {
                    alert('Pagina não encontrada');
                },
                500: function () {
                    alert('Erro no servidor');
                }
            },
            sucess: function (dados) {
                    console.log("sucesso");
                    $("#ajaxResponse").append("<p> Parabéns!!</p>");
               
            }
        });
    });
});