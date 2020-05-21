package es.uca.android_application.Program;

import android.util.Pair;
import java.util.Vector;

//CLASE QUE MODELA UN DÍA DEL PROGRAMA.
public class programItem
{
    private String title_;
    private Vector<Pair<String,String>> activities_;

    programItem(String t, Pair<String,String> p1,Pair<String,String> p2, Pair<String,String> p3,
                Pair<String,String> p4,Pair<String,String> p5,Pair<String,String> p6)
    {
        this.title_=t;
        activities_= new Vector<Pair<String,String>>(6);
        activities_.add(p1);
        activities_.add(p2);
        activities_.add(p3);
        activities_.add(p4);
        activities_.add(p5);
        activities_.add(p6);

    }
    //GETS.
    public String getTitle() {return title_;}
    public Pair<String,String> getActivity(int index)
    {
        return activities_.get(index);
    }
    public Vector<Pair<String,String>> getActivities() {return activities_;}
    //SETS.
    public void setTitle(String t){this.title_=t;}
    public void addActivity(String hour, String activity)
    {
        activities_.add(new Pair("hour","activity"));
    }
}
