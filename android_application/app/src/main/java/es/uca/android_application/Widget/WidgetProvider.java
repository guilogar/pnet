package es.uca.android_application.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import es.uca.android_application.R;
import es.uca.android_application.Program.*;
public class WidgetProvider extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {

        for (int appWidgetId: appWidgetIds)
        {
            //Creamos el intent.
            Intent intent= new Intent(context, program.class);
            //Se lo pasamos al pendingIntent para que haga cosas fuera de la app.
            PendingIntent pendingIntent= PendingIntent.getActivity(context, 0, intent, 0);
            //Se establece la conexión entre Widget y app.
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
            //Al hacer click en widget..
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }




    }
}
