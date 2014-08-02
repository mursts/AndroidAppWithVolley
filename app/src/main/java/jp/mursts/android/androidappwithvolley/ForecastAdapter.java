package jp.mursts.android.androidappwithvolley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import jp.mursts.android.androidappwithvolley.models.LWWS;

public class ForecastAdapter extends ArrayAdapter<LWWS.Forecasts> {
    private static final String TAG = ForecastAdapter.class.getSimpleName();

    private LayoutInflater mInfrater;
    private ImageLoader mImageLoader;

    public ForecastAdapter(Context context, RequestQueue queue) {
        super(context, 0);
        mInfrater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(queue, new LruCacheSample());
    }

    private class ViewHolder {
        private ImageView mIcon;
        private TextView mDate;
        private TextView mForecasts;
        private TextView mTemperature;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if(convertView == null) {
            convertView = mInfrater.inflate(R.layout.list_item_row, parent, false);
            mHolder = new ViewHolder();

            mHolder.mIcon = (ImageView) convertView.findViewById(R.id.icon);
            mHolder.mDate = (TextView) convertView.findViewById(R.id.date);
            mHolder.mForecasts = (TextView) convertView.findViewById(R.id.forecast);
            mHolder.mTemperature = (TextView) convertView.findViewById(R.id.temperature);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        LWWS.Forecasts forecasts = getItem(position);

        String iconUrl = forecasts.getImage().getUrl();

        mHolder.mDate.setText(forecasts.getDate());
        mHolder.mForecasts.setText(forecasts.getTelop());
        mHolder.mTemperature.setText(forecasts.getTemperatures());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(
                mHolder.mIcon, 0, 0);
        mImageLoader.get(iconUrl, listener);

        return convertView;
    }

    /**
     * @see http://techbooster.org/android/hacks/16474/
     */
    private class LruCacheSample implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mMemoryCache;

        LruCacheSample(){
            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            int cacheSize = maxMemory / 8;       // 最大メモリに依存
            // int cacheSize = 5 * 1024 * 1024;  // 5MB

            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // 使用キャッシュサイズ(KB単位)
                    return bitmap.getByteCount() / 1024;
                }
            };
        }

        // ImageCacheのインターフェイス実装
        @Override
        public Bitmap getBitmap(String url) {
            return mMemoryCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mMemoryCache.put(url,bitmap);
        }
    }
}
