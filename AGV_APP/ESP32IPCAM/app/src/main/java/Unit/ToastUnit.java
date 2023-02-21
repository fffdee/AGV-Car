package Unit;

import android.content.Context;
import android.widget.Toast;

public class ToastUnit {
    public static Toast mToast;
    public  static void showMsg(Context context,String msg){
        if(mToast == null){
            mToast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);

        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
