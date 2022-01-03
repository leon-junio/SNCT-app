import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MostrarImagem extends SurfaceView implements SurfaceHolder.Callback
{
    Camera camera;
    public MostrarImagem(Context context,Camera camera) {
        super(context);
        this.camera = camera;
    }
    @Override
   public void  surfaceChanged(SurfaceHolder holder , int f, int w,int h){

   }
   @Override
    public void surfaceDestroyed(SurfaceHolder holder){

   }
   @Override
    public void surfaceCreated(SurfaceHolder holder){

   }

}
