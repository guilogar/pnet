
// IMPORTANT: in all code, i use the method append of jquery for add the elements. For more information, see https://api.jquery.com/append/

// Previous check of any condition....
function sendData()
{
    // $('form#form-inscription');
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

// TODO: poner mas bonito esta funcion y convinarla con el codigo de script que esta en inscripcion.html relacionado con 
// controlar los input de tipo date.
/*Establish the possible inscription according to the number of days the user will stay (arrival date and departure date).*/
function selectionInscription(){
    
    var date1= document.getElementById("adname");
    var date2= document.getElementById("ddname");
    var myDate1= new Date(date1.value);
    var myDate2= new Date(date2.value);
    var differenceTime=Math.abs(myDate2-myDate1);
    var differenceDays = Math.ceil(differenceTime / (1000 * 60 * 60 * 24)); 
    /*Difference between dates is at least 1 day.*/
    if(differenceDays==1)
    {
        var selector=document.getElementById("form-selector");
        for (var i=0; i<selector.length; i++) 
        {
            if (selector.options[i].value == "completa" || selector.options[i].value == "doble")
                selector.remove(i);
        }
    }else
    {
        if(differenceDays==2)
        {
                var selector=document.getElementById("form-selector");
            for (var i=0; i<selector.length; i++) 
            {
                if (selector.options[i].value == "completa")
                    selector.remove(i);
            }
        }
    }

}