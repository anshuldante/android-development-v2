# Threads and Processes

* Ways update the ui from a non-main thread.
  * Activity.runOnUiThread(Runnable)
  * View.post(Runnable)
  * View.postDelayed(Runnable, long)

```java
public void onClick(View v) {
    new Thread(new Runnable() {
        public void run() {
            // a potentially time-consuming task
            final Bitmap bitmap =
                    processBitMap("image.png");
            imageView.post(new Runnable() {
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        }
    }).start();
}
```
