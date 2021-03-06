package ir.vasl.magicalnotifier;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import ir.vasl.Utils.PublicFunctions;
import ir.vasl.globalEnums.EnumNotificationType;
import ir.vasl.globalObjects.ActionButton;

import static android.content.Context.NOTIFICATION_SERVICE;
import static ir.vasl.Utils.PublicValues.NOTIFICATION_CHANNEL_ID;
import static ir.vasl.Utils.PublicValues.NOTIFICATION_CHANNEL_NAME;
import static ir.vasl.Utils.PublicValues.NOTIFICATION_CHANNEL_SIMPLE;

/**
 * Created by HamidReza on 12,January,2019
 * Happy Coding ;)
 */
public class MagicalNotifier {

    private Context context;
    private int notificationId = -1;
    private int smallIcon = -1;
    private int largeIcon = -1;
    private ActionButton actionButtonOne = null;
    private ActionButton actionButtonTwo = null;
    private ActionButton actionButtonThree = null;
    private EnumNotificationType notificationType = EnumNotificationType.SMART;
    private String title;
    private String subTitle;
    private String button;
    private String bigPictureUrl = null;
    private String bigText = null;
    private String bigVideoUrl = null;
    private PendingIntent pendingIntent;

    private Builder builder = null;

    public MagicalNotifier() {
    }

    public MagicalNotifier(Context context, int notificationId, int smallIcon, int largeIcon, ActionButton actionButtonOne, ActionButton actionButtonTwo, ActionButton actionButtonThree, EnumNotificationType notificationType, String title, String subTitle, String button, String bigPictureUrl, String bigText, String bigVideoUrl, PendingIntent pendingIntent) {
        this.context = context;
        this.notificationId = notificationId;
        this.smallIcon = smallIcon;
        this.largeIcon = largeIcon;
        this.actionButtonOne = actionButtonOne;
        this.actionButtonTwo = actionButtonTwo;
        this.actionButtonThree = actionButtonThree;
        this.notificationType = notificationType;
        this.title = title;
        this.subTitle = subTitle;
        this.button = button;
        this.bigPictureUrl = bigPictureUrl;
        this.bigText = bigText;
        this.bigVideoUrl = bigVideoUrl;
        this.pendingIntent = pendingIntent;
    }

    public Builder getBuilder() {
        return builder;
    }

    private void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public static class Builder {

        private Context context;
        private int notificationId = -1;
        private int smallIcon = -1;
        private int largeIcon = -1;
        private ActionButton actionButtonOne;
        private ActionButton actionButtonTwo;
        private ActionButton actionButtonThree;
        private EnumNotificationType notificationType = EnumNotificationType.SMART;
        private String title;
        private String subTitle;
        private String button;
        private String bigPictureUrl;
        private String bigText;
        private String bigVideoUrl;
        private PendingIntent pendingIntent;

        private NotificationCompat.Builder mBuilder;
        private NotificationManager mNotificationManager;

        public Builder(Context context) {
            this.context = context;
            this.mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_SIMPLE);
            this.mBuilder.setVibrate(null);
            this.mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        }

        public Builder setNotificationId(int notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public Builder setSmallIcon(int smallIcon) {
            this.smallIcon = smallIcon;
            return this;
        }

        public Builder setLargeIcon(int largeIcon) {
            this.largeIcon = largeIcon;
            return this;
        }

        public Builder setActionButtonOne(ActionButton actionButtonOne) {
            this.actionButtonOne = actionButtonOne;
            return this;
        }

        public Builder setActionButtonTwo(ActionButton actionButtonTwo) {
            this.actionButtonTwo = actionButtonTwo;
            return this;
        }

        public Builder setActionButtonThree(ActionButton actionButtonThree) {
            this.actionButtonThree = actionButtonThree;
            return this;
        }

        public Builder setNotificationType(EnumNotificationType notificationType) {
            this.notificationType = notificationType;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public Builder setButton(String button) {
            this.button = button;
            return this;
        }

        public Builder setBigPictureUrl(String bigPictureUrl) {
            this.bigPictureUrl = bigPictureUrl;
            return this;
        }

        public Builder setBigText(String bigText) {
            this.bigText = bigText;
            return this;
        }

        public Builder setBigVideoUrl(String bigVideoUrl) {
            this.bigVideoUrl = bigVideoUrl;
            return this;
        }

        public Builder setPendingIntent(PendingIntent pendingIntent) {
            this.pendingIntent = pendingIntent;
            return this;
        }

        public MagicalNotifier build() {
            MagicalNotifier magicalNotifier = new MagicalNotifier(context, notificationId, smallIcon, largeIcon, actionButtonOne, actionButtonTwo, actionButtonThree, notificationType, title, subTitle, button, bigPictureUrl, bigText, bigVideoUrl, pendingIntent);
            magicalNotifier.setBuilder(this);
            return magicalNotifier;
        }

        public MagicalNotifier show() {
            switch (notificationType) {
                case SIMPLE:
                    showSimpleNotification();
                    break;
                case SIMPLE_WITH_AVATAR:
                    showSimpleNotificationWithAvatar();
                    break;
                case SIMPLE_WITH_AVATAR_AND_BUTTON:
                    showSimpleNotificationWithAvatarAndButton();
                    break;
                case BIG_PICTURE:
                    showBigPictureNotification();
                    break;
                case BIG_TEXT:
                    showBigTextNotification();
                    break;
                case CUSTOM:
                    showCustomNotification();
                    break;
                case undefined:
                case SMART:
                    showSmartNotification();
                    break;
            }

            MagicalNotifier magicalNotifier = new MagicalNotifier(context, notificationId, smallIcon, largeIcon, actionButtonOne, actionButtonTwo, actionButtonThree, notificationType, title, subTitle, button, bigPictureUrl, bigText, bigVideoUrl, pendingIntent);
            magicalNotifier.setBuilder(this);
            return magicalNotifier;
        }

        /*
         * NOTIFICATION TYPES
         * */
        private void showSimpleNotification() {
            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setPriority(NotificationCompat.DEFAULT_ALL).setVibrate(null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = 0x008000;
                mBuilder.setColor(color);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_LOW);
                channel.setVibrationPattern(new long[]{0});
                channel.enableVibration(false);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            }

            if (pendingIntent != null)
                mBuilder.setContentIntent(pendingIntent);

            // notificationId is a unique int for each notification that you must define
            if (notificationId == -1)
                notificationId = PublicFunctions.getNotificationID();

            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        private void showSimpleNotificationWithAvatar() {
            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            if (largeIcon == -1)
                largeIcon = R.drawable.ic_default_large_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon))
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setPriority(NotificationCompat.PRIORITY_LOW);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = 0xC0C0C0;
                mBuilder.setColor(color);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_LOW);
                channel.setVibrationPattern(new long[]{0});
                channel.enableVibration(false);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            }

            // notificationId is a unique int for each notification that you must define
            if (notificationId == -1)
                notificationId = PublicFunctions.getNotificationID();

            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        private void showSimpleNotificationWithAvatarAndButton() {
            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            if (largeIcon == -1)
                largeIcon = R.drawable.ic_default_large_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon))
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setColor(ContextCompat.getColor(context, R.color.primary));

            if (actionButtonOne != null) {
                switch (actionButtonOne.getEnumNotificationAction()) {
                    case OPEN_URL:
                        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionButtonOne.getTargetUrl()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
                        mBuilder.addAction(actionButtonOne.getIcon(), actionButtonOne.getTitle(), pendingIntent);
                        break;

                    case OPEN_APP:
                        break;
                }
            }

            if (actionButtonTwo != null) {
                switch (actionButtonTwo.getEnumNotificationAction()) {
                    case OPEN_URL:
                        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionButtonTwo.getTargetUrl()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
                        mBuilder.addAction(actionButtonTwo.getIcon(), actionButtonTwo.getTitle(), pendingIntent);
                        break;

                    case OPEN_APP:
                        break;
                }
            }

            if (actionButtonThree != null) {
                // mBuilder.addAction(actionButtonThree.getIcon(), actionButtonThree.getTitle(), null);
                switch (actionButtonThree.getEnumNotificationAction()) {
                    case OPEN_URL:
                        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionButtonThree.getTargetUrl()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
                        mBuilder.addAction(actionButtonThree.getIcon(), actionButtonThree.getTitle(), pendingIntent);
                        break;

                    case OPEN_APP:
                        break;
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = 0xC0C0C0;
                mBuilder.setColor(color);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            }

            if (pendingIntent != null)
                mBuilder.setContentIntent(pendingIntent);

            // notificationId is a unique int for each notification that you must define
            if (notificationId == -1)
                notificationId = PublicFunctions.getNotificationID();

            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        private void showBigPictureNotification() {
            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setLights(Color.YELLOW, 1000, 300);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = 0x008000;
                mBuilder.setColor(color);
            }

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Glide
                            .with(context).asBitmap().load(bigPictureUrl)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                                    NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle().bigPicture(resource);
                                    bigPictureStyle.setSummaryText(subTitle);
                                    mBuilder.setStyle(bigPictureStyle);

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                                                NOTIFICATION_CHANNEL_NAME,
                                                NotificationManager.IMPORTANCE_HIGH);
                                        mNotificationManager.createNotificationChannel(channel);
                                        mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                                    }

                                    if (pendingIntent != null)
                                        mBuilder.setContentIntent(pendingIntent);

                                    // notificationId is a unique int for each notification that you must define
                                    if (notificationId == -1)
                                        notificationId = PublicFunctions.getNotificationID();

                                    mNotificationManager.notify(notificationId, mBuilder.build());
                                }
                            });
                }
            });
        }

        private void showBigTextNotification() {
            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = 0x008000;
                mBuilder.setColor(color);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            }

            if (pendingIntent != null)
                mBuilder.setContentIntent(pendingIntent);

            // notificationId is a unique int for each notification that you must define
            if (notificationId == -1)
                notificationId = PublicFunctions.getNotificationID();

            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        private void showSmartNotification() {
            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            if (largeIcon != -1)
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon));

            if (pendingIntent != null)
                mBuilder.setContentIntent(pendingIntent);

            if (actionButtonOne != null) {
                switch (actionButtonOne.getEnumNotificationAction()) {
                    case OPEN_URL:
                        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionButtonOne.getTargetUrl()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
                        mBuilder.addAction(actionButtonOne.getIcon(), actionButtonOne.getTitle(), pendingIntent);
                        break;

                    case OPEN_APP:
                        break;
                }
            }

            if (actionButtonTwo != null) {
                switch (actionButtonTwo.getEnumNotificationAction()) {
                    case OPEN_URL:
                        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionButtonTwo.getTargetUrl()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
                        mBuilder.addAction(actionButtonTwo.getIcon(), actionButtonTwo.getTitle(), pendingIntent);
                        break;

                    case OPEN_APP:
                        break;
                }
            }

            if (actionButtonThree != null) {
                // mBuilder.addAction(actionButtonThree.getIcon(), actionButtonThree.getTitle(), null);
                switch (actionButtonThree.getEnumNotificationAction()) {
                    case OPEN_URL:
                        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionButtonThree.getTargetUrl()));
                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
                        mBuilder.addAction(actionButtonThree.getIcon(), actionButtonThree.getTitle(), pendingIntent);
                        break;

                    case OPEN_APP:
                        break;
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int color = 0x008000;
                mBuilder.setColor(color);
            }

            if (bigPictureUrl != null) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        Glide
                                .with(context).asBitmap().load(bigPictureUrl)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                        super.onLoadFailed(errorDrawable);
                                    }

                                    @Override
                                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle().bigPicture(resource);
                                        bigPictureStyle.setSummaryText(subTitle);
                                        mBuilder.setStyle(bigPictureStyle);

                                        NotificationManager mNotificationManager =
                                                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                                                    NOTIFICATION_CHANNEL_NAME,
                                                    NotificationManager.IMPORTANCE_HIGH);
                                            mNotificationManager.createNotificationChannel(channel);
                                            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                                        }

                                        // notificationId is a unique int for each notification that you must define
                                        mNotificationManager.notify(PublicFunctions.getNotificationID(), mBuilder.build());
                                    }
                                });
                    }
                });

            } else if (bigText != null) {
                mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                            NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_HIGH);
                    mNotificationManager.createNotificationChannel(channel);
                    mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                }

                // notificationId is a unique int for each notification that you must define
                if (notificationId == -1)
                    notificationId = PublicFunctions.getNotificationID();

                mNotificationManager.notify(notificationId, mBuilder.build());

            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                            NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_HIGH);
                    mNotificationManager.createNotificationChannel(channel);
                    mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
                }

                // notificationId is a unique int for each notification that you must define
                if (notificationId == -1)
                    notificationId = PublicFunctions.getNotificationID();

                mNotificationManager.notify(notificationId, mBuilder.build());
            }
        }

        private void showCustomNotification() {
            RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.layout_expanded_notification);
            RemoteViews collapsedView = new RemoteViews(context.getPackageName(), R.layout.layout_collapsed_notification);

            expandedView.setImageViewResource(R.id.big_icon, R.drawable.ic_default_large_notification);
            expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));

            if (bigText != null)
                expandedView.setTextViewText(R.id.notification_message, bigText);

            if (smallIcon == -1)
                smallIcon = R.drawable.ic_default_notification;

            mBuilder
                    .setSmallIcon(smallIcon)
                    .setContentTitle(title)
                    .setContentText(subTitle)
                    .setAutoCancel(true)
                    .setCustomContentView(collapsedView)
                    .setCustomBigContentView(expandedView)
                    .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

            if (pendingIntent != null)
                mBuilder.setContentIntent(pendingIntent);

            // notificationId is a unique int for each notification that you must define
            if (notificationId == -1)
                notificationId = PublicFunctions.getNotificationID();

            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        /*
         * NOTIFIERS
         * */

        public void notifyTitle(int notificationId, String title) {
            mBuilder.setContentTitle(title);
            mNotificationManager.notify(notificationId, mBuilder.build());
        }

        public void notifySubTitle(int notificationId, String subTitle) {
            mBuilder.setContentText(subTitle);
            mNotificationManager.notify(notificationId, mBuilder.build());
        }
    }
}
