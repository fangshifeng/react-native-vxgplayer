package test.fsf.com.vxgplayer.ReactNative;

import android.os.Looper;
import android.text.TextUtils;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.nio.ByteBuffer;
import java.util.Map;

import javax.annotation.Nullable;

import test.fsf.com.vxgplayer.util.SharedSettings;
import veg.mediaplayer.sdk.MediaPlayer;

/**
 * Created by fangshifeng on 2017/8/16.
 */

public class VXGPlayerViewManager extends ViewGroupManager<MediaPlayer>{
    private static final String REACT_CLASS = "RCTVXGPlayer";
    private static final int PLAYER_ACTION_STOP = 1;
    private ThemedReactContext mReactContext;
    private MediaPlayer mPlayer;
    public boolean mPanelIsVisible = true;
    public boolean isFileUrl = false;
    private PlayerCallBacks currentPlayerCallBacks = null;

    public VXGPlayerViewManager(ReactApplicationContext reactContext) {
        Looper.prepare();
        mPlayer = new MediaPlayer(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MediaPlayer createViewInstance(ThemedReactContext reactContext) {
        if(mPlayer != null) {
            mPlayer.Close();
        }

        mReactContext = reactContext;

        return mPlayer;
    }

    @ReactProp(name = "src")
    public void setSrc(MediaPlayer player, String src) {
        if(!TextUtils.isEmpty(src)) {
            startRending(src);
        }
    }

    /**
     * 开始播放
     *
     * @param src
     */
    private void startRending(String src) {
        SharedSettings.getInstance(mReactContext).loadPrefSettings();
        SharedSettings sett = SharedSettings.getInstance();
        int connectionProtocol = sett.connectionProtocol;
        int connectionDetectionTime = sett.connectionDetectionTime;
        int connectionBufferingTime = sett.connectionBufferingTime;

        int decoderType = sett.decoderType;
        int rendererType = sett.rendererType;
        int rendererEnableColorVideo = sett.rendererEnableColorVideo;
        int rendererAspectRatioMode = mPanelIsVisible ? 0 : sett.rendererAspectRatioMode;
        int synchroEnable = sett.synchroEnable;
        int synchroNeedDropVideoFrames = sett.synchroNeedDropVideoFrames;

        isFileUrl = isUrlFile(src);

        currentPlayerCallBacks = new PlayerCallBacks();

        mPlayer.Open(src,
                connectionProtocol,
                connectionDetectionTime,
                connectionBufferingTime,
                decoderType,
                rendererType,
                synchroEnable,
                synchroNeedDropVideoFrames,
                rendererEnableColorVideo,
                rendererAspectRatioMode,
                isFileUrl ? 1 : mPlayer.getConfig().getDataReceiveTimeout(),
                sett.decoderNumberOfCpuCores,
                currentPlayerCallBacks);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "stop", PLAYER_ACTION_STOP
        );
    }

    @Override
    public void receiveCommand(MediaPlayer root, int commandId, @Nullable ReadableArray args) {
        switch(commandId){
            case PLAYER_ACTION_STOP:
                if(mPlayer!=null){
                    mPlayer.Close();
                    mPlayer.onDestroy();
                }
                break;
        }
    }

    private boolean isUrlFile(String url) {
        return (url != null && !url.isEmpty() &&
                (!url.contains("://") || url.contains("file://")));
    }

    private class PlayerCallBacks implements MediaPlayer.MediaPlayerCallback{
        public PlayerCallBacks(){}

        @Override
        public int Status(int i) {
            return 0;
        }

        @Override
        public int OnReceiveData(ByteBuffer byteBuffer, int i, long l) {
            return 0;
        }
    }
}
