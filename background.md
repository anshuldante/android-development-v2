# Background Tasks

* ![Task type decision tree](/android/android-development-v2/resources/task-category-tree.png)
* Immediate tasks => WorkManager or ForegroundServices.
* Deferred tasks => WorkManager
* Exact tasks => AlarmManager
* Runtime.getRuntime().availableProcessors()
* Ways update the ui from a non-main thread.
  * Activity.runOnUiThread(Runnable)
  * View.post(Runnable)
  * View.postDelayed(Runnable, long)
* Apps will still receive CONNECTIVITY_ACTION broadcasts if they register their BroadcastReceiver with Context.registerReceiver() and that context is still valid.

```java
// Schedule a job to run when network connectivity is resumed
Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build(); 
OneTimeWorkRequest onetimeJob = new OneTimeWorkRequest.Builder(YourJob.class)
                    .setConstraints(constraints).build(); // or PeriodicWorkRequest
WorkManager.getInstance().enqueue(onetimeJob);
```

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
