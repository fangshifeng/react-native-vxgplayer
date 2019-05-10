package test.fsf.com.vxgplayer.ReactNative;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * Created by fangshifeng on 2017/8/16.
 */

public class VXGPlayerModule extends ReactContextBaseJavaModule{
    private static final String REACT_CLASS = "RCTVXGPlayer";

    public VXGPlayerModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
