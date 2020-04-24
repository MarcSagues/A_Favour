package cat.udl.tidic.a_favour.Views;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.drawerlayout.widget.DrawerLayout;

import java.lang.reflect.Method;
import java.security.PublicKey;
import java.util.concurrent.Callable;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.R;

public class LoadingPanel
{
     private static volatile LoadingPanel instance = new LoadingPanel();
     private LoadingPanel(){}
     public static LoadingPanel getInstance() { return instance; }
     private static AlertDialog ad;

     public static void enableLoading(Context c, boolean enable)
     {
          if (FORTESTING.dev){return;}
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
          viewGroup.addView(child,viewGroup.getChildCount()-1);
     }

     public static void setErrorDialog(Context c, Callable<Void> func) throws Exception
     {
          if (FORTESTING.dev){return;}
          enableLoading(c,false);
          //Si falla la connexió s'haura de posar un layout de "error"
          AlertDialog.Builder builder = new AlertDialog.Builder(c);
          builder.setMessage(R.string.dialogMessage).setTitle(R.string.dialogTitle);
          builder.setPositiveButton(R.string.retry, (dialog, id) ->
          {
               ad = null;
               try {
                    func.call();
               } catch (Exception e) {
                    e.printStackTrace();
               }

          });
          builder.setNegativeButton(R.string.cancel, (dialog, id) ->
          {
               dialog.cancel();
               ad = null;
          });

          if ( ad== null)
          {
               ad = builder.create();
               builder.show();
          }
     }

     public static void sendMessage(String message)
     {
          Context c = App.getAppContext();
          Toast.makeText(c , message, Toast.LENGTH_SHORT).show();
     }
}
