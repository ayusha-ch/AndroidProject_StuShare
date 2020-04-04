package com.example.stu_share;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import static com.example.stu_share.AdminEventList.userAdm;


public class AdminDrawerUtil {

    public static void getDrawer(final Activity activity, Toolbar toolbar) {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withTranslucentStatusBar(true)
                .withCompactStyle(false)
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .build();
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);


        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(13)
                .withName("My Profile").withIcon(R.drawable.ic_profile);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIdentifier(8).withName("Users List").withIcon(R.drawable.ic_users);


        SecondaryDrawerItem item1= new SecondaryDrawerItem().withIdentifier(9).withName("Events List").withIcon(R.drawable.icons8_joined);
//        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
//                .withName(R.string.contact).withIcon(R.drawable.icons8_contact);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(10)
                .withName(R.string.message).withIcon(R.drawable.ic_message);
        SecondaryDrawerItem drawerItemDonate = new SecondaryDrawerItem().withIdentifier(11)
                .withName(R.string.logout).withIcon(R.drawable.ic_logout);



        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withTranslucentStatusBar(true)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        drawerEmptyItem,
                        drawerItemManagePlayers,
                        item2,
                        item1,
                        new DividerDrawerItem(),
                        drawerItemHelp,
                        drawerItemDonate

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 8 && !(activity instanceof AdminUserList)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, AdminUserList.class);
                            intent.putExtra("user",userAdm);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 13 && !(activity instanceof MyProfile)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MyProfile.class);
                            intent.putExtra("user",userAdm);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 11 && !(activity instanceof MainActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.putExtra("user",userAdm);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 10 && !(activity instanceof AdminMessageList)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, AdminMessageList.class);
                            intent.putExtra("user",userAdm);
                            view.getContext().startActivity(intent);
                        }
//                        else if (drawerItem.getIdentifier() == 4 && !(activity instanceof EmailActivity)) {
//                            // load tournament screen
//                            Intent intent = new Intent(activity, EmailActivity.class);
//                            intent.putExtra("user",user3);
//                            view.getContext().startActivity(intent);
//                        }
                        else if (drawerItem.getIdentifier() == 9 && !(activity instanceof AdminEventList)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, AdminEventList.class);
                            intent.putExtra("user",userAdm);
                            view.getContext().startActivity(intent);
                        }

                        return true;
                    }
                })
                .build();
    }
}

