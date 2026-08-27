package com.my.newproject;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {

    LinearLayout root, content;
    TextView title, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    TextView text(String s, int size) {
        TextView v = new TextView(this);
        v.setText(s);
        v.setTextSize(size);
        v.setTextColor(Color.rgb(35,35,35));
        v.setPadding(24,18,24,18);
        return v;
    }

    Button button(String s) {
        Button b = new Button(this);
        b.setText(s);
        b.setAllCaps(false);
        b.setPadding(12,12,12,12);
        return b;
    }

    void base(String screen) {
        ScrollView scroll = new ScrollView(this);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18,18,18,18);
        root.setBackgroundColor(Color.WHITE);

        title = text("Watch In Earn  •  " + screen, 24);
        title.setTextColor(Color.rgb(70,45,180));
        root.addView(title);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        root.addView(content);

        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);

        String[] tabs = {"Home","Tasks","Wallet","Profile","Admin"};
        for (String t : tabs) {
            Button b = button(t);
            nav.addView(b, new LinearLayout.LayoutParams(0, -2, 1));
            if (t.equals("Home")) b.setOnClickListener(v -> showHome());
            if (t.equals("Tasks")) b.setOnClickListener(v -> showTasks());
            if (t.equals("Wallet")) b.setOnClickListener(v -> showWallet());
            if (t.equals("Profile")) b.setOnClickListener(v -> showProfile());
            if (t.equals("Admin")) b.setOnClickListener(v -> showAdmin());
        }
        root.addView(nav);
        scroll.addView(root);
        setContentView(scroll);
    }

    void showHome() {
        base("Home");
        balance = text("Balance: 0 Coins", 22);
        balance.setTextColor(Color.rgb(40,130,70));
        content.addView(balance);

        content.addView(text("Welcome! Complete available tasks and manage your rewards.", 17));

        Button tasks = button("▶ View Video Tasks");
        tasks.setOnClickListener(v -> showTasks());
        content.addView(tasks);

        Button checkin = button("📅 Daily Check-in");
        checkin.setOnClickListener(v -> {
            Toast.makeText(this, "Daily check-in recorded (demo)", Toast.LENGTH_SHORT).show();
        });
        content.addView(checkin);

        Button referral = button("👥 Referral");
        referral.setOnClickListener(v -> Toast.makeText(this, "Referral code: WIE12345", Toast.LENGTH_LONG).show());
        content.addView(referral);
    }

    void showTasks() {
        base("Video Tasks");
        content.addView(text("Sample tasks", 20));
        addTask("Video Task 1", "https://www.youtube.com/");
        addTask("Video Task 2", "https://www.youtube.com/");
        addTask("Video Task 3", "https://www.youtube.com/");
        content.addView(text("Note: This starter project does not automatically reward YouTube watch time. A compliant verification/task system and backend must be added.", 14));
    }

    void addTask(String name, String url) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(12,12,12,12);
        card.addView(text(name, 19));
        card.addView(text("Open task", 14));
        Button open = button("Open");
        open.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(i);
        });
        card.addView(open);
        content.addView(card);
    }

    void showWallet() {
        base("Wallet");
        content.addView(text("Available Balance: 0 Coins", 22));
        content.addView(text("Withdrawal requests will appear in the admin section after a backend is connected.", 16));
        Button withdraw = button("Request Withdrawal");
        withdraw.setOnClickListener(v -> showWithdrawDialog());
        content.addView(withdraw);
    }

    void showWithdrawDialog() {
        final EditText amount = new EditText(this);
        amount.setHint("Enter amount / coins");
        new AlertDialog.Builder(this)
            .setTitle("Withdrawal Request")
            .setView(amount)
            .setPositiveButton("Submit", (d,w) -> Toast.makeText(this, "Request saved locally (demo)", Toast.LENGTH_SHORT).show())
            .setNegativeButton("Cancel", null).show();
    }

    void showProfile() {
        base("Profile");
        content.addView(text("Mobile login / OTP integration placeholder", 18));
        content.addView(text("User Profile\n• Mobile Number\n• Referral History\n• Transaction History\n• Help & Support", 16));
    }

    void showAdmin() {
        base("Admin Panel");
        content.addView(text("Admin access should be protected with secure authentication.", 16));
        Button videos = button("Manage Videos");
        videos.setOnClickListener(v -> Toast.makeText(this, "Add/edit/delete task UI to be connected to backend", Toast.LENGTH_LONG).show());
        content.addView(videos);
        content.addView(button("Withdrawal Requests"));
        content.addView(button("User Management"));
        content.addView(button("Daily Check-in Settings"));
        content.addView(button("Notifications"));
        content.addView(button("Ad Settings"));
    }
}
