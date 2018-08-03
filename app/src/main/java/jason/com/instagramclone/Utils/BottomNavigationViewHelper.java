package jason.com.instagramclone.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.lang.reflect.Field;

import jason.com.instagramclone.Home.HomeActivity;
import jason.com.instagramclone.Likes.LikesActivity;
import jason.com.instagramclone.Profile.ProfileActivity;
import jason.com.instagramclone.R;
import jason.com.instagramclone.Search.SearchActivity;
import jason.com.instagramclone.Share.ShareActivity;

public class BottomNavigationViewHelper extends AppCompatActivity {

    @SuppressLint("RestrictedApi")
    public static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView,false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

    public static void enableNavigation(final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){

                    case R.id.ic_house:
                        Intent intentOne = new Intent(context, HomeActivity.class);//ACTIVITY_NUM = 0
                        context.startActivity(intentOne);
                        break;

                    case R.id.ic_search:
                        Intent intentTwo = new Intent(context, SearchActivity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intentTwo);
                        break;

                    case R.id.ic_circle:
                        Intent intentThree = new Intent(context, ShareActivity.class);//ACTIVITY_NUM = 2
                        context.startActivity(intentThree);
                        break;

                    case R.id.ic_alert:
                        Intent intentFour = new Intent(context, LikesActivity.class);//ACTIVITY_NUM = 3
                        context.startActivity(intentFour);
                        break;

                    case R.id.ic_android:
                        Intent intentFive = new Intent(context, ProfileActivity.class);//ACTIVITY_NUM = 4
                        context.startActivity(intentFive);
                        break;

                }

                return false;
            }
        });
    }

}// end class