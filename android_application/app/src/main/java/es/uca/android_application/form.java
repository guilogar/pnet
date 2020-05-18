package es.uca.android_application;
//CLASE FORMULARIO.
public class form
{
    private String _id=null;    //Id de la inscripción; estático, no puede cambiarse. Al descargar de la base de datos, se rellena, en caso contrario, es nulo.
    private String fname;       //Nombre.
    private String lname;       //Apellidos.
    private String ename;       //Email.
    private String adname;      //Fecha de llegada.
    private String ddname;      //Fecha de salida.
    private String dniname;     //DNI.
    private String phonename;   //Teléfono.
    private String bdname;      //Fecha de nacimiento.
    private String idname;      //Fecha de inscripción.
    //Constructor normal con id.
    public form(String id, String name, String surnames,String email, String arrival_date, String departure_date, String dni, String phone_n, String birthday, String inscr_date)
    {
        this._id=id;
        this.fname=name;
        this.lname=surnames;
        this.ename=email;
        this.adname=arrival_date;
        this.ddname=departure_date;
        this.dniname=dni;
        this.phonename=phone_n;
        this.bdname=birthday;
        this.idname=inscr_date;
    }
    //Constructor normal sin id.
    public form( String name, String surnames,String email, String arrival_date, String departure_date, String dni, String phone_n, String birthday, String inscr_date)
    {
        this.fname=name;
        this.lname=surnames;
        this.ename=email;
        this.adname=arrival_date;
        this.ddname=departure_date;
        this.dniname=dni;
        this.phonename=phone_n;
        this.bdname=birthday;
        this.idname=inscr_date;
    }
    //Gets.
    public String getId() {return _id;}
    public String getName() { return fname;}
    public String getSurnames() { return lname;}
    public String getEmail() { return ename;}
    public String getArrivalDate() { return adname;}
    public String getDepartureDate() { return ddname;}
    public String getDni() { return dniname;}
    public String getPhoneN() { return phonename;}
    public String getBirthday() { return bdname;}
    public String getInscrDate() { return idname;}
    //Sets.
    public void setId(String i){this._id=i;}
    public void setName(String n) { this.fname=n;}
    public void setSurnames(String s) { this.lname=s;}
    public void setEmail(String e) { this.ename=e;}
    public void setArrivalDate(String ad) { this.adname=ad;}
    public void setDepartureDate(String dd) { this.ddname=dd;}
    public void setDni(String d) { this.dniname=d;}
    public void setBirthday(String b) { this.bdname=b;}
    public void setPhoneN(String p) { this.phonename=p;}
    public void setInscrDate(String i_d) { this.idname=i_d;}
}