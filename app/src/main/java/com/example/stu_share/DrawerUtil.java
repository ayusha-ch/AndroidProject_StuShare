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
import static com.example.stu_share.EventList.user3;
public class DrawerUtil {

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

        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.profile).withIcon(R.drawable.ic_profile);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.create).withIcon(R.drawable.ic_create);


        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.upcoming).withIcon(R.drawable.icons8_joined);
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
                .withName(R.string.contact).withIcon(R.drawable.icons8_contact);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(5)
                .withName(R.string.message).withIcon(R.drawable.ic_message);
        SecondaryDrawerItem drawerItemDonate = new SecondaryDrawerItem().withIdentifier(6)
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
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemSettings,
                        drawerItemHelp,
                        drawerItemDonate
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 2 && !(activity instanceof EventCreateDescription)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, EventCreateDescription.class);
                            intent.putExtra("user",user3);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 1 && !(activity instanceof MyProfile)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MyProfile.class);
                            intent.putExtra("user",user3);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 6 && !(activity instanceof MainActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.putExtra("user",user3);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 5 && !(activity instanceof MessageList)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MessageList.class);
                            intent.putExtra("user",user3);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 4 && !(activity instanceof MessageCreate)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, MessageCreate.class);
                            MessageCoordinator.Message message=new MessageCoordinator.Message();
                            message.title="Re: About the App";
                            message.detail="Dear Admin";
                            message.sender_email=user3.email;
                            message.receiver_email="2";
                            intent.putExtra("user",user3);
                            intent.putExtra("id","admin");
                            intent.putExtra("message",message);
                            intent.putExtra("user",user3);
                            view.getContext().startActivity(intent);
                        }
                        else if (drawerItem.getIdentifier() == 3 && !(activity instanceof EventListJoined)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, EventListJoined.class);
                            intent.putExtra("user",user3);
                            view.getContext().startActivity(intent);
                        }

                        return true;
                    }
                })
                .build();
    }
}
