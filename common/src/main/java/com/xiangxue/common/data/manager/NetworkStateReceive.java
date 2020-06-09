package com.xiangxue.common.data.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.xiangxue.common.R;
import com.xiangxue.common.utils.NetworkUtils;

import java.util.Objects;

/**
 * 监听网络状态的广播接收者
 * 例如：可以收到 “网络不给力”
 */
public class NetworkStateReceive extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!NetworkUtils.isConnected()) {
                Toast.makeText(context, context.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
