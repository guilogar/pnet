
/*Establish the minimum date of the arrival date as the current one.*/
function minDate1(){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0.
    var yyyy = today.getFullYear();
    if(dd<10)
    {
            dd='0'+dd;
    } 
    if(mm<10)
    {
            mm='0'+mm;
    } 

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("adname").setAttribute("min", today);
}
    
/*Establish the minimum date of the departure date as the arrival date. */
function minDate2(){
    var myDate= document.getElementById("adname");
    var min=new Date(myDate.value);
    min.setDate(min.getDate()+1);
   
    var dd = min.getDate();
    var mm = min.getMonth()+1; //January is 0.
    var yyyy = min.getFullYear();
    if(dd<10)
    {
            dd='0'+dd;
    } 
    if(mm<10)
    {
            mm='0'+mm;
    } 

    min = yyyy+'-'+mm+'-'+dd;
    document.getElementById("ddname").setAttribute("min", min);
}

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