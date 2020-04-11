
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