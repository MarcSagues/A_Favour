package cat.udl.tidic.a_favour.Views;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.drawerlayout.widget.DrawerLayout;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.R;

public class LoadingPanel
{
     private static volatile LoadingPanel instance = new LoadingPanel();
     private LoadingPanel(){}
     public static LoadingPanel getInstance() { return instance; }

     public static void enableLoading(Context c, boolean enable)
     {
          RelativeLayout lp = ((Activity) c).findViewById(R.id.loadingPanel);

          if (lp == null)
          {
               generateLoadingPanel(c);
          }

          lp = ((Activity) c).findViewById(R.id.loadingPanel);

          if (enable)
          {
               lp.setVisibility(View.VISIBLE);
          }
          else
          {
               lp.setVisibility(View.GONE);
          }


     }
     private static void generateLoadingPanel(Context c)
     {
          final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) ((Activity) c)
                  .findViewById(android.R.id.content)).getChildAt(0);
          View child =  ((Activity) c).getLayoutInflater().inflate(R.layout.loading_panel, null);
          viewGroup.addView(child,0);
     }
}
