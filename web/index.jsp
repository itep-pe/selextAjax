<%-- 
    Document   : selectAjax
    Created on : 17/03/2015, 09:04:23
    Author     : jefferson.lima
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <script type="text/javascript" src="./js/jquery-1.11.2.min.js"></script>
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />


        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js" type="text/javascript"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
    </head>
    <body>

        <script>
            $(document).ready(function () {
                $("#myAjaxRequestForm").submit(function (e) {
                    e.preventDefault();
                });
                $('select[name=sltatividade]').on('change', function () {
                    //get the form data and then serialize that
                    dataString = $("#myAjaxRequestForm").serialize();
                    //get the form data using another method 
                    var codigoAtividade = $('select[name=sltatividade]').val();
                    dataString = "codigoAtividade=" + codigoAtividade;
                    //make the AJAX request, dataType is set to json
                    //meaning we are expecting JSON data in response from the server
                    $.ajax({
                        type: "POST",
                        url: "selectController",
                        data: dataString,
                        //if received a response from the server
                        success: function (data, textStatus, jqXHR) {
                            $("select[name=carroatividade] option").remove();
                            var pegadados = data.split("/");
                            for (var i = 0; i < pegadados.length - 1; i++) {
                                var codAtividade = pegadados[i].split("-")[0];
                                var modelo = pegadados[i].split("-")[1];
                                var placa = pegadados[i].split("-")[2];
                                $("select[name=carroatividade]").append('<option>' + "Modelo: " + modelo + " - " + "Placa: " + placa + '</option>');
                            }
                        },
                        //If there was no resonse from the server
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log("Algo ruim aconteceu: " + textStatus);
                            $("#ajaxResponse").html(jqXHR.responseText);
                        },
                        //capture the request before it was sent to server
                        beforeSend: function (jqXHR, settings) {
                            //adding some Dummy data to the request
                            settings.data += "&dummyData=whatever";
                            //disable the button until we get the response
                        },
                        //this is called after the response or error functions are finsihed
                        //so that we can take some action
                        complete: function (jqXHR, textStatus) {
                            //enable the button 
                        }
                    });
                });
            });
        </script>

        <form id="myAjaxRequestForm">
            <fieldset>
                <legend>Atividade</legend>                
                <!--                <label for="nome">Departamento</label>-->
                <select name="sltatividade">
                    <option selected>--Selecione o código da atividade--</option>
                    <option value="A1">A1: UGP - Barragens</option>
                    <option value="A2">A2: CPRH - EIA</option>
                    <option value="A3">A3: Compesa - Adutora do Agreste</option>
                </select>

                <input type="button" value="Salvar" class="btn btn-info"/>               
            </fieldset>

            <fieldset>
                <legend>Automóveis relacionados à atividade</legend>
                <!--                <label for="nome">Carros</label>-->
                <div id="ajaxResponse"></div>
                <select name="carroatividade">
                    <option selected>--Carros disponíveis--</option>
                </select><br>
            </fieldset>
        </form>
    </body>
</html>
