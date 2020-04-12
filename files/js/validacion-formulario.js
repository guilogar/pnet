
// IMPORTANT: in all code, i use the method append of jquery for add the elements. For more information, see https://api.jquery.com/append/

// Previous check of any condition....
function sendData()
{
    // TODO: Ancla por otro lado + Añadir comprobciones del hito y si falla, con el eventpreventDefault, parar el envio y añadir un bloque div de Boostrap con el mensajito de error. 
    //event.preventDefault().
}
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
function checkFormSend()
{
    let formSend = Boolean(getUrlVars()["formSend"]);
    if(formSend)
    {
        $('section.body > form#form-inscription').remove();
        $('section.body > h6').remove();
        let alertBoostrap = $('<div class="alert alert-success" role="alert">Formulario enviado con éxito. </div>');
        let a = $('<span>Haz click <a href="http://localhost:5500/src/inscripcion.html">aquí</a> para enviar otro.</span>');
        alertBoostrap.append(a);
        $('section.body').append(alertBoostrap);
    }
}
//Reloads web page to set input values to their default state.
function reload(){
    location.reload();
}

//Form restriction.
$(document).ready(function () {
    // On key down disabled on input type dates...
    $("input[type=date].form-inscription-input").on("keydown", function search(e)
    {
        return false;
    });
    // Restrictions arrival date: it will be between 2020-02-28 and the departure date.
    $("input[type=date].form-inscription-input#adname").change(function(event)
    {
        const value = $(this).val();
        const date = new Date(value);
        const valueDepartureDate=  $("#ddname").val();
        if(date < new Date("2020-02-28"))
        {
            $(this).val("2020-02-28");
        } else if(date > new Date(valueDepartureDate))
        {
            $(this).val(valueDepartureDate);  
        }
    });
    // Restrictions departure date: it will be between the arrival date and 2020-03-1.
    $("input[type=date].form-inscription-input#ddname").change(function()
    { 
        const value = $(this).val();
        const date = new Date(value);
        const valueArrivalDate=  $("#adname").val();
        if(date < new Date(valueArrivalDate))
        {
            $(this).val(valueArrivalDate);
        } else if(date > new Date("2020-03-01"))
        {
            $(this).val("2020-03-01");
        }
    });
    //Restrictions selector. It executes when the user changes an option from the selector (function change()).
    $("#form-selector").change(function()
    { 
        const arrivalDate = new Date($("#adname").val());
        const departureDate = new Date($("#ddname").val());
        const difference = new Date(departureDate- arrivalDate);
        alert(difference.getDate());
        if(difference.getDate() == 2)
        {                   
            $("#form-selector option[value='completa']").remove();
        }else 
        {
            if(difference.getDate()==1)
            {
                $("#form-selector option[value='completa']").remove();
                $("#form-selector option[value='doble']").remove(); 
            }
        }
    });
    //Restrictions name.

    //Restrictions surnames.


});