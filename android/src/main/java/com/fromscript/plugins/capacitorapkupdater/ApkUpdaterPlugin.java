package com.fromscript.plugins.capacitorapkupdater;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.getcapacitor.PermissionState;
import android.Manifest;

@CapacitorPlugin(name = "ApkUpdater", permissions = {
    @Permission(
        strings = { Manifest.permission.INTERNET },
        alias = ApkUpdaterPlugin.INTERNET
    ),
    @Permission(
        strings = { Manifest.permission.REQUEST_INSTALL_PACKAGES },
        alias = ApkUpdaterPlugin.INSTALL
    )
})
public class ApkUpdaterPlugin extends Plugin {

    private ApkUpdater implementation = new ApkUpdater();

    static final String INTERNET = "internet";
    static final String INSTALL = "install";

    @PluginMethod
    public void downloadAndInstall(PluginCall call) {
        String url = call.getString("url");

        if (url == null || url.isEmpty()) {
            call.reject("Missing URL");
            return;
        }

        implementation.downloadAndInstall(this, call, url);
    }

    @PluginMethod
    public void checkPermissions(PluginCall call) {
        if(getPermissionState(INSTALL) != PermissionState.GRANTED) {
            requestPermissionForAlias(INSTALL, call, "pluginPermissionsCallback");
        }
    }

    @PermissionCallback
    private void pluginPermissionsCallback(PluginCall call) {
        JSObject ret = new JSObject();

        Boolean internetPermission = getPermissionState(INTERNET) == PermissionState.GRANTED;
        Boolean installPermission = getPermissionState(INSTALL) == PermissionState.GRANTED;

        if(internetPermission && installPermission) {
            ret.put("granted", true);
        }else {
            ret.put("rejected", true);
        }

        ret.put("internet", internetPermission);
        ret.put("install", installPermission);

        call.resolve(ret);
    }
}
