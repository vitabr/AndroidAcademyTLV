package com.aat.rntv;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Refael Ozeri on 05/03/2016.
 */
public class BaseApplication extends Application {

    private static BaseApplication singleton;
    public static BaseApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        singleton = this;
    }

    private void createDumpDB(){
//        User user = new User();
//        //user.setmUid();
//
//        Lesson lesson = new Lesson();
//        lesson.setStartDate(new Date());
//        lesson.setTitle("Lecture + lab session. Bring your laptops with eclipse and android SDK working.");
//        lesson.setDescription("Lecture #1  (IDE instructions below)\n" +
//                "\n" +
//                "• Course and Team Overview\n" +
//                "\n" +
//                "• Android Overview - What is Android, Linux, layers... very high level\n" +
//                "\n" +
//                "• Show Eclipse and the perspective we can exploit\n" +
//                "\n" +
//                "• Application Fundamentals: Activity, Services, Broadcast, Content.\n" +
//                "\n" +
//                "• The Activity Class - Life Cycle\n" +
//                "\n" +
//                "• Eclipse project structure\n" +
//                "\n" +
//                "• Log class\n" +
//                "\n" +
//                "• Toast");
//
//        lesson = new Lesson();
//        lesson.setStartDate(new Date());
//        lesson.setTitle("Lecture #2: Views, Manifest, Permissions & Explicit Intent");
//        lesson.setDescription("Lecture\n" +
//                "\n" +
//                "Part 1\n" +
//                "\n" +
//                "• AnyDon’t - Task app.\n" +
//                "\n" +
//                "• Introduction to Android framework.\n" +
//                "\n" +
//                "• Life Cycle - Context\n" +
//                "\n" +
//                "• Eclipse project structure.\n" +
//                "\n" +
//                "• Fragmentation\n" +
//                "\n" +
//                "• Main User Interface Classes - layouts,forms,etc\n" +
//                "\n" +
//                "• Manipulating Views - XML & Programmatically\n" +
//                "\n" +
//                "*************************LAB 1**************************\n" +
//                "\n" +
//                "Part 2\n" +
//                "\n" +
//                "• Adding an Activity/Service/ Content…..\n" +
//                "\n" +
//                "• Design Patterns\n" +
//                "\n" +
//                "• Adapter design pattern.\n" +
//                "\n" +
//                "• Explicit Intents.\n" +
//                "\n" +
//                "• Observer design pattern (Listener)\n" +
//                "\n" +
//                "*************************LAB 2************************** ");

    }

}
