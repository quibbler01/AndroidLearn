package com.easyicon.learnglide.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.easyicon.learnglide.util.Utils;

public class MessengerService extends Service {

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    public MessengerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QUIBBLER", "服务端：" + Utils.getProcessName(this, Process.myPid()));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("QUIBBLER", "客户端进程发送来的消息：" + msg.getData().getString("msg"));
                    Messenger clientMessenger = msg.replyTo;
                    Message replyMessage = Message.obtain(null, 1);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "服务端回复消息");
                    replyMessage.setData(bundle);
                    try {
                        clientMessenger.send(replyMessage);
                    } catch (RemoteException e) {
                        Log.e("QUIBBLER", "服务端->客户端失败" + e.toString());
                    }
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
