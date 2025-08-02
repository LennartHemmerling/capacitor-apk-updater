package com.fromscript.plugins.capacitorapkupdater;

import com.getcapacitor.Logger;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApkUpdater {
    public void downloadAndInstall(Plugin apkUpdaterPlugin, PluginCall call, String url) {
        apkUpdaterPlugin.getActivity().runOnUiThread(() -> new Thread(() -> {
            try {
                // Check unknown sources permission (Android 8+)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean canInstall = apkUpdaterPlugin.getContext().getPackageManager().canRequestPackageInstalls();
                    if (!canInstall) {
                        Intent settingsIntent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                        settingsIntent.setData(Uri.parse("package:" + apkUpdaterPlugin.getContext().getPackageName()));
                        settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        apkUpdaterPlugin.getContext().startActivity(settingsIntent);
                        call.reject("App needs permission to install unknown apps.");
                        return;
                    }
                }

                // Download the APK
                URL downloadUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    call.reject("HTTP error: " + connection.getResponseCode());
                    return;
                }

                InputStream input = new BufferedInputStream(connection.getInputStream());
                File apkFile = new File(apkUpdaterPlugin.getContext().getExternalFilesDir(null), "update.apk");
                OutputStream output = new FileOutputStream(apkFile);

                byte[] data = new byte[4096];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
                connection.disconnect();

                // Get URI for FileProvider
                Uri apkUri = FileProvider.getUriForFile(
                        apkUpdaterPlugin.getContext(),
                        apkUpdaterPlugin.getContext().getPackageName() + ".fileprovider",
                        apkFile
                );

                // Launch installer intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                apkUpdaterPlugin.getContext().startActivity(intent);

                call.resolve();

            } catch (Exception e) {
                Log.e("ApkUpdater", "Download or install failed", e);
                call.reject("Failed: " + e.getMessage());
            }
        }).start());
    }
}
