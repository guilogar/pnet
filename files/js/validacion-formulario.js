
// IMPORTANT: in all code, i use the method append of jquery for add the elements. For more information, see https://api.jquery.com/append/

// Previous check of any condition....

function sendData(event)
{
    event.preventDefault();

    //Name contain only characters.
    var str = $("#fname").val();
    var check = /^[a-zA-Z]+$/.test(str);
   
    //Name is not the person who is going to give the talk.
    var check3 = str.includes("Edsger Dijkstra");
    var check4 = str.includes("Steve Jobs");
    var check5 = str.includes("Alan Turing");
    var check6 = str.includes("Tim Berners-Lee");
   
    // Surnames contains only characters.
    str = $("#lname").val();
    var check2 = /^[a-zA-Z]+$/.test(str); // TODO: permit blank spaces between lastname......
   
    // console.log(!check)
    // console.log(!check2)
    // console.log(check3)
    // console.log(check4)
    // console.log(check5)
    // console.log(check6)
    if(!check || !check2 || check3 || check4 || check5 || check6)
    {
        //Alert.
        $('div.alert').remove(); // Delete previous alerts....
        $( ".body" ).append( "<div class='alert alert-danger' role='alert' > ¡ERROR, NOMBRE Y/O APELLIDOS INTRODUCIDOS SÓLO DEBEN CONTENER CARACTERES O NO SER ALGUN PONENTE!  </div>");
       // location.href="inscripcion.html";
       return;
    }

    //Checking if any option is selected.
    if($("#form-selector").val()=="ninguna")
    {
        $( ".body" ).append( "<div class='alert alert-danger' role='alert' > ¡ERROR, SELECCIONA ALGUNA OPCIÓN EN EL SELECTOR!");
        return;
    }
    
    const inputs = $('#form-inscription * input[name]');
    let inscription = { };
    
    for(const input of inputs)
    {
        const nameInput = $(input).attr('name');
        const valInput = $(input).val();
        inscription[nameInput] = valInput;
    }

    $.ajax({
        type: "POST",
        dataType: "json",
        url: "http://localhost:3000/api/v1/inscriptions",
        data: inscription,
        success: function(data)
        {
            console.log(data);
            window.location.href = inscription.urlToRedirect;
        },
        error: function(err)
        {
            console.log(err);
            window.location.href = inscription.urlToRedirect.replace('true', 'false');
        }
    });    
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function checkFormSend(url)
{
    let formSend = getUrlVars()["formSend"];
    
    if(formSend === undefined) return;

    formSend = JSON.parse(getUrlVars()["formSend"]);

    $('section.body > form#form-inscription').remove();
    $('section.body > h6').remove();

    if(formSend === true)
    {
        let alertBoostrap = $('<div class="alert alert-success" role="alert">Formulario enviado con éxito.</div>');
        let a = $('<span>Haz click <a href="' + url + '">aquí</a> para enviar otro.</span>');
        alertBoostrap.append(a);
        $('section.body').append(alertBoostrap);
    } else if(formSend === false)
    {
        let alertBoostrap = $('<div class="alert alert-danger" role="alert">Lo lamentamos, pero su formulario no se ha podido enviar con éxito. Vuelva a intentarlo.</div>');
        let a = $('<span>Haz click <a href="' + url + '">aquí</a> para enviar otro.</span>');
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